package com.situ.scrm.sys.dictionary.doamin;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

@Alias("Dictionary")
public class Dictionary extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PARENT_CODE = "S000";// 默认的一级资源的CODE
	private String parentCode; // 父级Id
	private String sucCode; // 字典code
	private String sucName; // 字典名称
	private Integer sucOrder; // 排序
	private Integer hasChild; // 是否有子资源

	// 额外的元素
	private List<Dictionary> children;// 子元素的数据

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getSucCode() {
		return sucCode;
	}

	public void setSucCode(String sucCode) {
		this.sucCode = sucCode;
	}

	public String getSucName() {
		return sucName;
	}

	public void setSucName(String sucName) {
		this.sucName = sucName;
	}

	public Integer getSucOrder() {
		return sucOrder;
	}

	public void setSucOrder(Integer sucOrder) {
		this.sucOrder = sucOrder;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	public List<Dictionary> getChildren() {
		return children;
	}

	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}

	public Dictionary(String parentCode, String sucCode, String sucName, Integer sucOrder, Integer hasChild) {
		super();
		this.parentCode = parentCode;
		this.sucCode = sucCode;
		this.sucName = sucName;
		this.sucOrder = sucOrder;
		this.hasChild = hasChild;
	}

	public Dictionary() {
	}

}
