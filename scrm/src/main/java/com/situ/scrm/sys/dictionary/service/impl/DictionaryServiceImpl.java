package com.situ.scrm.sys.dictionary.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.dao.DictionaryDao;
import com.situ.scrm.sys.dictionary.doamin.Dictionary;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.utils.DAOUtils;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	private DictionaryDao dictionarydao;
	@Autowired
	private SysCountUtils sysCountUtils;

	
	/**
	 * 进修改
	 */
	@Override
	public Dictionary getDictionaryById(Long rowId) {
		Dictionary dictionary = dictionarydao.get(rowId);
		String parentCode = dictionary.getParentCode();
		Dictionary parentDictionary = dictionarydao.getByCode(parentCode);
		String parentName = parentDictionary.getSucName();
		dictionary.setParentName(parentName);
		return dictionary;
	}
	
	@Override
	public Dictionary getAllDictionaryById(Long rowId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Title: findAllDictionary
	 * @Description:(查询所有的资源信息)
	 * @return
	 */
	@Override
	public LayResult findAllDictionary() {
		List<Dictionary> allDictionary = new ArrayList<Dictionary>();
		//将所有的信息build成Map<ParentCode,List<Dictionary>>
		Map<String,List<Dictionary>> dictionaryMap = buildDictionaryMap();
		if(dictionaryMap != null) {
			//先从map中奖一级资源的集合取出。
			List<Dictionary> dictionaryList = dictionaryMap.get(Dictionary.DEFAULT_PARENT_CODE);
			//遍历一级资源
			if(dictionaryList != null) {
				for(Dictionary dictionary : dictionaryList) {
					//判断是否有子数据
					Integer hasChild = dictionary.getHasChild();
					//如果有子数据
					if(hasChild == 1) {
						//通过递归的方法（重复调用本身）将所有的资源的子数据处理成功。
						callBackChildList(dictionary,dictionaryMap);
					}
					allDictionary.add(dictionary);
				}
			}
		}
		return new LayResult(200, "ok", allDictionary);
	}
	
	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据)
	 * @param parentCode
	 * @param resourceMap
	 * @return
	 */
	private void callBackChildList(Dictionary dictionary, Map<String, List<Dictionary>> dictionaryMap) {
		String parentCode = dictionary.getSucCode();
		List<Dictionary> childList = dictionaryMap.get(parentCode);
		if (childList != null) {
			for (Dictionary child : childList) {
				if (child.getHasChild() == 1) {
					callBackChildList(child, dictionaryMap);
				}
			}
			// 将拿到的子数据设置进去。
			dictionary.setChildren(childList);
		}
	}
	
	/**
	 * @Title: buildDictionaryMap
	 * @Description:(将资源数据转换成MAP格式的数据)
	 * @param resourceList
	 * @return Map<parentCode,List<SysResource>>
	 */
	private Map<String, List<Dictionary>> buildDictionaryMap() {
		// 将系统资源所有的数据都查询出来。
		List<Dictionary> dictionaryList = dictionarydao.find();
		Map<String, List<Dictionary>> dictionaryMap = new HashMap<String, List<Dictionary>>(); //查询出来的数据放到Map中
		if (dictionaryList != null) {
			for (Dictionary dictionary : dictionaryList) {//遍历取出来的数据
				String parentCode = dictionary.getParentCode();  //得到parentCode作为Map的主键
				List<Dictionary> list = dictionaryMap.get(parentCode);  //通过parentCode取出系统资源数据
				if (list == null) {
					list = new ArrayList<Dictionary>();
				}
				list.add(dictionary);   //遍历出的所有数据放到list里面
				dictionaryMap.put(parentCode, list);  //用parentCode作为key   list作为value   放进Map里面
			}
		}
		return dictionaryMap;
	}

	/**
	 * @Title: saveSysResource
	 * @Description:(新增资源信息)
	 * @param sysResource
	 * @return
	 */
	@Override
	@Transactional
	public Long saveDictionary(Dictionary dictionary) {
		String sucCode = sysCountUtils.buildRescCode();
		dictionary.setSucCode(sucCode);
		//根绝父类数据处理排序问题。
		String parentCode = dictionary.getParentCode();
		Integer maxOrder = dictionarydao.getMaxOrder(parentCode);
		maxOrder = maxOrder == null ? 0 : maxOrder;
		dictionary.setSucOrder(maxOrder + 1);
		//如果不是默认的ParentCode，则需要对parent类更新有子类数据信息
		if(!Dictionary.DEFAULT_PARENT_CODE.equals(parentCode)) {
			dictionarydao.updateHasChild(parentCode, 1);
		}
		dictionary.setHasChild(0);
		dictionary.setActiveFlag(1);
		dictionary.setCreateBy("sys");
		dictionary.setCreateDate(new Date());
		dictionarydao.save(dictionary);
		return dictionary.getRowId();
	}

	/**
	 * @Title: updateSysResource
	 * @Description:(修改资源信息)
	 * @param sysResource
	 * @return
	 */
	@Override
	public Long updateDictionary(Dictionary dictionary) {
		Long rowId = dictionary.getRowId();
		Dictionary editDictionary = dictionarydao.get(rowId);
		editDictionary = DAOUtils.buildEditData(editDictionary, dictionary);
		editDictionary.setUpdateBy("sys");
		editDictionary.setUpdateDate(new Date());
		dictionarydao.update(editDictionary);
		return rowId;
	}

	@Override
	public Integer checksucName(String sucName, String parentCode) {
		Dictionary dictionary = dictionarydao.getByNameAndParent(sucName, parentCode);
		return dictionary == null ? 0 : 1;
	}
	
	/**
	 * @Title: saveSysResource
	 * @Description:(删除)
	 * @param sysResource
	 * @return
	 */
	@Override
	public Integer doDeleteDictionary(Long rowId) {
		Dictionary deleteDictionary = dictionarydao.get(rowId);
		String sucCode = deleteDictionary.getSucCode();
		String parentCode = deleteDictionary.getParentCode();
		dictionarydao.delete(rowId);
		//如果不是默认的父类
		if(!parentCode.equals(Dictionary.DEFAULT_PARENT_CODE)) {
			//修改父类的资源是否有子元素
			Integer hasChild = 0;
			List<Dictionary> dictionaryList = dictionarydao.findByParent(parentCode);
			if(dictionaryList != null && !dictionaryList.isEmpty()) {
				hasChild = 1;
			}
			dictionarydao.updateHasChild(parentCode, hasChild);
		}
		return 1;
	}

}
