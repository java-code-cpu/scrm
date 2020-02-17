/**
 * @Company:中享思途   
 * @Title:Test.java 
 * @Author:wxinpeng   
 * @Date:2020年2月13日 下午1:10:00     
 */
package com.situ.scrm.utils;

/**
 * @ClassName:Test
 * @Description:(这里用一句话描述这个类的作用)
 */
public class Test {

	public static void main(String[] args) {
		
		//<auth:have url="role/1" method="get">
		
		String url = "role/{rowId}";
		String checkUrl = "role/1";
		//将数据库中提供的带着占位符的url数据，通过字符替换的方式，让其转成正则的格式，方便后期匹配。
		String regex = url.replaceAll("\\{\\w*\\}", "\\\\w+");
		System.out.println(regex);
		boolean bool = checkUrl.matches(regex);
		System.out.println(bool);
	}

}
