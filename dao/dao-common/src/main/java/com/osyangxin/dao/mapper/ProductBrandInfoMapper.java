package com.osyangxin.dao.mapper;

import com.osyangxin.dao.model.ProductBrandInfo;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface ProductBrandInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductBrandInfo record);

    int insertSelective(ProductBrandInfo record);

    ProductBrandInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductBrandInfo record);

    int updateByPrimaryKey(ProductBrandInfo record);
}