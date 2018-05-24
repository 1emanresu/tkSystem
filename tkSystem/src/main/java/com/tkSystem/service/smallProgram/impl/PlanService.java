package com.tkSystem.service.smallProgram.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tkSystem.dao.entity.TkLocation;
import com.tkSystem.dao.entity.TkPlanPhoto;
import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.dao.mapper.TkLocationMapper;
import com.tkSystem.dao.mapper.TkPlanDetailMapper;
import com.tkSystem.dao.mapper.TkPlanMapper;
import com.tkSystem.dao.mapper.TkTargetReportMapper;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ShortMessageService;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;
import com.tkSystem.dao.mapper.TkUserMapper;
import com.tkSystem.service.smallProgram.PlanServiceInterface;
import com.tkSystem.dao.mapper.TkPlanPhotoMapper;
import com.tkSystem.dao.mapper.TkSystemMapper;

@Transactional(readOnly = true)
@Service("planService")
public class PlanService implements PlanServiceInterface {
	@Resource
	private TkPlanMapper TkPlanDao;
	@Resource
	private TkPlanDetailMapper TkPlanDetailDao;
	@Resource
	private TkUserMapper TkUserMapper;
	@Resource
	private TkPlanPhotoMapper TkPlanPhotoMapper;
	@Resource
	private TkSystemMapper TkSystemMapper;
	@Resource
	private TkTargetReportMapper TkTargetReportDao;
	@Resource
	private TkLocationMapper TkLocationDao;

	/**
	 * 任务管理 (发布任务 ,分配任务,查看拓客人员任务进度、任务总进度,)
	 * 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#postPlan(javax.servlet
	 * .http.HttpServletRequest)
	 */
	@Override
	public RetCode postPlan(HttpServletRequest request) {
		// 任务编号
		String clid = CLID.getID();
		// 请求参数对象
		WyMap paMap = WyMap.getParameter(request);
		WyMap MapTkPlan = paMap;
		// 返回对象
		RetCode retCode = RetCode.getErrorCode();
		String tkPlanDetailPhoto = "";
		if (request.getParameterValues("tkPlanDetailPhoto") == null) {
			if (request.getParameter("tkPlanDetailPhoto") == null
					|| request.getParameter("tkPlanDetailPhoto").trim().equals("")) {
			} else {

			}
		} else {
			StringBuffer sb = new StringBuffer();
			String[] photo = request.getParameterValues("tkPlanDetailPhoto");
			for (String string : photo) {
				// 任务图片编号
				String plan_clid = CLID.getID();
				TkPlanPhoto record = new TkPlanPhoto();
				record.setTkPlanPhotoId(plan_clid);
				record.setTkPlanPhotoUrl(string);
				record.setTkPlanId(clid);
				int iii = TkPlanPhotoMapper.insert(record);
				sb.append(plan_clid + ",");
			}
			String newStr = sb.toString();
			tkPlanDetailPhoto = newStr.substring(0, newStr.length() - 1);
		}
		/**
		 * TkPlan新增
		 */
		paMap.put("tkPlanDetailPhoto", tkPlanDetailPhoto);
		paMap.put("tkPlanDetailId", clid);
		paMap.put("tkPlanDetailUserId", request.getParameter("tkUserId"));
		/**
		 * MapTkPlan
		 */
		MapTkPlan.put("tkPlanId", clid);
		MapTkPlan.put("tkPlanTarget", request.getParameter("tkPlanDetailTarget"));
		MapTkPlan.put("tkPlanState", "1");
		MapTkPlan.put("tkPlanName", request.getParameter("tkPlanDetailTitle"));
		MapTkPlan.put("tkPlanTime", ToolsUtil.getDate());
		MapTkPlan.put("tkPlanUserId", request.getParameter("tkUserId"));
		try {
			paMap = postLocotion(paMap);
			paMap.printStr();
			int i = TkPlanDetailDao.insertSelective(paMap);
			int ii = TkPlanDao.insertSelective(MapTkPlan);
			if (ii > 0 && i > 0) {
				retCode = RetCode.getSuccessCode("插入成功");
				retCode.put("TkPlanDetail", paMap);
				retCode.put("TkPlan", MapTkPlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode();
			throw new RuntimeException();
		} finally {
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#putPlan(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public RetCode putPlan(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			retCode = RetCode.getSuccessCode(TkPlanDao.updateByPrimaryKeySelective(paMap));
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#postPlanExecute(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode postPlanExecute(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			String tkPlanExecuteId = CLID.getID();
			paMap.put("tkPlanExecuteId", tkPlanExecuteId);
			retCode = RetCode.getSuccessCode(TkPlanDao.postPlanExecute(paMap));
			String name, tkUserPhone;
			HashMap Map;
			WyMap paMap1 = new WyMap();
			paMap1.put("tkUserId", paMap.get("tkPlanUserId").toString());
			System.out.println(paMap.get("tkPlanUserId").toString());
			com.tkSystem.dao.entity.TkUser user = TkUserMapper.selectByPrimaryKey(paMap1);
			tkUserPhone = user.getTkUserPhone();
			name = user.getTkUserName();
			Map = ShortMessageService.sendSms(tkUserPhone, new Byte("1"), String.valueOf(name));
			if (Map.get("state").toString().equals("OK")) {
				retCode.put("sendSms", "true");
			}
			/**
			 * 分配任务后更改任务状态
			 */
			WyMap MapTkPlan = new WyMap();
			MapTkPlan.put("tkPlanId", request.getParameter("tkPlanId"));
			MapTkPlan.put("tkPlanState", "3");
			TkPlanDao.updateByPrimaryKeySelective(MapTkPlan);
			/**
			 * 分配任务后更改任务tkuserid
			 */
			WyMap MapTkPlan1 = new WyMap();
			MapTkPlan1.put("tkPlanId", request.getParameter("tkPlanId"));
			MapTkPlan1.put("tk_plan_detail_tkuser_id", request.getParameter("tkPlanUserId"));
			TkPlanDetailDao.updateTkUserId(MapTkPlan1);
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getPlanByUserId(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode getPlanByUserId(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			List<WyMap> list = TkPlanDao.selectByUserId(paMap);
			List<WyMap> list2 = new ArrayList();
			/*
			 * for (WyMap wyMap : list) { String tk_plan_detail_photo =
			 * wyMap.get("tk_plan_detail_photo").toString(); if
			 * (tk_plan_detail_photo.contains("://")) {
			 * 
			 * } else { // 根据tk_plan_photo_id查看 String str =
			 * tk_plan_detail_photo.split(",")[0];
			 * tk_plan_detail_photo=TkPlanPhotoMapper.selectByPrimaryKey(str).
			 * getTkPlanPhotoUrl(); } list2.add(wyMap); }
			 */
			retCode = RetCode.getSuccessCode(list);
			retCode.put("msg", "tk_plan_time as '任务发布时间',tk_plan_name as '任务名称',tk_plan_id as '任务编号' ");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getPlanByUserIdZ(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getPlanByUserIdZ(HttpServletRequest request, HttpServletResponse response) {
		Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);

		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		String time = matter1.format(as);
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			java.util.List<WyMap> list = TkPlanDao.selectByUserId(paMap);
			java.util.List<WyMap> list1 = new ArrayList<>();
			for (WyMap object : list) {
				String str = object.get("tkPlanTime").toString();
				String str0 = object.get("tkPlanState").toString();
				if (str.contains(time) && str0.equals("1")) {
					list1.add(object);
				}
			}
			retCode = RetCode.getSuccessCode(list1);
			retCode.put("msg", "tk_plan_time as '任务发布时间',tk_plan_name as '任务名称',tk_plan_id as '任务编号' ");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getTaskToClock(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode getTaskToClock(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			java.util.List<WyMap> list = TkPlanDetailDao.getTaskToClock(paMap);
			List<WyMap> list2 = new ArrayList();
			for (WyMap wyMap : list) {
				String tk_plan_detail_photo = wyMap.get("tk_plan_detail_photo").toString();
				if (tk_plan_detail_photo.contains("://")) {

				} else {
					// 根据tk_plan_photo_id查看
					String str = tk_plan_detail_photo.split(",")[0];
					tk_plan_detail_photo = TkPlanPhotoMapper.selectByPrimaryKey(str).getTkPlanPhotoUrl();
				}
				list2.add(wyMap);
			}
			retCode = RetCode.getSuccessCode(list2);
			retCode.put("msg",
					"tk_plan_detail_start as '任务开始时间',tk_plan_detail_title as '任务名称',tk_plan_detail_id as '任务编号' ");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getPlanByUserIdJ(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getPlanByUserIdJ(HttpServletRequest request, HttpServletResponse response) {
		Date as = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(as);
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			java.util.List<WyMap> list = TkPlanDao.selectByUserId(paMap);
			java.util.List<WyMap> list1 = new ArrayList<>();
			for (WyMap object : list) {
				String str = object.get("tkPlanTime").toString();
				String str0 = object.get("tkPlanState").toString();
				if (str.contains(time) && str0.equals("1")) {
					list1.add(object);
				}
			}
			retCode = RetCode.getSuccessCode(list1);
			retCode.put("msg", "tk_plan_time as '任务发布时间',tk_plan_name as '任务名称',tk_plan_id as '任务编号' ");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getPlan(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getPlan(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode = RetCode.getErrorCode();
		WyMap wyMap = WyMap.getParameter(request);
		try {
			// 当前用户类型
			String TkUserTypeId = TkUserMapper.selectByPrimaryKey(wyMap).getTkUserTypeId();
			switch (TkUserTypeId) {
			case "0":
				// 拓客人员
				wyMap.put("tk_plan_detail_tkuser_id", wyMap.get("tkUserId").toString());
				break;
			case "1":
				// 超级管理员
				break;
			case "2":
				// 一级经理
				wyMap.put("tk_plan_detail_user_id", wyMap.get("tkUserId").toString());
				break;

			default:
				break;
			}
			List<WyMap> list = TkPlanDao.getAreadyPlan(wyMap);
			if (list == null) {
				throw new Exception("当前 任务不存在");
			}
			List<WyMap> list1 = new ArrayList();
			for (WyMap Map : list) {
				String start, now, end;
				start = Map.get("tk_plan_detail_start").toString();
				now = ToolsUtil.getDate();
				end = Map.get("tk_plan_detail_end").toString();
				String AmongDate = ToolsUtil.getAmongDate(end, now);
				Map.put("AmongDate", AmongDate);
				String tk_plan_detail_photo = Map.get("tk_plan_detail_photo").toString();
				if (tk_plan_detail_photo.contains("://")) {

				} else {
					// 根据tk_plan_photo_id查看
					String str = tk_plan_detail_photo.split(",")[0];
					tk_plan_detail_photo = TkPlanPhotoMapper.selectByPrimaryKey(str).getTkPlanPhotoUrl();
					Map.put("tk_plan_detail_photo", tk_plan_detail_photo);
				}
				list1.add(Map);
			}
			retCode = RetCode.getSuccessCode(list1);
			String str = String.format("tk_plan_detail_title" + "   " + "当前任务名字" + "   "
					+ "tk_plan_detail_target_achieve" + "   " + "当前任务拓客完成人数" + "   " + "tk_plan_state" + "   " + "任务状态"
					+ "   " + "tk_plan_detail_location_detail" + "   " + "任务详细地址" + "   " + "tk_plan_detail_good_name"
					+ "   " + "礼物名字" + "   " + "tk_plan_detail_user_id" + "   " + "用户编号" + "   "
					+ "tk_plan_detail_remark" + "   " + "任务备注" + "   " + "tk_plan_detail_contacts_sex" + "   " + "联系人性别"
					+ "   " + "tk_plan_detail_target" + "   " + "任务目标" + "   " + "tk_plan_detail_location" + "   "
					+ "任务位置" + "   " + "tk_plan_detail_contacts_name" + "   " + "联系人名字" + "   " + "tk_plan_detail_start"
					+ "   " + "任务开始时间" + "   " + "tk_plan_detail_photo" + "   " + "任务图片" + "   "
					+ "tk_plan_contacts_phone" + "   " + "联系人手机号" + "   " + "tk_plan_detail_tkuser_id" + "   " + "拓客人编号"
					+ "   " + "tk_channel_id" + "   " + "渠道编号" + "   " + "tk_plan_detail_id" + "   " + "任务详情编号" + "   "
					+ "tk_plan_detail_good_amount" + "   " + "礼物数量" + "   " + "AmongDate" + "   " + "剩余时间" + "   "
					+ "tk_plan_detail_end" + "   " + "结束时间", "");
			retCode.put("msg", str);
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("失败");
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getPlanThree(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode getPlanThree(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			java.util.List<WyMap> list1 = new ArrayList();

			java.util.List<WyMap> list = TkPlanDao.getPlanThree(paMap);
			for (int i = 0; i < list.size();) {
				WyMap WyMap = list.get(i);
				i++;
				WyMap.put("No", i);
				list1.add(WyMap);
			}

			retCode = RetCode.getSuccessCode(list1);
			retCode.put("msg",
					"No=>排名,tk_client_amount=>拓客数量,tk_user_head=>头像地址,tk_user_phone=>用户手机号码,tk_user_name=>用户名字,tk_user_id=>用户id");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getPlanThreeH(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getPlanThreeH(HttpServletRequest request, HttpServletResponse response) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			java.util.List<WyMap> list1 = new ArrayList();

			java.util.List<WyMap> list = TkPlanDao.getPlanThreeH(paMap);
			for (int i = 0; i < list.size();) {
				WyMap WyMap = list.get(i);
				i++;
				int k = i + 3;
				WyMap.put("No", k);
				list1.add(WyMap);
			}
			retCode = RetCode.getSuccessCode(list1);
			retCode.put("msg",
					"No=>排名,tk_client_amount=>拓客数量,tk_user_head=>头像地址,tk_user_phone=>用户手机号码,tk_user_name=>用户名字,tk_user_id=>用户id");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getEmployeePlan(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode getEmployeePlan(HttpServletRequest request) {
		RetCode retCode;
		WyMap wyMap = WyMap.getParameter(request);
		try {
			retCode = RetCode.getSuccessCode(TkPlanDao.selectByPrimaryKey(wyMap));
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.smallProgram.PlanServiceInterface#getTodayTask(com.
	 * tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode getTodayTask(WyMap paMap) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			List<WyMap> list = TkPlanDetailDao.getTodayTask(paMap);
			List<WyMap> relist = new ArrayList();
			for (WyMap wyMap : list) {
				String tk_plan_detail_photo = wyMap.get("tk_plan_detail_photo").toString();
				if (tk_plan_detail_photo.contains("://")) {

				} else {
					// 根据tk_plan_photo_id查看
					String str = tk_plan_detail_photo.split(",")[0];
					tk_plan_detail_photo = TkPlanPhotoMapper.selectByPrimaryKey(str).getTkPlanPhotoUrl();
				}
				String tk_plan_detail_end = wyMap.get("tk_plan_detail_end").toString();
				String among = ToolsUtil.getAmongDate(tk_plan_detail_end, ToolsUtil.getDate());
				wyMap.put("amongDate", among);
				relist.add(wyMap);
			}
			retCode = RetCode.getSuccessCode(relist);
		} catch (Exception e) {
			retCode = RetCode.getErrorCode("今日任务获取失败!");
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getPlanDetailByPlanId(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getPlanDetailByPlanId(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode = RetCode.getErrorCode();
		WyMap wyMap = WyMap.getParameter(request);
		try {
			wyMap.put("tkPlanId", wyMap.get("tk_plan_detail_id").toString());
			List list = TkSystemMapper.getClientInfoByPlanId(wyMap);
			wyMap = TkPlanDetailDao.getPlanDetailByPlanId(wyMap);
			String tk_plan_detail_end = wyMap.get("tk_plan_detail_end").toString();
			String among = ToolsUtil.getAmongDate(tk_plan_detail_end, ToolsUtil.getDate());
			wyMap.put("amongDate", among);
			wyMap.put("clientInfoArray", list);
			// 获取图片地址
			String tk_plan_detail_photo = wyMap.get("tk_plan_detail_photo").toString();
			if (tk_plan_detail_photo.contains("://")) {

			} else {
				// 根据tk_plan_photo_id查看
				String str = tk_plan_detail_photo.split(",")[0];
				tk_plan_detail_photo = TkPlanPhotoMapper.selectByPrimaryKey(str).getTkPlanPhotoUrl();
			}
			retCode = RetCode.getSuccessCode(wyMap);
			retCode.put("msg", "");
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("失败");
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getAreadyPlan(com.
	 * tkSystem.tools.WyMap)
	 */
	@Override
	public Object getAreadyPlan(WyMap wyMap) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			// 当前用户类型
			String TkUserTypeId = TkUserMapper.selectByPrimaryKey(wyMap).getTkUserTypeId();
			switch (TkUserTypeId) {
			case "0":
				// 拓客人员
				wyMap.put("tk_plan_detail_tkuser_id", wyMap.get("tkUserId").toString());
				break;
			case "1":
				// 超级管理员
				break;
			case "2":
				// 一级经理
				wyMap.put("tk_plan_detail_user_id", wyMap.get("tkUserId").toString());
				break;

			default:
				break;
			}
			List<WyMap> list = TkPlanDao.getAreadyPlan(wyMap);
			if (list == null) {
				throw new Exception("当前 任务不存在");
			}
			List<WyMap> list1 = new ArrayList();
			for (WyMap Map : list) {
				String start, now, end;
				start = Map.get("tk_plan_detail_start").toString();
				now = ToolsUtil.getDate();
				Long l = ToolsUtil.getAmongDateToLong(now, start);
				System.out.print(l < 0);
				if (l < 0) {
					// 将来
					continue;
				}
				end = Map.get("tk_plan_detail_end").toString();
				Long l1 = ToolsUtil.getAmongDateToLong(end, now);
				if (l1 < 0) {
					// 截至日期比现在小
					// 过去
					continue;
				}
				String AmongDate = ToolsUtil.getAmongDate(end, now);
				Map.put("AmongDate", AmongDate);

				String tk_plan_detail_photo = Map.get("tk_plan_detail_photo").toString();
				if (tk_plan_detail_photo.contains("://")) {

				} else {
					// 根据tk_plan_photo_id查看
					String str = tk_plan_detail_photo.split(",")[0];
					tk_plan_detail_photo = TkPlanPhotoMapper.selectByPrimaryKey(str).getTkPlanPhotoUrl();
				}
				list1.add(Map);
			}
			retCode = RetCode.getSuccessCode(list1);
			String str = String.format("tk_plan_detail_title" + "   " + "当前任务名字" + "   "
					+ "tk_plan_detail_target_achieve" + "   " + "当前任务拓客完成人数" + "   " + "tk_plan_state" + "   " + "任务状态"
					+ "   " + "tk_plan_detail_location_detail" + "   " + "任务详细地址" + "   " + "tk_plan_detail_good_name"
					+ "   " + "礼物名字" + "   " + "tk_plan_detail_user_id" + "   " + "用户编号" + "   "
					+ "tk_plan_detail_remark" + "   " + "任务备注" + "   " + "tk_plan_detail_contacts_sex" + "   " + "联系人性别"
					+ "   " + "tk_plan_detail_target" + "   " + "任务目标" + "   " + "tk_plan_detail_location" + "   "
					+ "任务位置" + "   " + "tk_plan_detail_contacts_name" + "   " + "联系人名字" + "   " + "tk_plan_detail_start"
					+ "   " + "任务开始时间" + "   " + "tk_plan_detail_photo" + "   " + "任务图片" + "   "
					+ "tk_plan_contacts_phone" + "   " + "联系人手机号" + "   " + "tk_plan_detail_tkuser_id" + "   " + "拓客人编号"
					+ "   " + "tk_channel_id" + "   " + "渠道编号" + "   " + "tk_plan_detail_id" + "   " + "任务详情编号" + "   "
					+ "tk_plan_detail_good_amount" + "   " + "礼物数量" + "   " + "AmongDate" + "   " + "剩余时间" + "   "
					+ "tk_plan_detail_end" + "   " + "结束时间", "");
			retCode.put("msg", str);
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("失败");
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.smallProgram.PlanServiceInterface#
	 * getReportByManageDetail(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode getReportByManageDetail(WyMap paMap) {
		RetCode ret = RetCode.getErrorCode();
		try {
			String tkTargetReportId = paMap.get("tkTargetReportId").toString();
			if (tkTargetReportId.isEmpty()) {
				throw new Exception("报备编号tkTargetReportId为空");
			} else {
				WyMap map = TkTargetReportDao.getReportByManageDetail(new WyMap("tkTargetReportId", tkTargetReportId));
				if (map == null) {
					throw new Exception(String.format("报备编号tkTargetReportId%s不存在", tkTargetReportId));
				} else {
					WyMap re = map;
					Object tk_plan_id = map.get("tk_plan_id");
					if (tk_plan_id == null) {
						throw new Exception(String.format("报备编号tkTargetReportId%s关联的任务不存在", tkTargetReportId));
					} else {
						WyMap rc = new WyMap("tk_plan_detail_id", tk_plan_id.toString());
						WyMap Plan = TkPlanDetailDao.getPlanDetailByPlanId(rc);
						if (Plan == null) {
							throw new Exception(String.format("报备编号tkTargetReportId%s关联的任务%s不存在", tkTargetReportId,
									tk_plan_id.toString()));
						} else {
							String tk_plan_detail_title = Plan.get("tk_plan_detail_title").toString();
							String tk_plan_detail_start = Plan.get("tk_plan_detail_start").toString();
							re.put("tk_plan_detail_title", tk_plan_detail_title);
							re.put("tk_plan_detail_start", tk_plan_detail_start);
						}
					}
					ret = RetCode.getSuccessCode(re);

					ret.put("msg",
							String.format("查看任务报备的详情 返回说明tk_plan_detail_title：任务名，tk_plan_detail_start：任务开始时间，"
									+ "tk_target_report_id：报备编号" + "，tk_target_reuser_id：经理编号"
									+ "，tk_target_report_remark：理由及备注" + "，tk_target_report_name：报备名"
									+ "，tk_target_report_time：报备时间" + "，tk_plan_id：报备关联的任务编号" + "，tk_user_id：报备人编号"
									+ "，tk_report_state：报备申请状态"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ret.put("msg", e.getMessage());
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#putReportByManage(com.
	 * tkSystem.tools.WyMap)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public RetCode putReportByManage(WyMap paMap) {
		RetCode ret = RetCode.getErrorCode();
		try {
			// 修改任务开始时间
			String tk_plan_detail_start = paMap.get("tk_plan_detail_starttime").toString();
			String tkTargetReportId = paMap.get("tkTargetReportId").toString();
			if (tkTargetReportId.isEmpty()) {
				throw new Exception("报备编号tkTargetReportId为空");
			} else {
				WyMap map = TkTargetReportDao.getReportByManageDetail(new WyMap("tkTargetReportId", tkTargetReportId));
				if (map == null) {
					throw new Exception(String.format("报备编号tkTargetReportId%s不存在", tkTargetReportId));
				} else {
					WyMap re = map;
					Object tk_plan_id = map.get("tk_plan_id");
					if (tk_plan_id == null) {
						throw new Exception(String.format("报备编号tkTargetReportId%s关联的任务不存在", tkTargetReportId));
					} else {
						WyMap plandetail = new WyMap();
						plandetail.put("tk_plan_detail_start", tk_plan_detail_start);
						plandetail.put("tk_plan_detail_id", tk_plan_id);
						TkPlanDetailDao.updateStartDate(plandetail);
						WyMap plan = new WyMap();
						plan.put("tk_plan_time", tk_plan_detail_start);
						plan.put("tk_plan_id", tk_plan_id);
						TkPlanDao.updateStartDate(plan);
					}
					ret = RetCode.getSuccessCode(re);
				}
			}
			// 修改报备状态
			String tk_report_state = paMap.get("tk_report_state").toString();
			String tk_target_report_id = paMap.get("tkTargetReportId").toString();
			if (tk_target_report_id.isEmpty()) {
				throw new Exception("报备编号tkTargetReportId为空");
			} else {
				WyMap mapP = new WyMap("tk_target_report_id", tk_target_report_id);
				switch (tk_report_state) {
				case "1":

					break;
				case "0":

					break;

				default:
					throw new Exception(
							String.format("报备编号tk_target_report_id%s中tk_report_state状态不正确，", tk_target_report_id));
				}
				mapP.put("tk_report_state", tk_report_state);
				int map = TkTargetReportDao.putReportByManage(mapP);
				if (map > 0) {
					WyMap re = mapP;
					ret = RetCode.getSuccessCode(re);
				} else {
					throw new Exception(
							String.format("报备编号tk_target_report_id%s修改不成功[报备状态0拒绝1通过]", tk_target_report_id));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ret.put("msg", e.getMessage());
			throw new RuntimeException();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.smallProgram.PlanServiceInterface#getReportByManage(com.
	 * tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode getReportByManage(WyMap paMap) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			if (paMap.get("tkUserId") == null) {

			} else {

				List<WyMap> list = TkTargetReportDao
						.selectByReUserId(new WyMap("tk_target_reuser_id", paMap.get("tkUserId").toString()));
				if (list == null) {
					throw new Exception("暂无用户任务报备");
				} else {
					List<WyMap> listtkTarget = new ArrayList();
					for (WyMap wyMap : list) {
						String tk_plan_id = wyMap.get("tk_plan_id").toString().trim();
						wyMap.put("tk_plan_detail_id", tk_plan_id);
						WyMap MapTkPlanDetail = TkPlanDetailDao.getPlanDetailByPlanId(wyMap);
						if (MapTkPlanDetail == null) {
							wyMap.put("tk_plan_detail_title", "null");
						} else {
							wyMap.put("tk_plan_detail_title",
									MapTkPlanDetail.get("tk_plan_detail_title").toString().trim());
						}
						TkUser MaptkUser = TkUserMapper.selectByPrimaryKey(wyMap);
						if (MaptkUser == null) {
							wyMap.put("TkUserHead", "null");
							wyMap.put("TkUserName", "null");
						} else {
							wyMap.put("TkUserHead", MaptkUser.getTkUserHead());
							wyMap.put("TkUserName", MaptkUser.getTkUserName());
						}
						listtkTarget.add(wyMap);
					}
					retCode = RetCode.getSuccessCode(list);
					retCode.put("msg",
							"经理查看拓客人员的报备申请：tk_plan_detail_title任务名，TkUserHead人员头像，TkUserName人员名称，tkTargetReportRemark备注，tk_plan_id任务编号，tkTargetReportId，报备编号，tkTargetReportName报备名称，tk_plan_detail_id任务详情编号，tkUserId用户编号");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	// 保存位置信息
	public WyMap postLocotion(WyMap wyMap) {
		try {
			TkLocation record = new TkLocation();
			String tk_location_id = CLID.getID();
			wyMap.put("tk_location_id", tk_location_id);
			record.setTkLocationId(wyMap.get("tk_location_id").toString());
			record.setTkLocationDetail(wyMap.get("address").toString());
			record.setLatitude(wyMap.get("latitude").toString());
			record.setLongitude(wyMap.get("longitude").toString());
			System.out.println(record.getTkLocationId());
			TkLocationDao.insert(record);

		} catch (Exception e) {
			e.printStackTrace();
			wyMap = null;
			throw new RuntimeException();
		}
		return wyMap;
	}
}
