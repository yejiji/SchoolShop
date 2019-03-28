package com.example.exceptions;

/**
 * Created by z1271 on 2019/3/19.
 */
public class ShopOperationException extends RuntimeException {


    private static final long serialVersionUID = 3914550911369340752L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
