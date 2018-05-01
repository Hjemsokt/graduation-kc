package cn.kc.graduation.rss;

import cn.kc.graduation.rss.dao.IStockInDao;
import cn.kc.graduation.rss.dao.IStockOutDao;
import cn.kc.graduation.rss.dao.impl.StockInDao;
import cn.kc.graduation.rss.dao.impl.StockOutDao;

public class StockOutTest {
	public static void main(String[] args) {
		IStockOutDao stockOutDao = new StockOutDao();
		System.out.println(stockOutDao.stockOut(105, 1004,
				1, 1214));
	}
}
