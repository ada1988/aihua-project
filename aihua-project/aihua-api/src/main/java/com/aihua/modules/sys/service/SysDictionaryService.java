package com.aihua.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.aihua.common.utils.PageUtils;
import com.aihua.modules.sys.entity.SysDictionaryEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统词典
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-24 14:58:39
 */
public interface SysDictionaryService extends IService<SysDictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
	public List<SysDictionaryEntity> queryTreeList();
	
	/**
	 * 插入
	 * @param sysDictionary
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年10月23日
	 */
	boolean insertDictionary(SysDictionaryEntity sysDictionary);
	
	/**
	 * 更改
	 * @param sysDictionary
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年10月23日
	 */
	boolean updateDictionary(SysDictionaryEntity sysDictionary);
}

