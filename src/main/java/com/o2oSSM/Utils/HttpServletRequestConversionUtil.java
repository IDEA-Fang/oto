package com.o2oSSM.Utils;

import javax.servlet.http.HttpServletRequest;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/24
 * 18:02
 * #
 */
public class HttpServletRequestConversionUtil {

    public static Integer getInteger(HttpServletRequest httpServletRequest, String key){
        try {
            return Integer.decode(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest httpServletRequest, String key){
        try {
            return Long.valueOf(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String  getString(HttpServletRequest httpServletRequest, String key){
        try {
          String result = httpServletRequest.getParameter(key);
          if (result!=null){return result.trim();}
          if ("".equals(result)){return null;}

          return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Boolean getBoolean(HttpServletRequest httpServletRequest, String key){
        try {
            return Boolean.valueOf(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static Double getDouble(HttpServletRequest httpServletRequest, String key){
        try {
            return Double.valueOf(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return -1d;
        }
    }


}
