package com.situ.scrm.sys.system.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.system.domain.System1;
import com.situ.scrm.sys.system.service.SystemService;

@RestController
@RequestMapping("/system")
public class SystemController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_SYSTEM_INDEX = "sys/system/system_index";
	
	@Autowired
	private SystemService systemservice;
	
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		System1 system = systemservice.fingById();
		modelAndView.addObject("highSeas", system.getHighSeas());
		modelAndView.addObject("docReminder", system.getDocReminder());
		modelAndView.addObject("config", system.getConfig1());
		modelAndView.addObject("rowId", system.getRowId());
		modelAndView.setViewName(PAGE_SYSTEM_INDEX);
		return modelAndView;
	}
	
	@PutMapping
	public Long update(System1 system) {
		System.out.println(system);
		systemservice.update(system);
		return null;
	}
}
