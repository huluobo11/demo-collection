package com.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.auth.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


/**
 * 自定义realm
 * 
 * @author Administrator
 */
public class Realm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String principal = (String) principals.getPrimaryPrincipal();
		// 连接数据库。。。
		// 根据用户名查询出当前用户有的权限，
		List<String> permissions = new ArrayList<>();
		permissions.add("user:create");
		permissions.add("item:add");

		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		Object credentials = usernamePasswordToken.getPassword();
		Object principal = usernamePasswordToken.getUsername();
		ByteSource salt = null;
		String realmName = getName();
		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
		return authenticationInfo;
	}

}