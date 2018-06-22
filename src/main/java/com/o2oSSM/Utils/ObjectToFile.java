package com.o2oSSM.Utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/25
 * 20:48
 * #
 */
public class ObjectToFile {

    public static void InputStreamToFile(InputStream inputStream,File file) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[1024];
        while ((bytesRead = inputStream.read(buffer, 0, bytesRead))!=-1){
             outputStream.write(buffer,0,bytesRead);
        }
        outputStream.close();
        inputStream.close();
        };

    public static File CommonsMultipartFileToFile(CommonsMultipartFile commonsMultipartFile){

        /**
         *   commonsMultipartFile.getInputStream()返回值是inputStream
         *   inputStream转化为File
         *   会在项目的根目录的临时文件夹下生成一个文件；
         */
        DiskFileItem diskFileItem = (DiskFileItem)commonsMultipartFile.getFileItem();
        File result = diskFileItem.getStoreLocation();

        return result;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\1-link\\Every\\fangfor.png");
        InputStream inputStream = new FileInputStream(file);

        InputStream in=new FileInputStream(file);//实例化FileInputStream
        int fileLength=(int)file.length();
        byte b[]=new byte[fileLength];
        int length=in.read(b);
        in.close();
        System.out.println("读取到的内容是："+new String(b));
    }
}

