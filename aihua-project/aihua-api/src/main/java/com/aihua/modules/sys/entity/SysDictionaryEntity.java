package com.aihua.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统词典
 */
@TableName("sys_dictionary")
public class SysDictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 上级编码
	 */
	private String pcode;
	/**
	 * 编码(010101)
	 */
	private String code;

	/**
	 * 是否显示1显示，0不显示
	 */
	@TableField(value="is_show")
	private Integer isShow;
	
	/**
	 * 序号
	 */
	private Integer sort;
	/**
	 * 属性名称
	 */
	private String name;
	/**
	 */
	private String keywords;
	/**
	 * 属性值
	 */
	private String value;
	/**
	 * 组织描述
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date ctime;
	
	@TableField(exist=false)
	private String parentName;

	/**
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：上级编码
	 */
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	/**
	 * 获取：上级编码
	 */
	public String getPcode() {
		return pcode;
	}
	/**
	 * 设置：编码(010101)
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编码(010101)
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：属性名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：属性名称
	 */
	public String getName() {
		return name;
	}
	/**
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * 设置：属性值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：属性值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：组织描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：组织描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCtime() {
		return ctime;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
