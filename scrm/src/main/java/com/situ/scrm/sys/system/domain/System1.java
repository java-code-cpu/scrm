package com.situ.scrm.sys.system.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

@Alias("System")
public class System1 extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	private String highSeas; // 公海天数
	private String docReminder; // 跟单提醒
	private String config1; // 公司名称

	public String getHighSeas() {
		return highSeas;
	}

	public void setHighSeas(String highSeas) {
		this.highSeas = highSeas;
	}

	public String getDocReminder() {
		return docReminder;
	}

	public void setDocReminder(String docReminder) {
		this.docReminder = docReminder;
	}

	public String getConfig1() {
		return config1;
	}

	public void setConfig1(String config1) {
		this.config1 = config1;
	}

	public System1(String highSeas, String docReminder, String config1) {
		super();
		this.highSeas = highSeas;
		this.docReminder = docReminder;
		this.config1 = config1;
	}

	public System1() {
	}

	@Override
	public String toString() {
		return "System1 [highSeas=" + highSeas + ", docReminder=" + docReminder + ", config1=" + config1 + "]";
	}

}
