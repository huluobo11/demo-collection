
package com.shiro.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class ShiroTest {
	// 认证、
	@Test
	public void testAuthenticate() {
		Factory<SecurityManager> SecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = SecurityManagerFactory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123456");
		try {
			currentUser.login(usernamePasswordToken);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
		} catch (LockedAccountException e) {
			e.printStackTrace();
		} catch (ExpiredCredentialsException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, currentUser.isAuthenticated()); // 断言用户已经登录
		// 6、退出
		currentUser.logout();

	}

	// 授权
	@Test
	public void testAuthorize() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		subject.login(token);
		boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
		//subject.checkRole("role6");// 验证不通过，抛出异常。
		System.out.println("基于角色,是否有角色：" + subject.hasRole("role1"));
		boolean permittedAll = subject.isPermittedAll("user:create:1", "user:update:1");
		//subject.checkPermission("user:cr:1");
		System.out.println("基于资源,是否有权限访问资源：" + subject.isPermitted("user:create"));
	}

	// 测试 自定义realm的资源授权
	@Test
	public void testRealmAuthorize() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		subject.login(token);
		boolean permittedAll = subject.isPermittedAll("user:create:1", "item:add:1");
		// isPermitted()方法执行时，就会查询输入的权限是否存在于 realm中的doGetAuthorizationInfo()的返回值中的权限列表中。
		System.out.println("基于资源,是否有权限访问资源：" + subject.isPermitted("user:create"));
		System.out.println(permittedAll);
		//subject.checkPermission("user:c1r:1");
	}

	@Test
	public void tes() throws NoSuchAlgorithmException, UnsupportedEncodingException {//
		// 1111 0000
		System.out.println(new Integer(240).byteValue());
		System.out.println(0x29a);
		System.out.println(16 * 16 * 2 + 16 * 9 + 10);
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String str = "123456";
		md5.reset();
		byte[] ret = md5.digest(str.getBytes("UTF-8"));
		System.out.println(Hex.encodeToString(ret));

	}
}
