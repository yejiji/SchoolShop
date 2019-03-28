package com.example.service;

import com.example.dto.ShopExecution;
import com.example.entity.Shop;
import com.example.exceptions.ShopOperationException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * Created by z1271 on 2019/3/19.
 */
public interface ShopService {


    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
    /**
     *
     * @param shopId
     * @return
     */

    Shop getByShopId(long shopId);

    ShopExecution modifyShop(Shop shop, InputStream shopImgInpStream,String fileName) throws ShopOperationException;


    //ShopExecution addShop(Shop shop, InputStream shopImg,String fileName);
    ShopExecution addShop(Shop shop, InputStream shopImgInpStream,String fileName) throws ShopOperationException;


}
