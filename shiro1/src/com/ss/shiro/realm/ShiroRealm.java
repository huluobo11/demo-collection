package com.ss.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm {
	// 认证用的方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[firstRealm] doGetAuthenticationInfo:" + token.hashCode());
		// 1.把AuthenticationToken转换成UsernamePasswordToken
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		// 2.从UsernamePasswordToken中取出username
		String username = uptoken.getUsername();
		// 3.调用数据库的方法，从数据库中查询username对应和用户记录
		System.out.println("从数据库中查询 username对应的user信息");
		// 4.如果不存在，可以抛出UnknowAccountException
		if ("unknown".equals(username)) {
			throw new UnknownAccountException("用户不存在！");
		}
		// 5.根据用户的信息，决定是否要抛出其他的AuthentticationException
		if ("monster".equals(username)) {
			throw new LockedAccountException("用户已经 被锁定！");
		}
		// 6.根据用户的情况来构建AuthenticationInfo 对象并返回。

		// 以下信息是从数据库中获取的
		// 1>principal：认证的实体信息，可以是username，也可以是数据库中对应的实体类对象
		Object principal = username;
		// 2>credentials：密码
		// Object credentials = "123456";
		Object credentials = null;
		if ("user".equals(username)) {
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}
		if ("admin".equals(username)) {
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}
		// 3>realmName：当前realm对象的name,调用父类 的getName就可以。
		String realmName = getName();
		// 4>盐值salt
		ByteSource salt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = null;
		info = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
		return info;
	}

	public static void main(String[] args) {
		String algorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;
		SimpleHash simpleHash = new SimpleHash(algorithmName, credentials, salt, hashIterations);
		System.out.println(simpleHash);

	}

	// 授权用的方法。
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 1.从principalcollection中取出登录用户的信息
		Object principal=principals.getPrimaryPrincipal();
		// 2.利用 登录用户的信息来获取当前用户的角色或者权限（可能用户信息中没有，需要查询数据库）
		Set<String>roles=new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)) {
			roles.add("admin");
		}
		// 3、创建 SimpleAuthorizationInfo对象 ，并设置其roles属性，然后返回他。
		AuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo(roles);
		return authorizationInfo;
	}
}
