package com.example.dao;

import com.example.entity.ProductImg;

import java.util.List;

/**
 * Created by z1271 on 2019/3/31.
 */
public interface ProductImgDao {
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    int deleteProductImgByProductId(long productId);
}
