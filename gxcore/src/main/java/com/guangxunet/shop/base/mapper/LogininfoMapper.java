package com.guangxunet.shop.base.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guangxunet.shop.base.domain.Logininfo;

public interface LogininfoMapper {

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Logininfo record);

    int countUserByName(String username);
    
    int countUserByMobile(String mobile);

    Logininfo login(@Param("username") String username, @Param("password") String password,@Param("usertype")int usertype);

    int countByUserType(int userManager);

    List<Map<String,Object>> autoComplate(@Param("word")String word, @Param("userType")int userType);
}
