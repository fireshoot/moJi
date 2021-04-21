package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.OrderInfo;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}