package com.o2oSSM.Utils;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/27
 * 23:05
 * #
 */
public class KaptchaCodeUtil {
    public static boolean  checkCaptchaCode(HttpServletRequest httpServletRequest){
        String captchaCodeExpected = String.valueOf(httpServletRequest.getSession()
                .getAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY));

        String captchaCodeActual = HttpServletRequestConversionUtil
                .getString(httpServletRequest,"captchaCodeActual" );
        if (captchaCodeActual == null&&!captchaCodeExpected.equals(captchaCodeActual)){
            return false;
        }
        return  true;
    }
}
