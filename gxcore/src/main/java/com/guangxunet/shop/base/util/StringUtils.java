package com.guangxunet.shop.base.util;


/**
 * Created by Administrator on 2016/7/20.
 */
public class StringUtils {
    public static boolean hasLength(String string){
        return string!=null && !"".equals(string);
    }
    
    /**
     * 获取用户手机号码的隐藏字符串
     * @param
     * @return
     */
    public static String getAnonymousPhoneNumber(String phoneNumber) {
        if (StringUtils.hasLength(phoneNumber)) {
            int len = phoneNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if ((i > 2 && i < 7)) {
                    replace += "*";
                } else {
                    replace += phoneNumber.charAt(i);
                }
            }
            return replace;
        }
        return phoneNumber;
    }
}

