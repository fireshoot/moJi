package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.ProductCategoryInfo;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface ProductCategoryInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductCategoryInfo record);

    int insertSelective(ProductCategoryInfo record);

    ProductCategoryInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductCategoryInfo record);

    int updateByPrimaryKey(ProductCategoryInfo record);
}