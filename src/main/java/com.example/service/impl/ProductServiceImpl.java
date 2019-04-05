package com.example.service.impl;

import ch.qos.logback.core.util.FileUtil;
import com.example.dao.ProductDao;
import com.example.dao.ProductImgDao;
import com.example.dto.ImageHolder;
import com.example.dto.ProductExecution;
import com.example.entity.Product;
import com.example.entity.ProductImg;
import com.example.enums.ProductStateEnum;
import com.example.exceptions.ProductOperationException;
import com.example.service.ProductService;
import com.example.util.ImageUtil;
import com.example.util.PageCalculator;
import com.example.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by z1271 on 2019/4/1.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {

        if(product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null){
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (thumbnail!=null){
                addThumbnail(product,thumbnail);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum<=0){
                    throw new ProductOperationException("创建商品失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品失败"+e.toString());
            }
            if (productImgList!=null&&productImgList.size()>0){
                addProductImgList(product,productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Product> productList = productDao.queryProductList(productCondition,rowIndex,pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {

        if (product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null){
            product.setLastEditTime(new Date());
            if (thumbnail!=null){
                Product tempProduct = productDao.queryProductById(product.getProductId());
                System.out.println(tempProduct.getProductId());
                if (tempProduct.getImgAddr()!=null){
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product,thumbnail);
            }
            if (productImgList!=null&&productImgList.size()>0){
                deleteProductImgList(product.getProductId());
                addProductImgList(product,productImgList);
            }
            try {
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum<=0){
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS,product);
            }catch (Exception e){
                throw new ProductOperationException("更新商品信息失败"+e.toString());
            }
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }


    }

    private void addThumbnail(Product product,ImageHolder thumbnail){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnaiAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        product.setImgAddr(thumbnaiAddr);
    }
    private void addProductImgList(Product product,List<ImageHolder> productImgHolderList){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> imageList = new ArrayList<ProductImg>();
        for (ImageHolder productImgHolder :productImgHolderList){
            String imgAddr = ImageUtil.generateNormalThumbnail(productImgHolder,dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            imageList.add(productImg);
        }
        if (imageList.size()>0){
            try {
                int effectedNum =productImgDao.batchInsertProductImg(imageList);
                if (effectedNum<=0){
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品详情图片失败:"+e.toString());
            }
        }

    }
    private void deleteProductImgList(long productId){
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList){
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }
}
