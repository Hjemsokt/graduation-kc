package cn.kc.graduation.rss.server;

import cn.kc.graduation.rss.dao.IUserDao;
import cn.kc.graduation.rss.dao.impl.UserDao;
import cn.kc.graduation.rss.model.User;
import org.apache.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;

public class MsgHandler implements Runnable {
	private Logger logger = Logger.getLogger(MsgHandler.class.getName());
	private LinkedBlockingQueue<String> jobQueue;
	private IUserDao userDao;

	public MsgHandler(int queueCapacity) {
		this.jobQueue = new LinkedBlockingQueue<>(queueCapacity);
		userDao = new UserDao();
	}

	protected void put(String msg) throws InterruptedException {
		jobQueue.put(msg);
		System.out.println(jobQueue.isEmpty());
	}

	@Override
	public void run() {
		while (null != jobQueue) {
			try {
				String a = jobQueue.take().split("\r\n")[0];
				System.out.println(a);
				String[] msg =  a.split("\\^");
				System.out.println("name:" + msg[0] + ",age: " + msg[1]);
				User user = new User();
				user.setName(msg[0]);
				user.setAge(Integer.valueOf(msg[1]));
				System.out.println("+++");
				logger.info(user.toString());
				logger.info("insertStockIn record: " + userDao.insertUser(user));

			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}
		}

	}
}
