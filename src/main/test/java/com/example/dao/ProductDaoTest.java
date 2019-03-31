package com.example.dao;

import com.example.BaseTest;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.dc.pr.PRError;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by z1271 on 2019/3/31.
 */
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;


    @Test
    public void testInsertProduct()throws Exception{
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        Product product=new Product();
        product.setProductName("测试1");
        product.setProductDesc("测试desc");
        product.setImgAddr("test");
        product.setPriority(1);
        product.setEnableStatus(1);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setShop(shop);
        product.setProductCategory(pc);
        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("desc2");
        product2.setImgAddr("test2");
        product2.setPriority(2);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop);
        product2.setProductCategory(pc);
        Product product3 = new Product();
        product3.setProductName("测试3");
        product3.setProductDesc("desc3");
        product3.setImgAddr("test2");
        product3.setPriority(2);
        product3.setEnableStatus(0);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop);
        product3.setProductCategory(pc);
        int effectedNum = productDao.insertProduct(product);
        assertEquals(1,effectedNum);
        effectedNum=productDao.insertProduct(product2);
        assertEquals(1,effectedNum);
        effectedNum=productDao.insertProduct(product3);
        assertEquals(1,effectedNum);

    }

}
