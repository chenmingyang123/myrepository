package com.guangxunet.shop.base.domain;
import org.apache.ibatis.type.Alias;

import com.guangxunet.shop.base.util.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**用户基本信息对象
 * Created by King on 2016/10/9.
 */
@Getter
@Setter
@ToString
@Alias("Userinfo")
public class Userinfo extends BaseDomain{
    private int version;//版本号(用于乐观锁)
    private long bitState;//用户状态码:当前是否有标在借款流程中
    private String realName;//用户真实姓名{冗余}
    private String idNumber;//身份证号{冗余}
    private String phoneNumber;//用户电话
    private String email;//用户邮箱

    /**
     * 获取用户身份号码的隐藏字符串
     * @param
     * @return
     */
    public String getAnonymousIdNumber() {
        if (StringUtils.hasLength(idNumber)) {
            int len = idNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if ((i > 5 && i < 10) || (i > len - 5)) {
                    replace += "*";
                } else {
                    replace += idNumber.charAt(i);
                }
            }
            return replace;
        }
        return idNumber;
    }

    /**
     * 获取用户手机号码的隐藏字符串
     * @param
     * @return
     */
    public String getAnonymousPhoneNumber() {
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



    //如果通过认证，则显示真实姓名
    public String getAnonymousRealName(){
        return realName;
    }

}
