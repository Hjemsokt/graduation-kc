package cn.kc.graduation.rss;

import cn.kc.graduation.rss.dao.IStockInDao;
import cn.kc.graduation.rss.dao.impl.StockInDao;

public class StockInTest {
	public static void main(String[] args) {
		IStockInDao stockInDao = new StockInDao();
		System.out.println(stockInDao.stockIn(1015l, 105l, "精酿苹果醋",
				"饮料", "312ml", (double) 300, 1004, 100l));

//		System.out.println(stockInDao.stockIn(1015l, 999l, "花生", "食物", "100g",
//				(double) 10, 1005, 10l));
	}
}
