package com.example.service;

import com.example.entity.ProductCategory;

import java.util.List;

/**
 * Created by z1271 on 2019/3/28.
 */

public interface ProductCategoryService {
    /**
     * 指定查询某个店铺下所有商品类别信息
     * @param shopId
     * @return
     */

    List<ProductCategory> queryProductCategoryList(long shopId);
}
