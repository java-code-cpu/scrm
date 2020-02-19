package com.situ.scrm.sys.system.service;

import com.situ.scrm.sys.system.domain.System1;

public interface SystemService {
	
	/**
	 * 查询所有数据
	 * @return
	 */
	System1 fingById();
	
	/**
	 * 更新数据
	 * @param system
	 * @return
	 */
	
	System1 update(System1 system);
	
	/**
	 * 初始化数据
	 */
	
	void initSystemData();
	
}
