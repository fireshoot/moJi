package com.osyangxin.dao.mapper;

import cn.hutool.core.collection.CollectionUtil;
import com.osyangxin.dao.model.ProductInfo;
import com.osyangxin.dao.req.ProductQueryCondition;
import java.util.List;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    List<ProductInfo> selectByCondition(ProductQueryCondition condition);

    default ProductInfo selectByConditionOne(ProductQueryCondition condition) {
        List<ProductInfo> productInfos = selectByCondition(condition);
        if (CollectionUtil.isEmpty(productInfos)) {
            return null;
        }
        return productInfos.get(0);
    }
}