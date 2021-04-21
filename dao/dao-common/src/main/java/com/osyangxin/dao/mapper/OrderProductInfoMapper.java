package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.OrderProductInfo;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface OrderProductInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderProductInfo record);

    int insertSelective(OrderProductInfo record);

    OrderProductInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderProductInfo record);

    int updateByPrimaryKey(OrderProductInfo record);
}