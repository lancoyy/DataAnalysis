package com.aone.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.aone.algorithm.KeywordExtracter;
import com.aone.algorithmImpl.KeywordExtracterImpl;
import com.aone.dao.TestDao;
import com.aone.daoImpl.TestDaoImpl;
import com.aone.entity.Keyword;
import com.aone.entity.Test;

public class KeywordExtracerTest {
	public static void main(String args[]) {
		
		String url = "jdbc:mysql://127.0.0.1:3306/an?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, "root", "123456");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from test");
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[] {
						"E:/workspace/MyEclipse Professional 2014/DataAnalysis/src/main/webapp/WEB-INF/applicationContext.xml",
						"E:/workspace/MyEclipse Professional 2014/DataAnalysis/src/main/webapp/WEB-INF/springmvc-servlet.xml" });
		KeywordExtracter ke = (KeywordExtracterImpl) ctx
				.getBean("keywordExtracter");
		System.out.println(ke.keywordList2StringList(ke.extractAllWordAndRankDesc("今天天气挺好，出去转转，今天一切去吃饭")));
		for(Keyword kw : ke.extractAllWordAndRankDesc("今天天气挺好，出去转转，今天一切去吃饭")){
			System.out.println(kw.getWord() + ":" + kw.getNature() + ":" + kw.getScore());
		}
	}
}
