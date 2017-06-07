package com.guangxunet.shop.base.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.guangxunet.shop.base.domain.Logininfo;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:25:48 
* 类说明 用户的上下文
*/
public class UserContext {
	public static final String LOGININFO_IN_SESSION  = "logininfo";
	
	public static HttpSession getSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	public static void putCurrent(Logininfo loginInfo) {
		getSession().setAttribute(LOGININFO_IN_SESSION,loginInfo);
	}
	
	
}
