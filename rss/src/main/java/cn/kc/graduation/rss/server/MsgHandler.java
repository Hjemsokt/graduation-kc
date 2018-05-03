package cn.kc.graduation.rss.server;

import cn.kc.graduation.rss.dao.IStockInDao;
import cn.kc.graduation.rss.dao.IStockOutDao;
import cn.kc.graduation.rss.dao.IUserDao;
import cn.kc.graduation.rss.dao.impl.StockInDao;
import cn.kc.graduation.rss.dao.impl.StockOutDao;
import cn.kc.graduation.rss.dao.impl.UserDao;
import cn.kc.graduation.rss.model.Record;
import cn.kc.graduation.rss.model.StockInDO;
import cn.kc.graduation.rss.model.User;
import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.api.scripting.JSObject;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class MsgHandler implements Runnable {
	private Logger logger = Logger.getLogger(MsgHandler.class.getName());
	private LinkedBlockingQueue<String> jobQueue;
	private IStockInDao stockInDao;
	private IStockOutDao stockOutDao;
	private ObjectMapper mapper;


	public MsgHandler(int queueCapacity) {
		this.jobQueue = new LinkedBlockingQueue<>(queueCapacity);
		stockInDao = new StockInDao();
		stockOutDao = new StockOutDao();
		mapper = new ObjectMapper();
	}

	protected void put(String msg) throws InterruptedException {
		jobQueue.put(msg);
	}

	@Override
	public void run() {
		while (null != jobQueue) {
			try {
				String a = jobQueue.take();
				System.out.println(a);
				Record record = mapper.readValue(a, Record.class);
				if (null == record.getNumber()) {
					record.setNumber(1L);
				}
				System.out.println(record);
				boolean isSucc;
				switch (record.getStockType()) {
					case In:
						isSucc = stockInDao.stockIn(
								record.getSupplierID(),
								record.getId(),
								record.getName(),
								record.getType(),
								record.getSize(),
								record.getValue(),
								record.getRepositoryID(),
								record.getNumber());
						logger.info("StockIn record(" + record + "), is " + isSucc);
						break;
					case Out:
						isSucc = stockOutDao.stockOut(
								record.getId(),
								record.getRepositoryID(),
								record.getNumber(),
								record.getCustomerID());
						logger.info("StockOut record(" + record + "), is " + isSucc);

						break;
					default:
						logger.warn("type error");
						break;
				}

			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
