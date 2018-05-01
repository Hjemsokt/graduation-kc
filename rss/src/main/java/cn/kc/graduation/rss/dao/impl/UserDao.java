package cn.kc.graduation.rss.dao.impl;

import cn.kc.graduation.rss.dao.IUserDao;
import cn.kc.graduation.rss.model.User;
import cn.kc.graduation.rss.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

public class UserDao implements IUserDao {

	private static final String namespace = "wms.dao.IUserDao.";
	private static final SqlSession session = MyBatisUtil.getSession();

	@Override
	public List<User> findAllUsers() {
		List<User> users = null;
		try {
			users = session.selectList(namespace + "findAllUsers", User.class);
			//注意：此处有陷阱，如果做了更新、插入或删除操作，必须使用：
			//session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSession(session);
		}
		return users;
	}

	@Override
	public int insertUser(User user) {
		int ret = 0;
		SqlSession session = MyBatisUtil.getSession();
		try {
		ret = session.insert(namespace + "insertUser", user);
			//注意：此处有陷阱，如果做了更新、插入或删除操作，必须使用：
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSession(session);
		}
		return ret;
	}
}