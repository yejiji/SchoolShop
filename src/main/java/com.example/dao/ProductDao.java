package com.example.dao;

import com.example.entity.Product;

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
}
