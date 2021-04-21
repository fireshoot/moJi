package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.ProductSwipeImageInfo;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface ProductSwipeImageInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSwipeImageInfo record);

    int insertSelective(ProductSwipeImageInfo record);

    ProductSwipeImageInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductSwipeImageInfo record);

    int updateByPrimaryKey(ProductSwipeImageInfo record);
}