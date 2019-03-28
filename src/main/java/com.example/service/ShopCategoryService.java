package com.example.service;

import com.example.entity.ShopCategory;

import java.util.List;

/**
 * Created by z1271 on 2019/3/24.
 */
public interface ShopCategoryService {

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
