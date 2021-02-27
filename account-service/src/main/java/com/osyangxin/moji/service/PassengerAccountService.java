/*
package com.osyangxin.moji.accountservice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.osyangxin.moji.accountservice.constant.AccountConstants;
import com.osyangxin.moji.accountservice.input.UserInfo;
import com.osyangxin.moji.accountservice.msg.RetStubDetail;
import com.osyangxin.moji.accountservice.util.CaptchaGenerator;
import com.osyangxin.moji.common.exception.ApplicationException;
import com.osyangxin.moji.component.bean.Condition;
import com.osyangxin.moji.component.service.CacheService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

*/
/**
 * @since 2019/07/22 10:57
 *//*

@Slf4j
@Service
public class PassengerAccountService {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private ReportProducer reportProducer;
    @Autowired
    private WalletStatisticalInfoMapper walletStatisticalInfoMapper;

    private static final String PHONE_FOR_AUDIT = "18162793481";


    @Value("${wechat-applet.APPID}")
    private String WECHAT_APPID;
    @Value("${wechat-applet.APPSECRET}")
    private String WECHAT_APPSECRET;

    public boolean sendCaptcha(Short tag, String phoneNum) {
        if (Objects.equals(PHONE_FOR_AUDIT, phoneNum)) {
            String key = String.format(AccountConstants.CACHE_LOGIN_CAPTCHA_PREFIX, PHONE_FOR_AUDIT);
            String val = "123456";
            cacheService.setVal(key, val);
            return true;
        }

        String captchaKey = String.format(AccountConstants.CACHE_LOGIN_CAPTCHA_PREFIX, phoneNum);
        String captchaVal = CaptchaGenerator.genDigitalCaptcha(AccountConstants.CAPTCHA_LENGTH);

        boolean done = smsService.sendCaptcha(tag, phoneNum, captchaVal);
        if (done)
            cacheService.setVal(captchaKey, captchaVal, AccountConstants.CAPTCHA_EXPIRE_TIME);
        return done;
    }

    */
/**
     * maas sso 微信小程序跳转登陆
     * @param maasToken
     * @param wechatCode
     * @return
     *//*

    public Map<String, String> login(String maasToken) {
        //1. 根据maasToken获取Maas用户信息
        UserInfo maasUserInfo = getMaasUserInfo(maasToken);
        Integer maasId = maasUserInfo.getMaasId();
        String mobile = maasUserInfo.getPhoneNum();
        if (maasId == null || mobile == null) {
            throw new ApplicationException(RetStubDetail.MAAS_CUSTOMER_INFO_INVALID);
        }
        //2. 调用微信接口获取openid
        String openId;
        //请求openid
        JSONObject response = requestGetWechatSession(wechatCode, WECHAT_APPID, WECHAT_APPSECRET);
        if ((response.getInteger("errcode") != null) && (response.getInteger("errcode") != 0)) {
            log.error("jscode2session response code is: " + response.getInteger("errcode"));
            throw new ApplicationException(RetStubDetail.WECHAT_REGISTER_FAIL);
        }
        openId = response.getString("openid");
        if (openId == null) {
            log.error("jscode2session's openid is null");
            throw new ApplicationException(RetStubDetail.WECHAT_REGISTER_FAIL);
        }
        //3. 根据maas_id判断用户是否存在鹏程的用户表里,来生成/更新用户
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new Condition("maas_id", maasId));
        conditionList.add(new Condition("deleted", DeletedStatus.DELETED_NO.getStatus()));
        List<MemberInfo> memberInfosByMaasId = memberInfoMapper.getByConditionList(conditionList);
        MemberInfo memberInfo;
        // 3.1 找到多条符合要求的信息，抛出异常
        if (memberInfosByMaasId.size() > 1) {
            throw new ApplicationException(RetStubDetail.DUPLICATE_MAAS_ID_FOUND);
        }
        // 3.2 找到一条符合要求的信息
        else if (memberInfosByMaasId.size() == 1) {
            memberInfo = memberInfosByMaasId.get(0);
            // 3.2.1 本地电话与Maas SSO电话信息一致，更新openid
            if (memberInfo.getPhoneNumber().equals(mobile)) {
                memberInfo.setWxOpenId(openId);
                memberInfo.setUpdatedAt(new Date());
                memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
            }
            // 3.2.2 本地电话与Maas SSO电话信息不一致，更新本地电话及openid
            else {
                memberInfo.setWxOpenId(openId);
                memberInfo.setPhoneNumber(mobile);
                memberInfo.setUpdatedAt(new Date());
                memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
            }

        }
        // 3.3 没有找到maas_id匹配的信息，再根据手机号匹配
        else {
            conditionList.clear();
            conditionList.add(new Condition("phone_number",mobile));
            conditionList.add(new Condition("deleted", DeletedStatus.DELETED_NO.getStatus()));
            List<MemberInfo> memberInfosByMaasMobile = memberInfoMapper.getByConditionList(conditionList);
            // 3.3.1 找到多条符合要求的信息，抛出异常
            if (memberInfosByMaasMobile.size() > 1) {
                throw new ApplicationException(RetStubDetail.DUPLICATE_MAAS_MOBILE_FOUND);
            }
            // 3.3.2 找到一条符合要求的信息,更新信息，将maas id写入
            else if(memberInfosByMaasMobile.size() ==1){
                memberInfo = memberInfosByMaasMobile.get(0);
                memberInfo.setWxOpenId(openId);
                memberInfo.setMaasId(maasId);
                memberInfo.setUpdatedAt(new Date());
                memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
            }
            // 3.3.3 没有找到maas_id和手机号匹配的用户，生成新用户
            else{
                memberInfo = new MemberInfo();
                memberInfo.setMaasId(maasId);
                memberInfo.setWxOpenId(openId);
                memberInfo.setChannel(ChannelType.MAAS_SSO.getStatus());
                memberInfo.setPhoneNumber(mobile);
                memberInfo.setCreatedAt(new Date());
                memberInfo.setUpdatedAt(new Date());
                memberInfo.setDeleted(DeletedStatus.DELETED_NO.getStatus());
                memberInfoMapper.insertSelective(memberInfo);
            }

        }
        //创建用户钱包
        createWalletForMember(memberInfo);
        //生成token
        String token = generateToken(LoginType.WECHAT_APPLET, memberInfo);

        Map<String, String> result = new HashMap<>(2);
        result.put("phoneNum", mobile);
        result.put("token", token);
        return result;
    }


    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> login(PassengerLoginForm loginForm, LoginType loginType) {
        String phoneNum = loginForm.getPhoneNum();
        String captcha = loginForm.getCaptcha();

        String captchaKey = String.format(AccountConstants.CACHE_LOGIN_CAPTCHA_PREFIX, phoneNum);
        String captchaValStored = cacheService.getVal(captchaKey);

        if (captchaValStored == null) {
            throw new ApplicationException(RetStubDetail.PASSENGER_CAPTCHA_EXPIRED);
        }

        if (!captchaValStored.equals(captcha)) {
            throw new ApplicationException(RetStubDetail.PASSENGER_CAPTCHA_WRONG);
        }

        if (!Objects.equals(phoneNum, PHONE_FOR_AUDIT)) {
            cacheService.deleteVal(captchaKey);
        }

        String openId = null;
        Date now = new Date();
        if (loginType.equals(LoginType.WECHAT_APPLET)) {
            //请求openid
            JSONObject response = requestGetWechatSession(loginForm.getWechatCode(), WECHAT_APPID, WECHAT_APPSECRET);
            if ((response.getInteger("errcode") != null) && (response.getInteger("errcode") != 0)) {
                log.error("jscode2session response code is: " + response.getInteger("errcode"));
                throw new ApplicationException(RetStubDetail.WECHAT_REGISTER_FAIL);
            }
            openId = response.getString("openid");
            if (openId == null) {
                log.error("WTF!!! jscode2session's openid is null");
                throw new ApplicationException(RetStubDetail.WECHAT_REGISTER_FAIL);
            }
        }

        MemberInfo memberInfo = getMemberInfoByPhoneNum(phoneNum);
        Integer memberId = memberInfo == null ? null : memberInfo.getId();

        if (memberInfo == null) {
            memberInfo = new MemberInfo();
            if (loginType.equals(LoginType.WECHAT_APPLET)) {
                memberInfo.setWxOpenId(openId);
            }
            memberInfo.setPhoneNumber(phoneNum);
            memberInfo.setCreatedAt(now);
            memberInfo.setUpdatedAt(now);
            memberInfo.setDeleted(DeletedStatus.DELETED_NO.getStatus());
            memberInfoMapper.insertSelective(memberInfo);
            */
/**
             * 插入成功，发送数据
             * *//*

            sendMemberData(memberInfo, OperationFlag.ADD.getStatus());
            */
/**
             * 插入成功，更新数据中心统计redis值
             * *//*

            updateDataCentreCache();
        } else if (loginType.equals(LoginType.WECHAT_APPLET)) {
            MemberInfo memberInfoToDB = new MemberInfo();
            memberInfoToDB.setId(memberId);
            memberInfoToDB.setWxOpenId(openId);
            memberInfoToDB.setUpdatedAt(now);
            memberInfoMapper.updateByPrimaryKeySelective(memberInfoToDB);
            */
/**
             * 更新成功，发送数据
             *  sendMemberData(memberInfoToDB, OperationFlag.UPDATE.getStatus());
             * *//*

        }

        createWalletForMember(memberInfo);

        // 根据userId查询缓存中的token
        String val = cacheService.getVal(String
                .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.PASSENGER.name(),
                        loginType.name(), memberInfo.getId()));
        //如果缓存中存在则删除
        if (null != val) {
            cacheService.deleteVal(String
                    .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.PASSENGER.name(),
                            loginType.name(), memberInfo.getId()));
            cacheService.deleteVal(String.format(Constants.CACHE_TOKEN_USER_PREFIX, val));
        }

        String token = StringUtil.generatorRandomEncryptSequence();

        cacheService.setVal(
                String.format(Constants.CACHE_TOKEN_USER_PREFIX, token),
                memberInfo.getId().toString() + "," + AccountType.PASSENGER.name() + "," + loginType.name()
        );
        cacheService.setVal(
                String.format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.PASSENGER.name(), loginType.name(), memberInfo.getId()),
                token
        );
        Map<String, String> result = new HashMap<>(16);
        result.put("phoneNum", phoneNum);
        result.put("token", token);
        return result;
    }

    private MemberInfo getMemberInfoByPhoneNum(String phoneNum) {
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new Condition("phone_number", phoneNum));
        conditionList.add(new Condition("deleted", DeletedStatus.DELETED_NO.getStatus()));
        List<MemberInfo> memberInfoList = memberInfoMapper.getByConditionList(conditionList);
        if (memberInfoList != null && memberInfoList.size() == 1) {
            return memberInfoList.get(0);
        } else {
            return null;
        }
    }

    private JSONObject requestGetWechatSession(String code, String appid, String appsecret) {
        if (StringUtils.isEmpty(code)) {
            throw new ApplicationException(RetStubDetail.WECHAT_CODE_NULL);
        }
        if (StringUtils.isEmpty(appid)) {
            throw new ApplicationException(RetStubDetail.WECHAT_APPID_NULL);
        }
        if (StringUtils.isEmpty(appsecret)) {
            throw new ApplicationException(RetStubDetail.WECHAT_APPSECRET_NULL);
        }

        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session");
        url.append("?appid=").append(appid);
        url.append("&secret=").append(appsecret);
        url.append("&js_code=").append(code);
        url.append("&grant_type=authorization_code");

        String response;
        try {
            response = HttpInvoker.getRestTemplate().getForObject(url.toString(), String.class);
        } catch (Exception e) {
            throw new ApplicationException(RetStubDetail.WECHAT_NETWORK_FAIL);
        }

        log.info("wechat request url is {}", url.toString());
        log.info("wechat jscode2session response is {}", response);

        return JSON.parseObject(response);
    }

    private void sendMemberData(MemberInfo memberInfo, int type) {
        PassengerInfo passengerInfo = new PassengerInfo();
        BeanUtils.copyProperties(memberInfo, passengerInfo);
        Date now = new Date();
        DateTimeFormatter sdf3 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        passengerInfo.setFlag(type);
        passengerInfo.setUpdateTime(sdf3.format(DateUtils.dateToLocalDateTime(now)));
        passengerInfo.setCompanyId(Constants.COMPANYID);

        SysThreadPool.doExecute(() -> {
            JSONObject json = (JSONObject) JSON.toJSON(passengerInfo);
            ReportMqMsg msg = ReportMqMsg.pushPassengerInformationRated(json);
            reportProducer.sendMqMsg(msg);
        });
    }

    private void updateDataCentreCache() {
        SysThreadPool.doExecute(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            cacheService.hashIncrOne(
                    String.format(Constants.DATACENTRE_KID_PREFIX,sdf.format(new Date())),
                    DataCentreCache.REGISTER.getStatus());

            log.info("redis自增成功,相应数值{}",DataCentreCache.REGISTER.getStatus());

        });
    }

    private void createWalletForMember(MemberInfo memberInfo) {
        if (memberInfo.getWalletId() == null) {
            WalletStatisticalInfo wallet = getEmptyWallet();
            memberInfo.setWalletId(wallet.getId());
            memberInfoMapper.updateByPrimaryKeySelective(memberInfo);
        }
    }

    private WalletStatisticalInfo getEmptyWallet() {
        WalletStatisticalInfo wallet = new WalletStatisticalInfo();
        wallet.setRemainingAmount(BigDecimal.ZERO);
        wallet.setTotalRechargeAmount(BigDecimal.ZERO);
        wallet.setTotalDeductionAmount(BigDecimal.ZERO);
        wallet.setTotalGiftAmount(BigDecimal.ZERO);
        wallet.setDeleted(DeletedStatus.DELETED_NO.getStatus());
        Date now = new Date();
        wallet.setCreatedAt(now);
        wallet.setUpdatedAt(now);
        walletStatisticalInfoMapper.insertSelective(wallet);
        return wallet;
    }

    private UserInfo getMaasUserInfo(String maasToken) {
        JSONObject info = AccountUtil.getUserInfo(headerFrom, maasToken);
        UserInfo userInfo = new UserInfo();
        userInfo.setMaasId(info.getInteger("maasId"));
        userInfo.setPhoneNum(info.getString("phoneNum"));
        return userInfo;
    }

    private String generateToken(LoginType loginType,MemberInfo memberInfo){
        // 根据userId查询缓存中的token
        String val = cacheService.getVal(String
                .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.PASSENGER.name(),
                        loginType.name(), memberInfo.getId()));
        //如果缓存中存在则删除
        if (null != val) {
            cacheService.deleteVal(String
                    .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.PASSENGER.name(),
                            loginType.name(), memberInfo.getId()));
            cacheService.deleteVal(String.format(Constants.CACHE_TOKEN_USER_PREFIX, val));
        }

        String token = StringUtil.generatorRandomEncryptSequence();

        cacheService.setVal(
                String.format(Constants.CACHE_TOKEN_USER_PREFIX, token),
                memberInfo.getId().toString() + "," + AccountType.PASSENGER.name() + "," + loginType.name()
        );
        cacheService.setVal(
                String.format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.PASSENGER.name(), loginType.name(), memberInfo.getId()),
                token
        );
        return token;
    }
}
*/
