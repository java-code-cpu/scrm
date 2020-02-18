package com.situ.scrm.sys.dictionary.service;

import java.util.List;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.doamin.Dictionary;

public interface DictionaryService {
	
	/**
	 * @Title: getDictionaryById
	 * @Description:(根据主键查询Resource对象)
	 * @param rowId
	 * @return
	 */
	Dictionary getDictionaryById(Long rowId);

	/**
	 * @Title: getAllResourceById
	 * @Description:(根据主键查询Resource对象)
	 * @param rescCode
	 * @return
	 */
	Dictionary getAllDictionaryById(Long rowId);

	/**
	 * @Title: findAllResource
	 * @Description:(查询所有的资源信息)
	 * @return
	 */
	LayResult findAllDictionary();

	/**
	 * @Title: saveDictionary
	 * @Description:(新增资源信息)
	 * @param dictionary
	 * @return
	 */
	Long saveDictionary(Dictionary dictionary);

	/**
	 * @Title: updateDictionary
	 * @Description:(修改资源信息)
	 * @param dictionary
	 * @return
	 */
	Long updateDictionary(Dictionary dictionary);

	/**
	 * @Title: checksuccName
	 * @Description:(检测名称唯一)
	 * @param rescName
	 * @param parentCode
	 * @return
	 */
	Integer checksucName(String sucName, String parentCode);

	/**
	 * @Title: doDeleteSysResource
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteDictionary(Long rowId);
}
