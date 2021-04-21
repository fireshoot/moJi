package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.ProductDoinfo;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface ProductDoinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDoinfo record);

    int insertSelective(ProductDoinfo record);

    ProductDoinfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductDoinfo record);

    int updateByPrimaryKey(ProductDoinfo record);
}