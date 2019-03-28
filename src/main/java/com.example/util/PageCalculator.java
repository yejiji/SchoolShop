package com.example.util;

/**
 * Created by z1271 on 2019/3/26.
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
