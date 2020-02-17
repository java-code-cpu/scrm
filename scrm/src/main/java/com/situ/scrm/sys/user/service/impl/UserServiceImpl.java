package com.situ.scrm.sys.user.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.user.dao.UserDao;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;
import com.situ.scrm.utils.MD5Utils;

@Service
public class UserServiceImpl implements UserService {
	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	/**
	 * @Title: checkUserCode
	 * @Description:(检测名称唯一)
	 * @param userCode
	 * @return
	 */
	@Override
	public Integer checkUserCode(String userCode) {
		User checkUser = userDao.getUserByCode(userCode);
		return checkUser != null ? 1 : 0;
	}

	/**
	 * @Title: saveUser
	 * @Description:(新增功能)
	 * @param User
	 * @return
	 */
	@Override
	public Long saveUser(User user) {
		user.setUserPass(MD5Utils.encode(user.getUserPass()));
		user.setIsLock(User.IS_LOCK_NO);
		user.setLoginCount(1);
		user.setCreateBy("SYS");
		user.setCreateDate(new Date());
		user.setActiveFlag(1);
		userDao.save(user);
		return user.getRowId();
	}

	@Override
	public Integer doDeleteUser(Long rowId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Long rowId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer doEditUser(User User) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(User searchUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LayResult findUserByPage(Integer page, Integer limit, User searchUser) {
		// TODO Auto-generated method stub
		return null;
	}
}
