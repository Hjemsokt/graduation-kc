package cn.kc.graduation.rss.dao;



public interface IStockInDao {
	/**
	 * 货物入库操作
	 *
	 * @param supplierID   供应商ID
	 * @param goodsID      货物ID
	 * @param name         货物名称
	 * @param type         货物类型
	 * @param size 		   货物规格
	 * @param value        货物价值
	 * @param repositoryID 入库仓库ID
	 * @param number       入库数量
	 * @return 返回一个boolean 值，若值为true表示入库成功，否则表示入库失败
	 */
    boolean stockIn(long supplierID, long goodsID, String name,
					String type, String size, double value,
					long repositoryID, long number);

}
