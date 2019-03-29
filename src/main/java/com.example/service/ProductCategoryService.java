package com.example.service;

import com.example.dto.ProductCategoryExecution;
import com.example.entity.ProductCategory;
import com.example.enums.ProductCategoryStateEnum;
import com.example.exceptions.ProductCategoryOperationException;

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


    ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategories)
            throws ProductCategoryOperationException;
}
