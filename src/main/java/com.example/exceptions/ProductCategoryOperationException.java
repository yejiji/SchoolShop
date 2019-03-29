package com.example.exceptions;

import com.example.dto.ProductCategoryExecution;

/**
 * Created by z1271 on 2019/3/29.
 */
public class ProductCategoryOperationException extends RuntimeException {


    private static final long serialVersionUID = -1390307167802820721L;
    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
