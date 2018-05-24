package com.tkSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tkSystem.dao.entity.TkGood;
import com.tkSystem.dao.entity.TkGoodApplyGood;
import com.tkSystem.dao.mapper.TkGoodApplyGoodMapper;
import com.tkSystem.dao.mapper.TkGoodMapper;
import com.tkSystem.dao.mapper.TkPlanDetailMapper;
import com.tkSystem.dao.mapper.TkTargetReportMapper;
import com.tkSystem.dao.mapper.TkUserGradeMapper;
import com.tkSystem.dao.mapper.TkUserMapper;
import com.tkSystem.service.GoodsServiceInterface;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Service("goodsService")
public class GoodsService implements GoodsServiceInterface {
	@Resource
	private TkGoodMapper TkGoodDao;
	@Resource
	private TkGoodApplyGoodMapper TkGoodApplyGoodDao;
	@Resource
	private TkUserGradeMapper TkUserGradeDao;
	@Resource
	private TkUserMapper TkUserDao;
	@Resource
	private TkPlanDetailMapper TkPlanDetailDao;
	@Resource
	private TkTargetReportMapper TkTargetReportDao;

	/* (non-Javadoc)
	 * @see com.tkSystem.service.GoodsServiceInterface#post(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode post(WyMap paMap) {
		int ret = TkGoodDao.insertSelective(paMap);
		if (ret > 0)
			return RetCode.getSuccessCode();
		return RetCode.getErrorCode();
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.GoodsServiceInterface#get(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode get(WyMap paMapd) {
		TkGood ret = TkGoodDao.selectByPrimaryKey(paMapd);
		if (ret != null)
			return RetCode.getSuccessCode(ret);
		return RetCode.getErrorCode();
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.GoodsServiceInterface#put(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode put(WyMap paMap) {
		int ret = TkGoodDao.updateByPrimaryKey(paMap);
		if (ret > 0)
			return RetCode.getSuccessCode();
		return RetCode.getErrorCode();
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.GoodsServiceInterface#delete(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode delete(WyMap paMap) {
		int ret = TkGoodDao.deleteByPrimaryKey(paMap);
		if (ret > 0)
			return RetCode.getSuccessCode();
		return RetCode.getErrorCode();
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.GoodsServiceInterface#s(com.tkSystem.tools.WyMap)
	 */
	@Override
	public Object s(WyMap wyMap1) {
		RetCode ret = RetCode.getErrorCode();
		// 经理获取拓客信息
		List<WyMap> listTkUserGrade = TkUserGradeDao.selectBySelective(wyMap1);
		wyMap1.put("listTkUserGrade", listTkUserGrade);
		List<WyMap> listTkUser = TkUserDao.selectBySelective(wyMap1);
		wyMap1.put("listTkUser", listTkUser);
		// 经理获取报备信息
		List<WyMap> listTkGoodApply = TkGoodApplyGoodDao.selectBySelective(wyMap1);
		List<WyMap> listTkGoodApplys = new ArrayList();
		for (WyMap wyMap : listTkGoodApply) {
			String tk_good_apply_user_id = wyMap.get("tk_good_apply_user_id").toString();
			String tkuserName = null, tkUserhead = null;
			for (WyMap wyMa : listTkUser) {
				String tk_user_id = wyMa.get("tk_user_id").toString();
				boolean bool = tk_user_id.equals(tk_good_apply_user_id);
				if (bool) {
					if (wyMa.get("tk_user_name") == null) {
						tkuserName = "null";
					} else {
						tkuserName = wyMa.get("tk_user_name").toString();
					}
					if (wyMa.get("tk_user_head") == null) {
						tkUserhead = "null";
					} else {
						tkUserhead = wyMa.get("tk_user_head").toString();
					}
					break;
				}
			}
			wyMap.put("tkuserName", tkuserName);
			wyMap.put("tkUserhead", tkUserhead);
			wyMap.put("tk_plan_detail_id", wyMap.get("tk_good_apply_plan_id").toString().trim());
			WyMap MapTkPlanDetail = TkPlanDetailDao.getPlanDetailByPlanId(wyMap);
			if (MapTkPlanDetail == null) {
				wyMap.put("tk_plan_detail_title", "null");
			} else {
				wyMap.put("tk_plan_detail_title", MapTkPlanDetail.get("tk_plan_detail_title").toString().trim());
			}
			listTkGoodApplys.add(wyMap);
		}
		// 经理获取任务
		ret = RetCode.getSuccessCode(listTkGoodApplys);
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.GoodsServiceInterface#ss(com.tkSystem.tools.WyMap)
	 */
	@Override
	public Object ss(WyMap wyMap1) {
		RetCode ret = RetCode.getErrorCode();
		String tk_good_apply_plan_id;
		try {
			/*
			 * // 获取物料申请信息(物料goodid,物料数量amount) String goodid = ""; String amount = ""; if
			 * (!wyMap1.get("tk_good_apply_plan_id").toString().isEmpty()) { TkGoodApplyGood
			 * TkGoodApplyGood = TkGoodApplyGoodDao
			 * .selectByPrimaryKey(wyMap1.get("tk_good_apply_plan_id").toString()); if
			 * (TkGoodApplyGood == null) { throw new
			 * Exception(String.format("当前任务（编号为%s，TkGoodApplyGood）不存在为空",
			 * wyMap1.get("tk_good_apply_plan_id").toString())); } else { goodid =
			 * TkGoodApplyGood.getTkGoodApplyGoodAmount(); amount =
			 * TkGoodApplyGood.getTkGoodApplyGoodId(); } } else { throw new
			 * Exception("tk_good_apply_plan_id为空"); } // 修改物料信息 if (goodid.isEmpty()) {
			 * WyMap map = new WyMap(); map.put("tkGoodId", goodid); TkGood TkGood =
			 * TkGoodDao.selectByPrimaryKey(map); if (TkGood == null) { throw new
			 * Exception(String.format("当前物料（编号为%s，TkGood）不存在",
			 * wyMap1.get("goodid").toString())); } else { // 物料增减操作 int re =
			 * Integer.parseInt(TkGood.getTkGoodAmount()) - Integer.parseInt(amount);
			 * if(re<0) { throw new Exception("仓库物料已经不满足物料申请"); } } } else { throw new
			 * Exception("申请的物料不存在仓库中"); }
			 */
			WyMap wyMap = new WyMap();
			wyMap.put("tkGoodApplyState", wyMap1.get("tkGoodApplyState").toString());
			wyMap.put("tkGoodApplyId", wyMap1.get("tk_good_apply_id").toString());

			// 修改报备信息
			int s = TkGoodApplyGoodDao.updateBySelective(wyMap);
			if (s > 0)
				ret = RetCode.getSuccessCode(wyMap);
		} catch (Exception e) {
			ret.put("msg", e.getMessage());
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.GoodsServiceInterface#getGoodsByManageDetail(com.tkSystem.tools.WyMap)
	 */
	@Override
	public Object getGoodsByManageDetail(WyMap wyMap1) {
		RetCode ret = RetCode.getErrorCode();
		String tk_good_apply_plan_id;
		try {
			// 获取物料申请信息(物料goodid,物料数量amount)
			String goodid = "";
			;
			if (!wyMap1.get("tk_good_apply_id").toString().isEmpty()) {
				WyMap TkGoodApplyGood = TkGoodApplyGoodDao.selectGoodApplyId(wyMap1);
				if (TkGoodApplyGood == null) {
					throw new Exception(String.format("当前任务（编号为%s，TkGoodApplyGood）不存在为空",
							wyMap1.get("tk_good_apply_plan_id").toString()));
				} else {

					goodid = TkGoodApplyGood.get("tk_good_apply_plan_id").toString().trim();

					WyMap w = TkPlanDetailDao.getPlanNameByPlanId(new WyMap("tk_plan_detail_id", goodid));
					TkGoodApplyGood.put("tk_plan_detail_title", w.get("tk_plan_detail_title"));
					ret = RetCode.getSuccessCode(TkGoodApplyGood);
				}
			} else {
				throw new Exception("tk_good_apply_plan_id为空");
			}

		} catch (Exception e) {
			ret.put("msg", e.getMessage());
		}
		return ret;
	}

	
}
