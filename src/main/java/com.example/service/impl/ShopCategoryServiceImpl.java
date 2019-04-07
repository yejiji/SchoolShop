package com.example.service.impl;

import com.example.dao.ShopCategoryDao;
import com.example.entity.ShopCategory;
import com.example.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by z1271 on 2019/3/24.
 */

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return shopCategoryDao.queryShopCategory(shopCategory);
    }

    @Override
    public List<ShopCategory> getFirstLevelShopCategoryList() {
        return null;
    }
}
