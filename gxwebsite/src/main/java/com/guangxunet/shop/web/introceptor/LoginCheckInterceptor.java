package com.guangxunet.shop.web.introceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:06:04 
* 类说明 移动端登录检查过滤器
*/
public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			
		return true;
	}
}
