package com.o2oSSM.Utils;

import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Exceptions.ObjectException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/20
 * 15:56
 * #
 */
public class FileUtils {

    //生成随机文件名 当前年月日时分秒+五位随机数
    public static String getRandomFileName() {
        Random random = new Random();
        //时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTimeDate = dateFormat.format(new Date());
        String randomFileName = nowTimeDate + random.nextInt(89999) + 10000;
        return randomFileName;
    }

    //获取输入流文件扩展名，什么格式文件
    public static String getFileExtension(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        return fileExtension;
    }

    //创建文件夹
    //   D:\4-Enjoy\Picture\SSMo2o\
    public static void makeDiePath(String targetAddress) {
        String realFileParentPath = PathUtil.getImageBasePath() + targetAddress;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    //删除文件
    public static void deleteFile(String productimgAddress) {
        String fileName =productimgAddress;
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在!\n ---productimgAddress--"+productimgAddress);
            //throw new ObjectException(ResultEnum.HAVE_NULL_VALUE);
        } else {
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.isFile()) {
                if (file.delete()) {
                    System.out.println("删除单个文件" + fileName + "成功！");
                } else {
                    System.out.println("删除单个文件" + fileName + "失败！");
                }
            }
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                    System.out.println("--------->>>>>++deleteDirectory" + files[i]);
                }
            }
        }
    }

}
