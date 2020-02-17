/**
 * @Company:中享思途   
 * @Title:RoleServiceImpl.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午5:20:52     
 */
package com.situ.scrm.sys.role.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.role.dao.RoleDao;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.sys.role.service.RoleService;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.utils.DAOUtils;

/** 
 * @ClassName:RoleServiceImpl 
 * @Description:(角色类的逻辑层的实现类)  
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private SysCountUtils sysCountUtils;

	/**
	 * @Title: checkRoleName 
	 * @Description:(检测名称唯一)
	 * @param roleName
	 * @return
	 */
	@Override
	public Integer checkRoleName(String roleName) {
		//根据名称去查询出实例。
		Role role = roleDao.getByName(roleName);
		//判断如果不等于null 则返回1，表示已经别使用了。
		return role != null ? 1 : 0;
	}

	/**
	 * @Title: saveRole 
	 * @Description:(新增功能)
	 * @param role
	 * @return
	 */
	@Override
	public Long saveRole(Role role) {
		role.setRoleCode(sysCountUtils.buildRoleCode());
		role.setActiveFlag(1);
		role.setCreateBy("SYS");
		role.setCreateDate(new Date());
		roleDao.save(role);
		return role.getRowId();
	}

	/**
	 * @Title: doDeleteRole 
	 * @Description:(删除功能)
	 * @param rowId
	 * @return
	 */
	@Override
	public Integer doDeleteRole(Long rowId) {
		roleDao.delete(rowId);
		return 1;
	}

	/**
	 * @Title: getRole 
	 * @Description:(根据id查询实例)
	 * @param rowId
	 * @return
	 */
	@Override
	public Role getRole(Long rowId) {
		return roleDao.get(rowId);
	}

	/**
	 * @Title: doEditRole 
	 * @Description:(执行删除)
	 * @param role
	 * @return
	 */
	@Override
	public Integer doEditRole(Role role) {
		Long rowId = role.getRowId();
		Role editRole = roleDao.get(rowId);
		roleDao.update(DAOUtils.buildEditData(editRole, role));
		return 1;
	}

	/**
	 * @Title: getCount 
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	@Override
	public Integer getCount(Role searchRole) {
		return roleDao.getCount(searchRole);
	}

	/**
	 * @Title: findRoleByPage 
	 * @Description:(根据分页查询数据)
	 * @param page 页号
	 * @param limit 每页显示的条数
	 * @param searchRole 查询条件的数据
	 * @return
	 */
	@Override
	public LayResult findRoleByPage(Integer page, Integer limit, Role searchRole) {
		//通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的拼写
		Role searchParam = DAOUtils.buildSearchParam(searchRole);
		//查询出符合条件的一共有多少条记录。
		Integer dataCount = roleDao.getCount(searchParam);
		//根据分页的请求信息查询出数量列表。
		List<Role> roleList = roleDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, roleList);
	}

	/**
	 * @Title: findAllRole 
	 * @Description:(查询所有的角色信息)
	 * @return
	 */
	@Override
	public List<Role> findAllRole() {
		return roleDao.find();
	}
}
