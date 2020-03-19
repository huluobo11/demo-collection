package com.imooc.utils;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AclUtils {
	
	public static String getDigestUserPwd(String id) {
		String digest = "";
		try {
			digest = DigestAuthenticationProvider.generateDigest(id);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return digest;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException, Exception {
		String id = "imooc:imooc";
		String idDigested = getDigestUserPwd(id);
		System.out.println(idDigested);
	}
}
