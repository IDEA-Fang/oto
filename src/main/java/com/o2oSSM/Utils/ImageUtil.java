package com.o2oSSM.Utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.o2oSSM.Utils.FileUtils.getRandomFileName;
import static com.o2oSSM.Utils.FileUtils.makeDiePath;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/22
 * 22:25
 * #
 */
public class ImageUtil {

    //项目文件路径
    private  static String basePath = Thread.currentThread().getContextClassLoader()
            .getResource("fangfor1.png").getPath();

    //小图，缩略图
    public static String generateThumbnail(ImageHolder imageHolder, String targetAddress) {

        //水印图片路径，然后转换乱码
        System.out.println(basePath);
        try {
            basePath = java.net.URLDecoder.decode(basePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //生成随机数文件名
        String realFileName = getRandomFileName();
        //获取输入流文件扩展名，什么格式文件
        String extension = FileUtils.getFileExtension(imageHolder.getInputStreamName());
        //创建文件夹
        makeDiePath(targetAddress);

        //加入照片文件
        File waterImg = new File(basePath);
        System.out.println("waterImg---in---project--"+waterImg);

        //文件全名
        String relativeAddress = targetAddress + realFileName + extension;
        File dest = new File(PathUtil.getImageBasePath() + realFileName);
        try {
            Thumbnails.of(imageHolder.getInputStream()).size(1920, 1080)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(waterImg), 0.5f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddress;
    }
    //详情图
    public static String generateNormalImg(ImageHolder imageHolder, String targetAddress) {

        //水印图片路径，然后转换乱码
        try {
            basePath = java.net.URLDecoder.decode(basePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //生成随机数文件名
        String realFileName = getRandomFileName();
        //获取输入流文件扩展名，什么格式文件
        String extension = FileUtils.getFileExtension(imageHolder.getInputStreamName());
        //创建文件夹
        makeDiePath(targetAddress);
        //加入照片文件
        File waterImg = new File(basePath);
        //文件全名
        String relativeAddress = targetAddress + realFileName + extension;
        //todo imgpath
        File dest = new File(PathUtil.getImageBasePath() + targetAddress+realFileName);


        try {
            Thumbnails.of(imageHolder.getInputStream()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(waterImg), 0.5f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddress;
    }






    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(basePath);
        //水印图片路径，然后转换乱码
            basePath = java.net.URLDecoder.decode(basePath, "UTF-8");


            //加入照片文件
            File waterImg = new File(basePath);
            System.out.println(waterImg);

            String path = "D:\\1-link\\Every\\aa11bb.jpg";
            File file = new File(path);
            System.out.println(file);

            try {
                Thumbnails.of(file).size(200, 200)
                        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(waterImg), 0.5f)
                        .outputQuality(0.9f).toFile("D:\\1-link\\Every\\SSMo2o\\new1.png");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

