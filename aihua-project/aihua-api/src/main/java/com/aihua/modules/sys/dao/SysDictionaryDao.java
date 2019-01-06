package com.aihua.modules.sys.dao;

import java.util.List;

import com.aihua.modules.sys.entity.SysDictionaryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 系统词典
 * 
 */
@Mapper
public interface SysDictionaryDao extends BaseMapper<SysDictionaryEntity> {
	/**
	 * 获取树形数据
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年10月23日
	 */
	@Select("select s.*,p.name parentName from sys_dictionary s left join (select code,name from sys_dictionary ) p on s.pcode = p.code")
	List<SysDictionaryEntity> queryTreeList();
	
	/**
	 * 通过父类编码，获取最后一个子类编码
	 * @param pcode
	 * @return
	 * @author: CZD  
	 * @Createtime: 2018年10月23日
	 */
	@Select("select code from sys_dictionary where pcode=#{pcode} order by id desc limit 0,1")
	String queryLastCodeByPcode(@Param("pcode")String pcode);
}
