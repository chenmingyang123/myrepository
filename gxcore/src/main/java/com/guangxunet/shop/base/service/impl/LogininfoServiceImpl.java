package com.guangxunet.shop.base.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guangxunet.shop.base.domain.Logininfo;
import com.guangxunet.shop.base.mapper.LogininfoMapper;
import com.guangxunet.shop.base.service.ILogininfoService;
import com.guangxunet.shop.base.util.BidConst;

/**登陆相关实现
 * Created by Administrator on 2016/9/30.
 */
@Service
public class LogininfoServiceImpl implements ILogininfoService{
    @Autowired
    private LogininfoMapper logininfoMapper;

    public void register(String username, String password) {


        int count = logininfoMapper.countUserByName(username);
        if (count>0){
            throw new RuntimeException("该用户名已经被注册");
        }else{
            //如果用户名可用，则注册成功，保存该用户
            /*Logininfo logininfo = new Logininfo();
            logininfo.setUsername(username);
            logininfo.setPassword(MD5.encode(password));
            logininfo.setState(Logininfo.STATE_NOMAL);
            logininfo.setUserType(Logininfo.USER_NORMAL);//登录设置为前台用户
            logininfoMapper.insert(logininfo);

            //注册成功之后创建对应的用户信息对象和账户信息对象，由于用户对象是从one方，依赖于注册用户的id,所以要放在它后面创建
            Userinfo userinfo = new Userinfo();
            userinfo.setId(logininfo.getId());
            userinfoService.add(userinfo);

            //创建账户对象
            Account account = new Account();
            account.setId(logininfo.getId());
            accountService.add(account);*/
        }
    }

    @Override
    public boolean checkUserNameExist(String username) {
        return (logininfoMapper.countUserByName(username)>0);
    }

    @Override
    public Logininfo login(String username, String password,String ip,int usertype) {
        //登录操作时创建登陆日志对象
        /*Iplog iplog = new Iplog();
        iplog.setUserName(username);
        iplog.setIp(ip);//ip由Controller控制器中传入，在HttpServletRequest中
        iplog.setLoginTime(new Date());
        iplog.setUserType(usertype);
        Logininfo logininfo = logininfoMapper.login(username, MD5.encode(password),usertype);
        if(logininfo!=null){
            UserContext.putCurrent(logininfo);//将登录者信息保存到session中
            iplog.setLoginState(Iplog.STATE_SUCCESS);//登陆状态
        }else{
            iplog.setLoginState(Iplog.STATE_FAILED);
        }
        //保存iplog
        iplogMapper.insert(iplog);

        return logininfo;*/
    	return null;
    }

    @Override
    public void initAdmin() {
        //查询数据库中是否有后台管理员
        int count = logininfoMapper.countByUserType(Logininfo.USER_MANAGER);
        System.out.println("===count==="+count);
        //如果没有就创建一个默认的,否则就不创建
        if (count <= 0){
            Logininfo admin = new Logininfo();
            admin.setUsername(BidConst.DEFAULT_ADMIN_NAME);
            admin.setPassword(BidConst.DEFAULT_ADMIN_PASSWORD);
            admin.setUserType(Logininfo.USER_MANAGER);
            admin.setState(Logininfo.STATE_NOMAL);
            this.logininfoMapper.insert(admin);
        }
    }
}
