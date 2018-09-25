package com.o2oSSM.Utils.weixinUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/25
 * 20:37
 * #微信校验工具类
 */
public class SignUtil {

    //与接口配置的信息要一致
    private static String token = "myoto";

    /**
     * 验证签名
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] arr = new String[]{token,timestamp,nonce};
        //将token，timestamp,nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for(int i = 0;i<arr.length;i++){
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String temStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            //将三个字符串凭借成一个字符串经行sha-1加密
            byte[] digest = md.digest(content.toString().getBytes());
            temStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        content = null;
        //将sha-1加密后的字符串可与signayure对比，
        // 标识该请求的来源于微信
        return temStr != null ? temStr.equals(signature.toUpperCase()):false;
    }
    /**
     * 将字节数组转换为十六进制字符串
     */
    private static  String byteToStr(byte[] byteArray){
        String strDigest = "";
        for (int i = 0;i<byteArray.length;i++){
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    /**
     * 将字节转换为十六进制字符串
     */
    private static String byteToHexStr(byte mByte){
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
