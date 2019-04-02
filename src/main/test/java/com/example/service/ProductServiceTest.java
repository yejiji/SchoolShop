package com.example.service;

import com.example.BaseTest;
import com.example.dto.ImageHolder;
import com.example.dto.ProductExecution;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.entity.Shop;
import com.example.enums.ProductStateEnum;
import com.example.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by z1271 on 2019/4/2.
 */
public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct()throws ShopOperationException,FileNotFoundException{
        Product product = new Product();
        Shop shop= new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试小天");
        product.setProductDesc("weiweiwei");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        File thumnaileFile = new File("E:/webpic/jiaxin.jpg");
        InputStream is = new FileInputStream(thumnaileFile);
        ImageHolder thumnaile = new ImageHolder(thumnaileFile.getName(),is);

        File ProductImg1 = new File("E:/webpic/jiaxin.jpg");
        InputStream is1 = new FileInputStream(ProductImg1);
        File productImg2 =new File("E:/webpic/logo.png");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(ProductImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        ProductExecution pe = productService.addProduct(product,thumnaile,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }
}
