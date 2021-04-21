package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.ProductOpsRecordHistoryInfo;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface ProductOpsRecordHistoryInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductOpsRecordHistoryInfo record);

    int insertSelective(ProductOpsRecordHistoryInfo record);

    ProductOpsRecordHistoryInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductOpsRecordHistoryInfo record);

    int updateByPrimaryKey(ProductOpsRecordHistoryInfo record);
}