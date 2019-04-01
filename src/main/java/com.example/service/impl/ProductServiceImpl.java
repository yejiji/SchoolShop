package com.example.service.impl;

import com.example.dao.ProductDao;
import com.example.dao.ProductImgDao;
import com.example.dto.ImageHolder;
import com.example.dto.ProductExecution;
import com.example.entity.Product;
import com.example.exceptions.ProductOperationException;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }
}
