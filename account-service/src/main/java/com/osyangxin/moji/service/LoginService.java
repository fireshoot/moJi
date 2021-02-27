package com.osyangxin.moji.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.osyangxin.dao.mapper.AccountInfoMapper;
import com.osyangxin.dao.mapper.SysAuthMapper;
import com.osyangxin.dao.mapper.SysUserMapper;
import com.osyangxin.dao.model.AccountInfo;
import com.osyangxin.dao.model.SysAuthOrdinalView;
import com.osyangxin.dao.model.SysRole;
import com.osyangxin.dao.model.SysUser;
import com.osyangxin.moji.bean.Condition;
import com.osyangxin.moji.common.bean.User;
import com.osyangxin.moji.common.constants.Constants;
import com.osyangxin.moji.common.enums.DeletedStatus;
import com.osyangxin.moji.common.enums.UserStatus;
import com.osyangxin.moji.common.enums.YesNoEnum;
import com.osyangxin.moji.common.exception.ApplicationException;
import com.osyangxin.moji.common.utils.HashUtil;
import com.osyangxin.moji.input.LoginForm;
import com.osyangxin.moji.input.ResetPasswordForm;
import com.osyangxin.moji.input.UserRegisterFrom;
import com.osyangxin.moji.msg.RetStubDetail;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


import static com.osyangxin.moji.common.constants.Constants.HEADER_MEMBER_TOKEN;
import static com.osyangxin.moji.common.constants.Constants.SUPPLIER_CODE;

@Service
public class LoginService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private int oneWeek = 604800;

    @Resource
    private CacheService cacheService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysAuthMapper sysAuthMapper;
    @Resource
    private AccountInfoMapper accountInfoMapper;


    public Map<String, Object> login(LoginForm input) {
        Date now = new Date();
        long startTime = System.currentTimeMillis();

        checkoutCaptcha(input);

        // 根据用户名查询用户
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition("name", input.getKeywords()));
        conditions.add(new Condition("deleted", DeletedStatus.DELETED_NO.getStatus()));
        List<SysUser> userList = sysUserMapper.getByConditionList(conditions);

        if (userList == null || userList.size() == 0) {
            throw new ApplicationException(RetStubDetail.USER_NOT_FOUND);
        }
        SysUser userFromDb = userList.get(0);

        // 比较用户状态
        compareUserStatus(userFromDb.getStatus());

        // 比较用户密码是否正确
        comparePassword(userFromDb.getCreatedAt(), userFromDb.getPassword(), input.getPassword());

        SysRole role = sysUserMapper.getRoleStatusByUserId(userFromDb.getId());
        if (role == null || role.getStatus() == YesNoEnum.N.getStatus().shortValue()) {
            throw new ApplicationException(RetStubDetail.USER_DISABLE);
        }
        // 更新登陆时间
        SysUser userUpdate = new SysUser();
        userUpdate.setId(userFromDb.getId());
        userUpdate.setLastVisitTime(now);
        userUpdate.setGetMsgAt(now);
        sysUserMapper.updateByPrimaryKeySelective(userUpdate);

        // 将用户信息放入session
        User user = createUserInfo(userFromDb);
        String token = UUID.randomUUID().toString();

        cacheService.setVal(String.format(Constants.CACHE_USER_SESSION_LOGIN_PREFIX, token), user.getId() + "," + 1, (long) oneWeek);

        long endTime = System.currentTimeMillis();
        log.debug("{}登录用时:{}ms", userFromDb.getName(), endTime - startTime);

        Map<String, Object> result = new HashMap<>(2);
        result.put("user", user);
        result.put(HEADER_MEMBER_TOKEN, token);
        return result;
    }

    public Boolean userRegister(UserRegisterFrom userRegisterFrom) {
        AccountInfo info = new AccountInfo();
        BeanUtils.copyProperties(userRegisterFrom, info);

        info.setAccountNum(generatorSupplierCode());
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        info.setAccountStatus(YesNoEnum.Y.getStatus());
        String salt = String.valueOf(info.getCreateTime().getTime());
        info.setPassword(HashUtil.encryptUserPasswordByMD5(userRegisterFrom.getPassword(), salt));
        accountInfoMapper.insert(info);
        return true;
    }

    private String generatorSupplierCode() {
        String prefix = "UN";
        StringBuffer sb = new StringBuffer(prefix);

        long code = cacheService.generate(SUPPLIER_CODE);
        String formate = "%07d";
        if (code > 9999999) {
            formate = "%0" + String.valueOf(code).length() + "d";
        }
        return sb.append(String.format(formate, code)).toString();
    }


    private void comparePassword(Date createAt, String password, String inputPassword) {
        if (!validatePassword(createAt, password, inputPassword)) {
            throw new ApplicationException(RetStubDetail.USER_PASSWORD_ERROR);
        }
    }

    private void compareUserStatus(Integer status) {

        if (status == null || status == UserStatus.STATUS_DISABLE.getStatus()) {
            throw new ApplicationException(RetStubDetail.USER_DISABLE);
        }
    }


    public String userLogin(LoginForm input) {
        checkoutCaptcha(input);

        LambdaQueryWrapper<AccountInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.and(wrapperItem -> wrapperItem.eq(AccountInfo::getAccountName, input.getKeywords())
                .or()
                .eq(AccountInfo::getAccountPhone, input.getKeywords())
                .or()
                .eq(AccountInfo::getAccountEmail, input.getKeywords())
        );
        AccountInfo accountInfo = accountInfoMapper.selectOne(wrapper);
        if (accountInfo == null) {
            throw new ApplicationException(RetStubDetail.USER_NOT_FOUND);
        }

        // 比较用户状态
        compareUserStatus(accountInfo.getAccountStatus());

        // 比较用户密码是否正确
        comparePassword(accountInfo.getCreateTime(), accountInfo.getPassword(), input.getPassword());

        String token = UUID.randomUUID().toString();
        cacheService.setVal(String.format(Constants.CACHE_USER_SESSION_LOGIN_PREFIX, token), accountInfo.getId() + "," + 0, (long) oneWeek);

        return token;
    }

    private void checkoutCaptcha(LoginForm input) {
        // 验证输入的验证码是否正确
        if (!validate(input.getCaptcha(), input.getUuid())) {
            throw new ApplicationException(RetStubDetail.LOGIN_CAPTCHA_ERROR);
        }
        // 删除验证码
        cacheService.deleteVal(String.format(Constants.CACHE_LOGIN_CAPTCHA_PREFIX, input.getUuid()));
    }


    private boolean validate(String validateCode, String uuid) {
        String realCode = cacheService.getVal(String.format(Constants.CACHE_LOGIN_CAPTCHA_PREFIX, uuid));
        log.debug("uuid {}, validateCode:{}, correct:{}", uuid, validateCode.toUpperCase(), realCode);
        return validateCode.toUpperCase().equals(realCode);
    }

    private boolean validatePassword(Date createAt, String password, String inputPassword) {
        String salt = String.valueOf(createAt.getTime());
        String cryptedPassword = HashUtil.encryptUserPasswordByMD5(inputPassword, salt);
        log.info("cryptedPassword :{}, salt :{}", cryptedPassword, createAt.getTime());
        return StringUtils.equals(cryptedPassword, password);
    }

    public User getAuthInfo(Integer userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (null == sysUser) {
            throw new ApplicationException(RetStubDetail.USER_NOT_FOUND);
        }
        return createUserInfo(sysUser);
    }

    private User createUserInfo(SysUser userFromDb) {
        User user = new User();
        user.setId(userFromDb.getId().longValue());
        user.setUserNum(userFromDb.getId().toString());
        user.setUsername(userFromDb.getName());
        List<SysAuthOrdinalView> authViewList = sysAuthMapper.getAuthByUserId(userFromDb.getId());
        if (authViewList == null || authViewList.isEmpty()) {
            throw new ApplicationException(RetStubDetail.USER_AUTH_ERROR);
        }
        Map<String, String> authMap = authViewList.stream().collect(Collectors.toMap(SysAuthOrdinalView::getUri, SysAuthOrdinalView::getName));
        user.setAuthMap(authMap);
        user.setType(authViewList.get(0).getType());
        return user;
    }

    public boolean adminResetPassword(ResetPasswordForm passwordForm, User user) {
        Date now = new Date();

        SysUser userFromDb = sysUserMapper.selectByPrimaryKey(user.getId().intValue());
        if (userFromDb == null) {
            throw new ApplicationException(RetStubDetail.USER_NOT_FOUND);
        }

        // 比较用户密码是否正确
        comparePassword(userFromDb.getCreatedAt(), userFromDb.getPassword(), passwordForm.getOldPassword());

        String salt = String.valueOf(userFromDb.getCreatedAt().getTime());
        String newPassword = DigestUtils.md5DigestAsHex((passwordForm.getPassword() + salt).getBytes());

        SysUser userForDb = new SysUser();
        userForDb.setId(userFromDb.getId());
        userForDb.setPassword(newPassword);
        userForDb.setUpdatedAt(now);
        userForDb.setLastResetPasswordTime(now);
        sysUserMapper.updateByPrimaryKeySelective(userForDb);
        return true;
    }

    public boolean userResetPassword(ResetPasswordForm passwordForm, AccountInfo accountInfo) {
        Date now = new Date();

        AccountInfo userFromDb = accountInfoMapper.selectById(accountInfo.getId());
        if (userFromDb == null) {
            throw new ApplicationException(RetStubDetail.USER_NOT_FOUND);
        }

        // 比较用户密码是否正确
        comparePassword(userFromDb.getCreateTime(), userFromDb.getPassword(), passwordForm.getOldPassword());

        String salt = String.valueOf(userFromDb.getCreateTime().getTime());
        String newPassword = DigestUtils.md5DigestAsHex((passwordForm.getPassword() + salt).getBytes());

        AccountInfo userForDb = new AccountInfo();
        BeanUtils.copyProperties(userFromDb, userForDb);

        userForDb.setId(userFromDb.getId());
        userForDb.setPassword(newPassword);
        userForDb.setUpdateTime(now);

        accountInfoMapper.updateById(userForDb);
        return true;
    }

    public boolean adminLogout(HttpServletRequest request) {
        String header = request.getHeader(HEADER_MEMBER_TOKEN);
        if (header != null) {
            cacheService.deleteVal(String.format(Constants.CACHE_USER_SESSION_LOGIN_PREFIX, header));
        }
        return true;
    }

    public boolean userLogout(HttpServletRequest request) {
        String header = request.getHeader(HEADER_MEMBER_TOKEN);
        if (header != null) {
            cacheService.deleteVal(String.format(Constants.CACHE_USER_SESSION_LOGIN_PREFIX, header));
        }
        return true;
    }
}
