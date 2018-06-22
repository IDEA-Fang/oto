package com.o2oSSM.Utils;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/23
 * 11:17
 * #获取文件路径
 */
public class PathUtil {
    private static  String separator = System.getProperty("file.separator");

    //目标图片存放的地址
    public static String getImageBasePath(){
        String os = System.getProperty("os.name");
        System.out.println(os);
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:\\4-Enjoy\\Picture\\SSMo2o\\";
        }
        else {basePath = "/home/SSMo2o/image/";}
        basePath = basePath.replace("/", separator);
        System.out.println("XXXXXXXXXX"+basePath);
        return basePath;
    }

    //图片的相对地址
    public static  String getShopImgPath(long shopId){
        String basePath = getImageBasePath();
        String imagePath ="upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",separator );
    }
}
