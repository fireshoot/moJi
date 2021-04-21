package com.osyangxin.dao.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * @类描述
 * @time 2021/4/21  21:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueryCondition {

    private String sort;

    private Integer priceGt;

    private Integer priceLte;

    private Long cid;

}
