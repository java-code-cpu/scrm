/**
 * @Company:中享思途   
 * @Title:UserController.java 
 * @Author:wxinpeng   
 * @Date:2020年1月8日 上午9:54:17     
 */
package com.situ.scrm.sys.user.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.role.service.RoleService;
import com.situ.scrm.sys.user.service.UserService;

/** 
 * @ClassName:UserController 
 * @Description:(用户的Controller层)  
 */
@RestController
@RequestMapping("/user")
public class UserController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_USER_INDEX = "sys/user/user_index";
	private static final String PAGE_USER_ADD_EDIT = "sys/user/user_add_edit";
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * @Title: goIdnex 
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping
	public ModelAndView goIdnex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_USER_INDEX);
		return modelAndView;
	}

	/**
	 * @Title: getForm 
	 * @Description:(得到表单)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView getForm(ModelAndView modelAndView) {
		modelAndView.addObject("roleList", roleService.findAllRole());
		modelAndView.setViewName(PAGE_USER_ADD_EDIT);
		return modelAndView;
	}

	/**
	 * @Title: checkUserCode 
	 * @Description:(检测CODE唯一)
	 * @param userCode
	 * @return
	 */
	@GetMapping("/checkcode")
	public Integer checkUserCode(String userCode) {
		return userService.checkUserCode(userCode);
	}
}
