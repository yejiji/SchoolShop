package com.example.service;

import com.example.dto.ImageHolder;
import com.example.dto.ProductExecution;
import com.example.entity.Product;
import com.example.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by z1271 on 2019/4/1.
 */
public interface ProductService {

    /**
     * 添加商品信息以及图片处理
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;
}
