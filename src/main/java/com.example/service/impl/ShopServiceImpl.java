package com.example.service.impl;

import com.example.dao.ShopDao;
import com.example.dto.ShopExecution;
import com.example.entity.Shop;
import com.example.enums.ShopStateEnum;
import com.example.exceptions.ShopOperationException;
import com.example.service.ShopService;
import com.example.util.ImageUtil;
import com.example.util.PageCalculator;
import com.example.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by z1271 on 2019/3/19.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

//    @Override
//    @Transactional
//    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
//        //控制判断
//        if (shop == null){
//            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
//        }
//        try {
//            shop.setEnableStatus(0);
//            shop.setCreateTime(new Date());
//            shop.setLastEditTime(new Date());
//            int effectedNum =shopDao.insertShop(shop);
//            if (effectedNum <=0 ){
//                throw new ShopOperationException("店铺创建失败");
//            }else {
//                if (shopImgInputStream !=null){
//                    try {
//                        addShopImg(shop, shopImgInputStream,fileName);
//                    }catch (Exception e){
//                        throw new ShopOperationException("addShopImgErr"+e.getMessage());
//                    }
//                    effectedNum = shopDao.updateShop(shop);
//                    if (effectedNum<=0){
//                        throw new ShopOperationException("更新图片地址失败");
//                    }
//                }
//            }
//        }catch (Exception e){
//            throw  new RuntimeException("addShop error:"+e.getMessage());
//        }
//
//
//        return new ShopExecution(ShopStateEnum.CHECK,shop);
//    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList!=null){
            se.setShopList(shopList);
            se.setCount(count);
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInpStream, String fileName) throws ShopOperationException {

        //1.判断是否需要更新图片
        if (shop==null||shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }else {
            try {
            if (shopImgInpStream!=null&&fileName!=null&&!"".equals(fileName)){
                System.out.println("执行删除");
                Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                if (tempShop.getShopImg()!=null){
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImg(shop,shopImgInpStream,fileName);
            }
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.updateShop(shop);
            if (effectedNum<=0){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }else {
                shop = shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS,shop);
            }}catch (Exception e){
                throw new ShopOperationException("modifyShop error"+e.getMessage());
            }
        }


    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImg,String fileName)throws ShopOperationException {
        //控制判断
        if (shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum =shopDao.insertShop(shop);
            if (effectedNum <=0 ){
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (shopImg !=null){
                    try {
                        addShopImg(shop, shopImg,fileName);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImgErr"+e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum<=0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw  new RuntimeException("addShop error:"+e.getMessage());
        }


        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, InputStream shopImg,String fileName){
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String ShopImgAddr = ImageUtil.generateThumbnail(shopImg,fileName,dest);
        shop.setShopImg(ShopImgAddr);
    }

}
