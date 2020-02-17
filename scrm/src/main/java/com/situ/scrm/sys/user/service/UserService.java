package com.situ.scrm.sys.user.service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.user.domain.User;

public interface UserService {
	/**
	 * @Title: checkUserCode 
	 * @Description:(检测名称唯一)
	 * @param userCode
	 * @return
	 */
	Integer checkUserCode(String userCode);

	/**
	 * @Title: saveUser 
	 * @Description:(新增功能)
	 * @param user
	 * @return
	 */
	Long saveUser(User user);

	/**
	 * @Title: doDeleteUser 
	 * @Description:(删除功能)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteUser(Long rowId);

	/**
	 * @Title: getUser 
	 * @Description:(根据id查询实例)
	 * @param rowId
	 * @return
	 */
	User getUser(Long rowId);

	/**
	 * @Title: doEditUser 
	 * @Description:(执行删除)
	 * @param user
	 * @return
	 */
	Integer doEditUser(User user);

	/**
	 * @Title: getCount 
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	Integer getCount(User searchUser);

	/**
	 * @Title: findUserByPage 
	 * @Description:(根据分页查询数据)
	 * @param page
	 * @param limit
	 * @param searchUser
	 * @return
	 */
	LayResult findUserByPage(Integer page, Integer limit, User searchUser);
}
