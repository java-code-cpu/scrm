/**
 * @Company:中享思途   
 * @Title:AuthTag.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午12:51:56     
 */
package com.situ.scrm.commons.auth;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.situ.scrm.utils.AppConfig;

/**
 * @ClassName:AuthTag
 * @Description:(权限管理的自定义标签)
 */
public class AuthTag extends SimpleTagSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SEPARATOR = "/";
	// 判断传入的值(实际为URL)是否在用户的权限列表中存在,如果存在则显示标签内的内容,否则不显示(一般为链接或是动作按钮)
	private String url;// 需要检测的URL
	private String method;// 需要检测的method方法

	/**
	 * @Title: doTag
	 * @Description:(标签渲染时调用此方法)
	 * @throws JspException
	 * @throws IOException
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// 获取HttpSession对象
		HttpSession session = ((PageContext) this.getJspContext()).getSession();
		// 在SimpleTagSupport类中存在一个getJspBody()方法,此方法返回的就是一个Fragment对象,利用此对象的invoke()方法即可完成标签体内容的输出
		JspFragment jspFragment = getJspBody();
		// 判断是否满足显示标签内的内容,如果true,则显示自定义标签中的内容。否则不显示。
		if (checkInvoke(session)) {
			jspFragment.invoke(null);
		}
	}

	/**
	 * @Title: checkInvoke
	 * @Description:(判断是否满足显示标签内的内容)
	 * @param session HttpSession对象
	 * @return
	 */
	private Boolean checkInvoke(HttpSession session) {
		// 首先检测是否为超级角色,超级角色不做权限检测
		// 如果是超级角色,直接返回true
		if (checkIsSuper(session)) {
			return true;
		} else {// 否则进行URL的检测,所提供的URL是否在用户的权限列表中
			// 取出放置在HttpSession中的用户权限URL集合
			Object objectResourceMap = session.getAttribute(AppConfig.SESSION_RESOURCE_MAP_ROLE);
			if (objectResourceMap != null) {
				@SuppressWarnings("unchecked")
				Map<String, Map<String, List<String>>> resourceMap = (Map<String, Map<String, List<String>>>) objectResourceMap;
				if (resourceMap != null) {
					if (url != null) {
						String checkUrl = url.trim();
						if (url.startsWith(SEPARATOR)) {
							checkUrl = url.replaceFirst(SEPARATOR, "");
						}
						// 如果URL的第一个字符为反斜杠,则尝试去掉;
						String firstUrl = checkUrl;
						if (checkUrl.contains(SEPARATOR)) {
							firstUrl = checkUrl.substring(0, checkUrl.indexOf(SEPARATOR));
						}
						Map<String, List<String>> listMap = resourceMap.get(firstUrl);
						if (listMap != null) {
							List<String> urlList = listMap.get(method.toUpperCase());
							// 判断如果提供的URL在用户的权限URL集合中,则返回true.
							if (urlList != null && !urlList.isEmpty()) {
								for (String regex : urlList) {
									if (checkUrl.matches(regex)) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * @Title: checkIsSuper
	 * @Description:(首先检测是否为超级角色,超级角色不做权限检测)
	 * @param session
	 * @return
	 */
	private boolean checkIsSuper(HttpSession session) {
		// 首先检测是否为超级角色,超级角色不做权限检测
		Boolean isSuper = false;// 是否为超级角色#true:是;false:不是;
		Object objectSupper = session.getAttribute(AppConfig.SESSION_IS_SUPPER);
		if (objectSupper != null) {
			if (Boolean.parseBoolean(objectSupper.toString())) {
				isSuper = true;
			}
		}
		return isSuper;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
