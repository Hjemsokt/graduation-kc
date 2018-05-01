package cn.kc.graduation.rss.dao;
import cn.kc.graduation.rss.model.User;

import java.util.List;

public interface IUserDao {
	public List<User> findAllUsers();
	public int insertUser(User user);
}