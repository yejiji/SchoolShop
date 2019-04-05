package com.example.dao;

import com.example.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by z1271 on 2019/3/31.
 */
public interface ProductDao {


    int insertProduct(Product product);

    /**
     * 通过id查询
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    List<Product> queryProductList(@Param("productCondition")Product productCondition,@Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);

    /**
     * 查询对应的商品总数
     *
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /**
     * 删除商品类别前讲商品类别ID置空
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);

}
