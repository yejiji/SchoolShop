package com.example.dao;

import com.example.BaseTest;
import com.example.entity.Area;
import com.example.entity.PersonInfo;
import com.example.entity.Shop;
import com.example.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by z1271 on 2019/3/19.
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;


    @Test
    public void testQueryShopList(){
        Shop shopcondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopcondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopcondition,0,5);
        int count = shopDao.queryShopCount(shopcondition);
        System.out.println("店铺列表大小:"+shopList.size());
        System.out.println("店铺数:"+count);
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(2L);
        shopcondition.setShopCategory(sc);
        shopList = shopDao.queryShopList(shopcondition,0,1);
        count = shopDao.queryShopCount(shopcondition);
        System.out.println("带category:"+shopList.size());
        System.out.println("带category条数:"+count);



    }

    @Test
    @Ignore
    public void testQueryByShopId(){
        long shopId=1;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:"+shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
    }

    @Test
    @Ignore
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner =new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("吞坤");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("114514");
        shop.setShopImg("sd");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(3L);
        shop.setShopDesc("小僧烤肉");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }
}
