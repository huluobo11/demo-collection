package com.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		int[] ids = { 1001, 1002, 1003 };
		String sql = "select * from item where itemId in(";
		for (int i = 0; i < ids.length; i++) {
			sql += "?,";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";
		System.out.println(sql);

		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://192.168.1.105:3306/shiro?characterEncoding=utf-8";
		Connection connection = DriverManager.getConnection(url, "root", "root");
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		for (int i = 0; i < ids.length; i++) {
			prepareStatement.setObject(i, ids[i]);
		}
		ResultSet resultSet = prepareStatement.executeQuery();
		FileInputStream fileInputStream = new FileInputStream(new File(""));
		System.out.println(0x29a);
	}
}
