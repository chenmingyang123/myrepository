package com.guangxunet.shop.base.mapper;

import java.util.List;

import com.guangxunet.shop.base.domain.Iplog;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月7日 下午9:59:30 
* 类说明 登陆日志Mapper接口
*/
public interface IpLogMapper {
	int insert(Iplog record);
	
	List<Iplog> selectAll();
}
