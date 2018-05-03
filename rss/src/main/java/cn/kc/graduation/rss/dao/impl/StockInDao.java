package cn.kc.graduation.rss.dao.impl;

import cn.kc.graduation.rss.dao.IStockInDao;
import cn.kc.graduation.rss.model.Goods;
import cn.kc.graduation.rss.model.StockInDO;
import cn.kc.graduation.rss.model.Storage;
import cn.kc.graduation.rss.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockInDao implements IStockInDao {
	private Logger logger = Logger.getLogger(StockInDO.class.getName());
	private static final String namespace = "cn.kc.graduation.rss.dao" +
			".IStockInDao.";
	private static SqlSession session ;

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
	@Override
	public boolean stockIn(long supplierID, long goodsID, String name,
				String type, String size, double value,
		long repositoryID, long number) {
		session = MyBatisUtil.getSession();

		// 检查入库数量有效性
		if (number < 0) {
			return false;
		}
		// 更新库存记录
		int affectRecords;
		Map map = new HashMap();
		map.put("goodsID", goodsID);
		map.put("repositoryID", repositoryID);

		try {
			if (!goodsValidate(goodsID)) {
				Goods goods = new Goods();
				goods.setId(goodsID);
				goods.setName(name);
				goods.setSize(size);
				goods.setType(type);
				goods.setValue(value);
				affectRecords =
						session.insert(namespace + "insert_goods", goods);
				if (0 >= affectRecords) {
					return false;
				}
			}

			List<Storage> storages = selectByGoodsIDAndRepositoryID(map);
			if (null == storages || storages.isEmpty()) {
				map.put("number", number);
				affectRecords = addNewStorage(map);
			} else {
				System.out.println("storage " + storages.get(0));
				map.put("number", number + storages.get(0).getNumber());
				affectRecords = updateStorage(map);
			}

			// 保存入库记录
			if (affectRecords > 0) {
				StockInDO stockInDO = new StockInDO();
				stockInDO.setGoodID(goodsID);
				stockInDO.setSupplierID(supplierID);
				stockInDO.setNumber(number);
				stockInDO.setPersonInCharge("admin");
				stockInDO.setTime(new Date());
				stockInDO.setRepositoryID(repositoryID);
				affectRecords = insertStockIn(stockInDO);
				session.commit();
				return affectRecords > 0 ? true : false;
			}
			return false;


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return false;
		} finally {
			MyBatisUtil.closeSession(session);
		}
	}


	/**
	 * 检查货物ID对应的记录是否存在
	 *
	 * @param goodsID 货物ID
	 * @return 若存在则返回true，否则返回false
	 */

	private boolean goodsValidate(Long goodsID) {
		List<Goods> a = session.selectList(namespace + "selectGoodsById",
				goodsID);
		boolean ret = null == a || a.isEmpty() ;
		logger.debug("good isExist (" +  !ret + ")");
		return !ret;
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

	/**
	 * 添加一条库存记录
	 *
	 * @param map, goodsID指定的货物ID, repositoryID 指定的仓库ID, number库存数量
	 * @return 返回一个int值, 代表影响的记录数
	 */

	protected int addNewStorage(Map map) {
		return session.update(namespace + "insert_storage", map);
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
	 * 添加一条新的入库记录
	 *
	 * @param stockInDO 入库记录
	 * @return 返回一个int值, 代表影响的记录数
	 */
	public int insertStockIn(StockInDO stockInDO) {
		return session.insert(namespace + "insert_stockIn", stockInDO);
	}
}



