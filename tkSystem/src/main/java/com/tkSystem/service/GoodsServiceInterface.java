package com.tkSystem.service;

import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.WyMap;

public interface GoodsServiceInterface {

	/**
	 * 物料管理 物料新增
	 * 
	 * @return
	 */
	RetCode post(WyMap paMap);

	/**
	 * 物料管理 物料查询
	 * 
	 * @return
	 */
	RetCode get(WyMap paMapd);

	/**
	 * 物料管理 物料修改
	 * 
	 * @return
	 */
	RetCode put(WyMap paMap);

	/**
	 * 物料管理 物料删除
	 * 
	 * @return
	 */
	RetCode delete(WyMap paMap);

	Object s(WyMap wyMap1);

	Object ss(WyMap wyMap1);

	Object getGoodsByManageDetail(WyMap wyMap1);

}