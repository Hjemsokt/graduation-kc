package cn.kc.graduation.rss.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {
	private static final String configFile = "mybatis-config.xml";
	private static SqlSessionFactory factory;

	 static  {
		try {
			InputStream is = Resources.getResourceAsStream(configFile);
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建连接
	 */
	public static SqlSession getSession() {
		while (true) {
			try {
				return factory.openSession( true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void closeSession(SqlSession session) {
		session.close();
	}

	public static void main(String[] args) {
		SqlSession session = MyBatisUtil.getSession();
		System.out.println(session);
		session.close();
		System.out.println(session);
	}
}