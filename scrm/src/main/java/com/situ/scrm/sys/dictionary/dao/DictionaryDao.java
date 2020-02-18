package com.situ.scrm.sys.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.dictionary.doamin.Dictionary;

@Repository
public interface DictionaryDao extends BaseDao<Dictionary> {
	/**
	 * @Title: getByNameAndParent
	 * @Description:(根据Name和parentCode查询实例)
	 * @param rescName
	 * @param parentCode
	 * @return
	 */
	Dictionary getByNameAndParent(@Param("sucName") String sucName, @Param("parentCode") String parentCode);

	/**
	 * @Title: getMaxOrder
	 * @Description:(根据ParentCode得到最大的排序)
	 * @param parentCode
	 * @return
	 */
	Integer getMaxOrder(String parentCode);

	/**
	 * @Title: updateHasChild
	 * @Description:(更新是否有子元素)
	 * @param hasChild
	 */
	void updateHasChild(@Param("sucCode") String sucCode, @Param("hasChild") Integer hasChild);

	/**
	 * @Title: getByCode
	 * @Description:(根据CODE查询实例)
	 * @param rescCode
	 * @return
	 */
	Dictionary getByCode(String sucCode);

	/**
	 * @Title: findByParent
	 * @Description:(根据parentCode查询子类数据)
	 * @param parentCode
	 * @return
	 */
	List<Dictionary> findByParent(String parentCode);
	
	/**
	 * @Title: findByParent
	 * @Description:(根据parentCode查出主键集合)
	 * @param parentCode
	 * @return
	 */
	List<Long> findIdByParent(String parentCode);
}
