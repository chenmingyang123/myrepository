package com.guangxunet.shop.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.util.JsonResult;

/**注册相关服务
 * Created by Administrator on 2016/9/30.
 */
@Controller
public class RegisterController {
    @Autowired
    private ILogininfoService logininfoService;

    /**
     * 前台新用户只能通过手机号注册
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public JsonResult register(String mobile, String password){
        JsonResult result = null;
        try {
            logininfoService.register(mobile, password);
            result = new JsonResult(true,"注册成功！");
        } catch (Exception e) {
            result = new JsonResult(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/checkUserNameExist")
    @ResponseBody
    public Boolean checkUserByName(String username){
        return logininfoService.checkUserNameExist(username);
    }

}
