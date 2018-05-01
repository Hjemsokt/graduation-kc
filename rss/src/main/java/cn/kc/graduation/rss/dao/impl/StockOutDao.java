package cn.kc.graduation.rss.dao.impl;

import cn.kc.graduation.rss.dao.IStockOutDao;
import cn.kc.graduation.rss.model.StockOutDO;
import cn.kc.graduation.rss.model.Storage;
import cn.kc.graduation.rss.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockOutDao implements IStockOutDao {
	private static final String namespace = "cn.kc.graduation.rss.dao" +
			".IStockOutDao.";
	private static final SqlSession session = MyBatisUtil.getSession();
	private Logger logger = Logger.getLogger(StockOutDao.class.getName());

	/**
	 * 货物出库操作
	 *
	 * @param goodsID      货物ID
	 * @param repositoryID 出库仓库ID
	 * @param number       出库数量
	 * @param customerID   客户ID
	 * @return 返回一个boolean 值，若值为true表示入库成功，否则表示入库失败
	 */
	@Override
	public boolean stockOut(long goodsID, long repositoryID, long number,
							long customerID) {

		// 更新库存记录
		int affectRecords;
		Map map = new HashMap();
		map.put("goodsID", goodsID);
		map.put("repositoryID", repositoryID);

		try {

			List<Storage> storages = selectByGoodsIDAndRepositoryID(map);
			if (null == storages || storages.isEmpty()) {
				logger.warn("goods (" + goodsID + ") is not exists");
				return false;
			} else {
				System.out.println("storage " + storages.get(0));
				map.put("number", storages.get(0).getNumber() - number);
				affectRecords = updateStorage(map);
				// 保存入库记录
				if (affectRecords > 0) {
					StockOutDO stockOutDO = new StockOutDO();
					stockOutDO.setCustomerID(customerID);
					stockOutDO.setGoodID(goodsID);
					stockOutDO.setNumber(number);
					stockOutDO.setPersonInCharge("admin");
					stockOutDO.setRepositoryID(repositoryID);
					stockOutDO.setTime(new Date());
					affectRecords = insertStockOut(stockOutDO);
					session.commit();
					return affectRecords > 0 ? true : false;

				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return false;
		} finally {
			MyBatisUtil.closeSession(session);
		}

	}

	/**
	 * 插入一条新的出库记录
	 *
	 * @param stockOutDO 出库记录
	 * @return 返回一个int值, 代表影响的记录数
	 */
	private int insertStockOut(StockOutDO stockOutDO) {
		return session.insert(namespace + "insert_stockOut", stockOutDO);

	}

	/**
	 * 更新一条库存记录
	 *
	 * @param map, goodsID指定的货物ID, repositoryID 指定的仓库ID, number库存数量
	 * @return 返回一个int值, 代表影响的记录数
	 */

	public int updateStorage(Map map) {
		return session.update(namespace + "update_storage", map);
	}

	/**
	 * 选择指定货物ID和仓库ID的库存信息
	 *
	 * @param map goods货物ID, repositoryID 库存ID
	 * @return 返回所有指定货物ID和仓库ID的库存信息
	 */
	protected List<Storage> selectByGoodsIDAndRepositoryID(Map map) {
		return session.selectList(namespace +
				"selectByGoodsIDAndRepositoryID", map);
	}

}
