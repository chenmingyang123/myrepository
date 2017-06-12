package com.guangxunet.shop.base.service;

import java.util.List;

import com.guangxunet.shop.base.domain.Banner;

/** 
* @author 作者 E-mail: King
* @version 创建时间：2017年6月11日 下午11:57:58 
* 类说明 首页Banner
*/
public interface IBannerService {
	/**
	 * 删除Banner
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条Banner
     * @param banner
     * @return
     */
    int insert(Banner banner);

    /**
     * 选择一个Banner
     * @param id
     * @return
     */
    Banner selectByPrimaryKey(Integer id);

    /**
     * 获取所有Banner列表
     * @return
     */
    List<Banner> selectAll();

    /**
     * 修改Banner记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(Banner banner);
}
