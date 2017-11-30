package com.ss.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
		/*
		 * /login.jsp=anon /shiro/login=anon /shiro/logout=logout /user.jsp=roles[user]
		 * /admin.jsp=roles[admin] /**=authc
		 */
		// 这里应该是从数据库中查询出url和权限的对应关系。
		// 这里用LinkedHashMap，是因为这些对应 关系是有顺序的，url的匹配会采用第一匹配策略。 
		hashMap.put("/login.jsp", "anno");
		hashMap.put("/shiro/login", "anno");
		hashMap.put("/shiro/logout", "logout");
		hashMap.put("/**", "authc");
		return hashMap;
	}
}
