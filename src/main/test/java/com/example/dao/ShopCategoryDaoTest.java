package com.example.dao;

import com.example.BaseTest;
import com.example.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by z1271 on 2019/3/23.
 */
public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testqueryShopCategory(){
        List<ShopCategory> areaList = shopCategoryDao.queryShopCategory(new ShopCategory());
//        assertEquals(2,areaList.size());
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        testCategory.setParent(parentCategory);
        areaList = shopCategoryDao.queryShopCategory(testCategory);
        assertEquals(1,areaList.size());
        System.out.println(areaList.get(0).getShopCategoryName());
    }

}
