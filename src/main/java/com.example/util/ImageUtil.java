package com.example.util;

import com.example.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by z1271 on 2019/3/19.
 */
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHHmmss");
    private static final Random r = new Random();
//    public static String generateThumbnail(InputStream thumbnail,String fileName, String targetAddr) {
//        String realFileName = getRandomFileName();
//        String extension = getFileExtension(fileName);
//        makeDirPath(targetAddr);
//        String relativeAddr = targetAddr + realFileName + extension;
//        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
//        try {
//            Thumbnails.of(thumbnail).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/tubiao.jpg")),
//                    0.25f).outputQuality(0.8f)
//                    .toFile(dest);
//        } catch (IOException e) {
//            throw new RuntimeException("创建缩略图失败：" + e.toString());
//        }
//        return relativeAddr;
//    }
        public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(200, 200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/tubiao.jpg")),
                    0.25f).outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }



    /**
     * 随机文件名
     *
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public static String getRandomFileName() {
        // 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
        int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
        String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
        return nowTimeStr + rannum;
    }

    /**
     * 创建路径目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * storePath是文件路径还是目录路径，
     * 如果是文件则删除文件  是目录则删除目录下所有文件
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File file[] =fileOrPath.listFiles();
                for (int i=0;i<file.length;i++){
                    file[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }
//    public static void main(String[] args) throws IOException {
//        Thumbnails.of(new File("E:/webpic/jiaxin.jpg"))
//                .size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/tubiao.jpg")),0.25f)
//                .outputQuality(0.8f)
//                .toFile("E:/webpic/jiaxin2.jpg");
//    }
}
