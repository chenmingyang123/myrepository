package com.guangxunet.shop.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.util.JsonResult;
import com.guangxunet.shop.base.util.UserContext;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:14:53 
* 类说明 前台系统用户登录
*/
public class LoginInfoController {
	@Autowired
	private ILogininfoService iLogininfoService;
	
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String username, String password, HttpServletRequest request){
		JsonResult result = null;
		Logininfo login = iLogininfoService.login(username, password, request.getRemoteAddr(), Logininfo.USER_NORMAL);
		
		if (login == null) {
			result = new JsonResult("用户名或密码错误，请重试！");
		}else{
			result = new JsonResult(true,"登录成功！");
		}
		return result;
	}
}
