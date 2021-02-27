/*
package com.osyangxin.moji.accountservice.service;

import com.alibaba.fastjson.JSONObject;
import com.haylion.maastaxi.bean.Condition;
import com.haylion.maastaxi.bean.Sort;
import com.haylion.maastaxi.common.constans.AccountType;
import com.haylion.maastaxi.common.constans.CargoPassengerServiceStatus;
import com.haylion.maastaxi.common.constans.Constants;
import com.haylion.maastaxi.common.constans.DataCentreCache;
import com.haylion.maastaxi.common.constans.DeletedStatus;
import com.haylion.maastaxi.common.constans.LoginType;
import com.haylion.maastaxi.common.constans.OnlineStatus;
import com.haylion.maastaxi.common.constans.RequestingOrderStatus;
import com.haylion.maastaxi.common.constans.VehicleStatus;
import com.haylion.maastaxi.common.exception.ApplicationException;
import com.haylion.maastaxi.common.utils.HashUtil;
import com.haylion.maastaxi.common.utils.StringUtil;
import com.osyangxin.dao.mapper.DriverInfoMapper;
import com.osyangxin.dao.mapper.DriverOnlineStatusMapper;
import com.osyangxin.dao.mapper.DriverSettingsMapper;
import com.osyangxin.dao.mapper.DriverVehicleRelationMapper;
import com.osyangxin.dao.mapper.VehicleInfoMapper;
import com.osyangxin.dao.model.DriverInfo;
import com.osyangxin.dao.model.DriverOnlineStatus;
import com.osyangxin.dao.model.DriverSettings;
import com.osyangxin.dao.model.DriverVehicleRelation;
import com.osyangxin.dao.model.VehicleInfo;
import com.haylion.maastaxi.http.HttpInvoker;
import com.haylion.maastaxi.msg.RetStubDetail;
import com.haylion.maastaxi.output.SimpleVehicleInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

*/
/**
 * @author Cris
 * @description 司机账号服务类
 * @date 2019/7/22
 *//*

@Service
@Slf4j
@EnableTransactionManagement
public class AccountService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private DriverInfoMapper driverInfoMapper;

    @Autowired
    private VehicleInfoMapper vehicleInfoMapper;

    @Autowired
    private DriverOnlineStatusMapper driverOnlineStatusMapper;

    @Autowired
    private DriverSettingsMapper driverSettingsMapper;

    @Autowired
    private DriverVehicleRelationMapper driverVehicleRelationMapper;

    @Value("${stopRequestingOrderUrl}")
    private String stopRequestingOrderUrl;

    private static final Integer DEFAULT_VEHICLE_ID = -1;

    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    */
/**
     * 司机端登陆
     *
     * @param code                 司机工号
     * @param md5EncryptedPassword MD5加密后的密码
     * @return 司机登陆token
     *//*

    @Transactional
    public Map<String, Object> driverLogin(String code, String md5EncryptedPassword) {
        Map<String, Object> map = new HashMap<>(4);
        //根据司机工号查询司机信息
        logger.info("starting query driver information for driver code: {}", code);
        DriverInfo driverInfo = findDriverByCode(code);
        Integer userId = driverInfo.getId();
        String storedPassword = driverInfo.getPassword();
        Date createDate = driverInfo.getCreatedAt();
        String inputPassword = HashUtil.encryptUserPasswordByMD5(md5EncryptedPassword, String.valueOf(createDate.getTime()));
        logger.info("starting check driver password");
        //密码判断
        if (!inputPassword.equals(storedPassword)) {
            throw new ApplicationException(RetStubDetail.PASSWORD_ERROR);
        }
        //判断司机是否离职
        if (driverInfo.getStatus() == 0) {
            throw new ApplicationException(RetStubDetail.DRIVER_ALREADY_RESIGNED);
        }
        logger.info("starting checking token");
        //根据userId查询缓存中的token
        String val = cacheService.getVal(String
                .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.DRIVER.name(), LoginType.APP.name(), userId));
        //如果缓存中存在则删除
        if (null != val) {
            logger.info("token already exists, delete old token {}", val);
            cacheService.deleteVal(String
                    .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.DRIVER.name(), LoginType.APP.name(), userId));
            cacheService.deleteVal(String.format(Constants.CACHE_TOKEN_USER_PREFIX, val));
        }
        //生成token
        logger.info("generating new  token");
        String token = StringUtil.generatorRandomEncryptSequence();
        cacheService.setVal(String.format(Constants.CACHE_TOKEN_USER_PREFIX, token),
                userId + "," + AccountType.DRIVER.name() + "," + LoginType.APP.name());
        cacheService.setVal(String.format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.DRIVER.name(),
                LoginType.APP.name(), userId), token);
        logger.info("new token generated: {}", token);
        //存储第一次心跳信息
        generateInitialHeartBeat(token);
        //更新司机听单状态
        DriverOnlineStatus driverOnlineStatus = updateOnlineStatus(userId, token);

        //判断听单是否创建了默认的听单设置数据，如果没有创建默认值
        logger.info("checking if driver online status exists");
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new Condition("driver_id", userId));
        conditionList.add(new Condition("deleted", 0));
        List<DriverSettings> driverSettingsList = driverSettingsMapper.getByConditionList(conditionList);
        //找到多条司机听单设置记录，返回异常
        if (driverSettingsList.size() > 1) {
            throw new ApplicationException(RetStubDetail.MULTIPLE_DRIVER_SETTINGS_FOUND);
        }
        //没有找到司机听单配置，生成默认数据，插入数据库
        else if (driverSettingsList.size() == 0) {
            DriverSettings driverSettings = new DriverSettings();
            driverSettings.setDriverId(userId);
            driverSettings.setCargoPassengerServiceStatus(CargoPassengerServiceStatus.ON.getStatus());
            driverSettings.setCreatedAt(new Date());
            driverSettingsMapper.insertSelective(driverSettings);
            logger.info("No driver settings found, insert a new record with default values");
        }
        logger.info("driver settings found, do nothing");
        map.put("latestUsedVehicleId", driverOnlineStatus.getVehicleId());
        map.put("token", token);
        return map;
    }

    */
/**
     * 司机端修改密码
     *
     * @param driverId             司机id
     * @param oldEncryptedPassword MD5加密后的原密码
     * @param newEncryptedPassword MD5加密后的新密码
     * @return 司机工号
     *//*

    public String driverChangePassword(Integer driverId, String oldEncryptedPassword, String newEncryptedPassword) {
        logger.info("driver is changing password");
        //根据司机工号查询司机信息
        DriverInfo driverInfo = driverInfoMapper.selectByPrimaryKey(driverId);
        if (null == driverInfo) {
            throw new ApplicationException(RetStubDetail.DRIVER_NOT_FOUND);
        }
        Date createDate = driverInfo.getCreatedAt();
        String storedOldPassword = driverInfo.getPassword();
        String inputPassword = HashUtil.encryptUserPasswordByMD5(oldEncryptedPassword, String.valueOf(createDate.getTime()));
        //密码错误 抛出异常
        if (!inputPassword.equals(storedOldPassword)) {
            throw new ApplicationException(RetStubDetail.PASSWORD_ERROR);
        }
        //密码正确 修改密码
        else {
            //1.加密后新密码不能为空
            if (null == newEncryptedPassword) {
                throw new ApplicationException(RetStubDetail.DRIVER_NEW_PASSWORD_CAN_NOT_BE_NULL);
            }
            //2.加密后新密码不仅仅由数字和密码组成
            else if (!isLetterDigit(newEncryptedPassword)) {
                throw new ApplicationException(RetStubDetail.DRIVER_NEW_PASSWORD_FORMAT_ERROR);
            }
            String newStoredPassword = HashUtil.encryptUserPasswordByMD5(newEncryptedPassword, String.valueOf(createDate.getTime()));
            driverInfo.setPassword(newStoredPassword);
            driverInfo.setUpdatedAt(new Date());
            driverInfoMapper.updateByPrimaryKeySelective(driverInfo);
        }
        logger.info("driver changed password");
        return driverInfo.getCode();
    }


    */
/**
     * 司机端登出
     *
     * @param driverId 司机id
     * @return 司机id
     *//*

    public Integer driverLogout(Integer driverId) {
        logger.info("driver logging out");
        //根据userId查询缓存中的token
        String val = cacheService.getVal(String
                .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.DRIVER.name(), LoginType.APP.name(), driverId));
        if (null == val) {
            throw new ApplicationException(RetStubDetail.DRIVER_NOT_LOGIN);
        }
        //司机登出，停止停单
        logger.info("logging out, delete token {}", val);
        stopRequestingOrders(val);
        cacheService.deleteVal(String
                .format(Constants.CACHE_USER_TOKEN_LOGIN_PREFIX, AccountType.DRIVER.name(), LoginType.APP.name(), driverId));
        cacheService.deleteVal(String.format(Constants.CACHE_TOKEN_USER_PREFIX, val));

        logger.info("driver logged out");
        return driverId;
    }

    */
/**
     * 更新司机听单状态
     *
     * @param driverId
     * @return
     *//*

    public DriverOnlineStatus updateOnlineStatus(Integer driverId, String token) {
        logger.info("updating driver requesting order status");
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new Condition("driver_id", driverId));
        conditionList.add(new Condition("deleted", DeletedStatus.DELETED_NO.getStatus()));
        List<DriverOnlineStatus> driverOnlineStatusList = driverOnlineStatusMapper.getByConditionList(conditionList);
        DriverOnlineStatus onlineStatus = new DriverOnlineStatus();
        //3.如果没有找到听单状态的记录，写入未听单记录
        if (driverOnlineStatusList == null || driverOnlineStatusList.size() <= 0) {
            onlineStatus.setDriverId(driverId);
            onlineStatus.setVehicleId(DEFAULT_VEHICLE_ID);
            onlineStatus.setOnlineStatus(OnlineStatus.OFF_LINE.getType());
            onlineStatus.setCreatedAt(new Date());
            driverOnlineStatusMapper.insertSelective(onlineStatus);
            */
/**
             * 插入成功，更新数据中心统计redis值
             * *//*

            updateLoginedCache();
        }
        //1.如果找到多条听单记录，抛出异常
        else if (driverOnlineStatusList.size() > 1) {
            throw new ApplicationException(RetStubDetail.MULTIPLE_DRIVER_REQUESTING_RECORDS_FOUND);
        }
        //2.如果找到1条听单
        else {
            onlineStatus = driverOnlineStatusList.get(0);
            onlineStatus.setId(onlineStatus.getId());
            onlineStatus.setUpdatedAt(new Date());
            onlineStatus.setStartTime(null);
            onlineStatus.setEndTime(null);
            onlineStatus.setDriverId(driverId);
            //未听单
            if (onlineStatus.getOnlineStatus().equals(RequestingOrderStatus.NOT_REQUESTING.getStatus())) {
                logger.info("driver is not requesting order");
                driverOnlineStatusMapper.updateByPrimaryKeySelective(onlineStatus);
            }
            //听单中
            else if (onlineStatus.getOnlineStatus().equals(RequestingOrderStatus.REQUESTING.getStatus())) {
                logger.info("driver is requesting order, stop requesting order");
                stopRequestingOrders(token);
                onlineStatus.setOnlineStatus(RequestingOrderStatus.NOT_REQUESTING.getStatus());
                driverOnlineStatusMapper.updateByPrimaryKeySelective(onlineStatus);
            } else {
                throw new ApplicationException(RetStubDetail.UNKNOWN_DRIVER_REQUESTING_STATUS);
            }
        }

        logger.info("end of updating driver online status");
        return onlineStatus;
    }

    */
/**
     * 根据司机工号获得司机所绑定的车辆
     *
     * @param code 司机工号
     * @return
     *//*

    public List<SimpleVehicleInfo> findVehicleByCode(String code) {
        logger.info("find vehicles by driver code");
        DriverInfo driverInfo = findDriverByCode(code);
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new Condition("driver_id", driverInfo.getId()));
        List<DriverVehicleRelation> driverVehicleRelationList = driverVehicleRelationMapper.getByConditionList(conditionList);
        if (null != driverVehicleRelationList && driverVehicleRelationList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            driverVehicleRelationList.stream().forEach(driverVehicleRelation -> sb.append(driverVehicleRelation.getVehicleId()).append(","));
            sb.delete(sb.toString().length() - 1, sb.toString().length());
            sb.append(")");
            conditionList.clear();
            conditionList.add(new Condition("id", "in", sb.toString()));
            conditionList.add(new Condition("status", VehicleStatus.VEHICLE_YES.getStatus()));
            conditionList.add(new Condition("deleted", DeletedStatus.DELETED_NO.getStatus()));
        } else {
            logger.info("Driver {} did not bind any vehicle", driverInfo.getId());
            return new ArrayList<>();
        }
        List<VehicleInfo> vehicleInfoList = vehicleInfoMapper.getSortedResultByOriginalConditionList(conditionList, new Sort("id", "asc"));
        List<SimpleVehicleInfo> simpleVehicleInfoList = new ArrayList<>();
        vehicleInfoList.stream().forEach(vehicleInfo -> {
            SimpleVehicleInfo simpleVehicleInfo = new SimpleVehicleInfo();
            simpleVehicleInfo.setId(vehicleInfo.getId());
            simpleVehicleInfo.setNumber(vehicleInfo.getNumber());
            simpleVehicleInfoList.add(simpleVehicleInfo);
        });
        logger.info("found following vehicles: {}", simpleVehicleInfoList.toString());
        return simpleVehicleInfoList;
    }

    private DriverInfo findDriverByCode(String code) {
        logger.info("find driver by driver code");
        List<Condition> conditionList = new ArrayList<>();
        conditionList.add(new Condition("code", code));
        conditionList.add(new Condition("deleted", DeletedStatus.DELETED_NO.getStatus()));
        List<DriverInfo> driverInfoList = driverInfoMapper.getByConditionList(conditionList);
        //未找到司机
        if (null == driverInfoList || driverInfoList.size() == 0) {
            throw new ApplicationException(RetStubDetail.DRIVER_NOT_FOUND);
        }
        //找到多个拥有相同code的司机
        else if (driverInfoList.size() > 1) {
            throw new ApplicationException(RetStubDetail.MULTIPLE_DRIVER_WITH_SAME_CODE);
        } else {
            DriverInfo driverInfo = driverInfoList.get(0);
            if (null == driverInfo.getCreatedAt()) {
                throw new ApplicationException(RetStubDetail.DRIVER_ACCOUNT_CREATED_DATE_IS_NULL);
            }
            logger.info("found driver name: {}, id: {}", driverInfo.getName(), driverInfo.getId());
            return driverInfo;
        }
    }

    private void stopRequestingOrders(String token) {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        headers.put("token", Collections.singletonList(token));
        JSONObject jsonObject = new JSONObject();
        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
        RestTemplate restTemplate = HttpInvoker.getRestTemplate();
        logger.info("starting stop requesting order request: url->{}, request-> {}", stopRequestingOrderUrl, request.toString());
        ResponseEntity<String> response = restTemplate.postForEntity(stopRequestingOrderUrl, request, String.class);
        logger.info("stop requesting order response: {}", response.toString());
    }

    */
/**
     * 判断字符串是否仅由数字和字母组成
     *
     * @param str
     * @return
     *//*

    private boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }

    private void generateInitialHeartBeat(String token) {
        Long timestamp = System.currentTimeMillis();
        cacheService.putToMap(Constants.HEART_BEAT_TIMESTAMP, token, timestamp, 10 * 60 * 1000L);
        logger.info("update initial heart beat for token {}, timestamp {}", token, timestamp);
    }

    public void updateLoginedCache(){
        SysThreadPool.doExecute(()->{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            cacheService.hashIncrOne(
                    String.format(Constants.DATACENTRE_DRIVER_PREFIX,sdf.format(new Date())),
                    DataCentreCache.LOGINED.getStatus());

            log.info("redis自增成功,相应数值{}",DataCentreCache.LOGINED.getStatus());
        });
    }
}

*/
