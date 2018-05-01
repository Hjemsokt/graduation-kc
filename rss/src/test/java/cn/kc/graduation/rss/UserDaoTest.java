package cn.kc.graduation.rss;


import cn.kc.graduation.rss.dao.IUserDao;
import cn.kc.graduation.rss.dao.impl.UserDao;
import cn.kc.graduation.rss.model.User;

import java.util.List;

public class UserDaoTest {

	public static void main(String[] args) {
		IUserDao userDao = new UserDao();
		List<User> users= userDao.findAllUsers();

		for(User user : users){
			System.out.println(user.toString());
		}
		User user = new User();
		user.setName("gzc");
		user.setAge(1);
		System.out.println(userDao.insertUser(user));
	}

}