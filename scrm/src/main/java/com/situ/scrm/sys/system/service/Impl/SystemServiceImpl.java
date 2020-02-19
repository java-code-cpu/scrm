package com.situ.scrm.sys.system.service.Impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.sys.system.dao.SystemDao;
import com.situ.scrm.sys.system.domain.System1;
import com.situ.scrm.sys.system.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SystemDao systemdao;

	/**
	 * 更新数据	
	 */
	
	@Override
	public System1 update(System1 system) {
		systemdao.update(system);
		return null;
	}
	/**
	 * 查找数据
	 */

	@Override
	public System1 fingById() {
		return systemdao.findById();
	}
	
	/**
	 * 初始化数据
	 */
	
	@Override
	@PostConstruct
	public void initSystemData() {
		System1 systemList = systemdao.findById();
		if(systemList == null) {
			System1 system = new System1();
			system.setActiveFlag(1);
			system.setHighSeas("0");
			system.setDocReminder("0");
			system.setConfig1("");
			system.setCreateBy("sys");
			system.setCreateDate(new Date());
			systemdao.save(system);
		}
	}
	
}
