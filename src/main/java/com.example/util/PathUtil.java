package com.example.util;

/**
 * Created by z1271 on 2019/3/19.
 */
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "E:/webpic/";
        }else {
            basePath = "/home/xiangze/image";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop/"+shopId +"/";
        return imagePath.replace("/",seperator);
    }

}
