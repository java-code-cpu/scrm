package com.situ.scrm.sys.dictionary.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.doamin.Dictionary;
import com.situ.scrm.sys.dictionary.service.DictionaryService;

@RestController
@RequestMapping("dictionary")
public class DictionaryController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX_DICTIONARY = "sys/dictionary/dictionary_index";
	private static final String PAGE_ADD_EDIT_DICTIONARY = "sys/dictionary/dictionary_add_edit";
	
	@Autowired
	private DictionaryService dictionaryservice;
	
	/**
	 * @Title: saveSysResource
	 * @Description:(进首页)
	 * @param sysResource
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX_DICTIONARY);
		return modelAndView;
	}
	
	/**
	 * @Title: saveSysResource
	 * @Description:(进新增或修改页面)
	 * @param sysResource
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView goAddOrEdit(ModelAndView modelAndView) {
		modelAndView.addObject("parentCode", Dictionary.DEFAULT_PARENT_CODE);
		modelAndView.setViewName(PAGE_ADD_EDIT_DICTIONARY);
		return modelAndView;
	}
	
	/**
	 * @Title: saveSysResource
	 * @Description:(进新增子目录)
	 * @param sysResource
	 * @return
	 */
	@GetMapping("/child/{parentId}")
	public ModelAndView goAddChild(@PathVariable Long parentId,ModelAndView modelAndView) {
		Dictionary parentDictionary = dictionaryservice.getDictionaryById(parentId);
		modelAndView.addObject("parentCode", parentDictionary.getSucCode());
		modelAndView.addObject("parentName", parentDictionary.getSucName());
		modelAndView.setViewName(PAGE_ADD_EDIT_DICTIONARY);
		return modelAndView;
	}
	
	/**
	 * @Title: saveSysResource
	 * @Description:(新增资源信息)
	 * @param sysResource
	 * @return
	 */
	@PostMapping
	public Long saveDictionary(Dictionary dictionary) {
		return dictionaryservice.saveDictionary(dictionary);
	}
	/**
	 * @Title: findAllResource
	 * @Description:(查询出所有的资源信息)
	 * @return
	 */
	@GetMapping("/list")
	public LayResult findAllResource() {
		return dictionaryservice.findAllDictionary();
	}
	/**
	 * @Title: doDeleteDictionary
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteDictionary(@PathVariable Long rowId) {
		return dictionaryservice.doDeleteDictionary(rowId);
	}
}
