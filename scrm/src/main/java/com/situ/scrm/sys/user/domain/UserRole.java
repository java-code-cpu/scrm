/**
 * @Company:中享思途   
 * @Title:UserRole.java 
 * @Author:wxinpeng   
 * @Date:2020年2月12日 上午9:55:57     
 */
package com.situ.scrm.sys.user.domain;

import java.io.Serializable;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:UserRole
 * @Description:(用户角色关联类)
 */
public class UserRole extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userCode;// 用户CODE
	private String roleCode;// 角色CODE

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
