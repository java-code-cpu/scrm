package com.situ.scrm.sys.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.system.domain.System1;

@Repository
public interface SystemDao extends BaseDao<System1> {
	
	System1 findById();
}
