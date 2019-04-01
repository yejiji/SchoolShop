package com.example.exceptions;

/**
 * Created by z1271 on 2019/4/1.
 */
public class ProductOperationException extends RuntimeException {

    private static final long serialVersionUID = -539191854805367026L;

    public ProductOperationException(String msg){
        super(msg);
    }
}
