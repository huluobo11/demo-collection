package com.ss.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("[SecondRealm]  doGetAuthenticationInfo:" + token.hashCode());
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
			credentials="073d4c3ae812935f23cb3f2a71943f49e082a718";
		}
		if ("admin".equals(username)) {
			credentials="ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
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
		String algorithmName = "SHA1";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");
		int hashIterations = 1024;
		SimpleHash simpleHash = new SimpleHash(algorithmName, credentials, salt, hashIterations);
		System.out.println(simpleHash);

	}
}
