package com.example.dao;

import com.example.entity.ProductCategory;

import java.util.List;

/**
 * Created by z1271 on 2019/3/27.
 */
public interface ProductCategoryDao {

    /**
     * 通过Id查询店铺
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
}
