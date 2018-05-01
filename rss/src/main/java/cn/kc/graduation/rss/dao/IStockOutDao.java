package cn.kc.graduation.rss.dao;

public interface IStockOutDao {
	/**
	 * 货物出库操作
	 *
	 * @param goodsID      货物ID
	 * @param repositoryID 出库仓库ID
	 * @param number       出库数量
	 * @param customerID   客户ID
	 * @return 返回一个boolean 值，若值为true表示入库成功，否则表示入库失败
	 */
	boolean stockOut(long goodsID, long repositoryID, long number,
					 long customerID);
}
