package com.example.service;

import com.example.BaseTest;
import com.example.dto.ImageHolder;
import com.example.dto.ShopExecution;
import com.example.entity.Area;
import com.example.entity.PersonInfo;
import com.example.entity.Shop;
import com.example.entity.ShopCategory;
import com.example.enums.ShopStateEnum;
import com.example.exceptions.ShopOperationException;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by z1271 on 2019/3/21.
 */
public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;


    @Test
    public void testGetShopList(){
        Shop shopCondition = new Shop();
        ShopCategory sc= new ShopCategory();
        sc.setShopCategoryId(2L);
        shopCondition.setShopCategory(sc);
        ShopExecution se = shopService.getShopList(shopCondition,1,2);
        System.out.println("店铺列表数量："+se.getShopList().size());
        System.out.println("店铺总数量："+se.getCount());

    }

    @Test
    public void testModifyShop()throws ShopOperationException,FileNotFoundException{
        Shop shop = new Shop();
        shop.setShopId(18L);
        shop.setShopName("ikun爆破");
        File shopImg = new File("E:/webpic/saber.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder("saber.jpg",is);
        ShopExecution shopExecution= shopService.modifyShop(shop,imageHolder);
        System.out.println("新的图片地址"+shopExecution.getShop().getShopImg());

    }

    @Test
    public void addShop() throws IOException {
        Shop shop = new Shop();
        PersonInfo owner =new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(2L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("你也是缺");
        shop.setShopDesc("坤t");
        shop.setShopAddr("坤t");
        shop.setPhone("114514");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        //String filePath = "E:\\webpic\\jiaxin.jpg";
        File shopImg = new File("E:/webpic/jiaxin.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
        ShopExecution se = shopService.addShop(shop,imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());

    }

    private MultipartFile pathMultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",file.getName(),"text/plain",
                IOUtils.toByteArray(inputStream));
        return multipartFile;

    }
}
