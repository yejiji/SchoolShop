package com.example.service.impl;

import com.example.dao.ProductCategoryDao;
import com.example.dto.ProductCategoryExecution;
import com.example.entity.ProductCategory;
import com.example.enums.ProductCategoryStateEnum;
import com.example.exceptions.ProductCategoryOperationException;
import com.example.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by z1271 on 2019/3/28.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> queryProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategories) throws ProductCategoryOperationException
    {
        if (productCategories!=null&&productCategories.size()>0){
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategories);
                if (effectedNum<=0){
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw  new ProductCategoryOperationException("batchAddProductCategoryList ERR:"+e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }

    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productcategoryId, long shopId) throws ProductCategoryOperationException {
        //TODO讲此商品类别下的商品的类别Id设置空
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productcategoryId,shopId);
            if (effectedNum<=0){
                throw new ProductCategoryOperationException("店铺类别删除失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);

            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deletePC ERROR"+e.getMessage());
        }

    }
}
