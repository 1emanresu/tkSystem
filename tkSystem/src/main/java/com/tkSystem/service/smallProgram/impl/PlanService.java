package com.tkSystem.service.smallProgram.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tkSystem.dao.entity.TkLocation;
import com.tkSystem.dao.entity.TkPlanExecute;
import com.tkSystem.dao.entity.TkPlanPhoto;
import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.dao.mapper.TkLocationMapper;
import com.tkSystem.dao.mapper.TkPlanDetailMapper;
import com.tkSystem.dao.mapper.TkPlanExecuteMapper;
import com.tkSystem.dao.mapper.TkPlanMapper;
import com.tkSystem.dao.mapper.TkTargetReportMapper;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ShortMessageService;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

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
	@Resource
	private TkPlanExecuteMapper TkPlanExecuteDao;

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
		//该任务状态 1默认 2已经签到3已分配
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
			String tk_plan_execute_start_time = "", tk_plan_execute_end_time = "";
			paMap.put("tkPlanExecuteId", tkPlanExecuteId);
			// 根据tkPlanId查询任务时间 保存进去时间
			String tk_plan_detail_id = request.getParameter("tkPlanId");
			paMap.put("tk_plan_detail_id", tk_plan_detail_id);
			WyMap TkPlanDetailmap = TkPlanDetailDao.getPlanDetailByPlanId(paMap);
			tk_plan_execute_start_time = TkPlanDetailmap.get("tk_plan_detail_start").toString();
			tk_plan_execute_end_time = TkPlanDetailmap.get("tk_plan_detail_end").toString();
			paMap.put("tkPlanExecuteStartTime", tk_plan_execute_start_time);
			paMap.put("tkPlanExecuteEndTime", tk_plan_execute_end_time);
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
			throw new RuntimeException();
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
		// Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
		Date as = new Date(new Date().getTime());
		// 查询除了今日任务时间之外的所有任务
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
				// 任务包含今天
				if (str.contains(time)) {
					continue;
				}
				// 非今天 创建的任务 未分配 未签到
				if (str0.equals("1")) {
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
			// 根据用户id获取 被分配到的任务信息
			java.util.List<WyMap> list = new ArrayList();
			List<WyMap> TkPlanDetailList = TkPlanDetailDao.getAll();
			List<WyMap> TkPlanDetailMap = new ArrayList();
			List<TkPlanExecute> listTkPlanExecute = TkPlanExecuteDao.selectByUserId(request.getParameter("tkUserId"));
			List<WyMap> TkPlanList = TkPlanDao.selectAll();
			// 筛选出已分配可签到的任务
			for (WyMap wyMap : TkPlanDetailList) {
				String tk_plan_detail_id = wyMap.get("tk_plan_detail_id").toString();
				for (WyMap wyMap2 : TkPlanList) {
					String tk_plan_id = wyMap2.get("tk_plan_id").toString();
					if (tk_plan_id.equals(tk_plan_detail_id)) {
						System.out.println("tk_plan_detail_id="+tk_plan_detail_id);
						// 该任务状态 1默认 2已经签到3已分配
						String tk_plan_state = wyMap2.get("tk_plan_state").toString();
					/*	switch (tk_plan_state) {
						case "1":

							break;
						case "2":

							break;
						case "3":
							TkPlanDetailMap.add(wyMap);
							break;
						default:
							break;
						}*/
						TkPlanDetailMap.add(wyMap);
					}
				}
			}
			for (TkPlanExecute tkPlanExecute : listTkPlanExecute) {
				// 根据任务id获取任务信息
				String PlanId = tkPlanExecute.getTkPlanId();
				for (WyMap PlanDetailMap : TkPlanDetailMap) {
					String tk_plan_detail_id = PlanDetailMap.get("tk_plan_detail_id").toString();
					if (tk_plan_detail_id.equals(PlanId)) {
						String tk_plan_detail_photo = PlanDetailMap.get("tk_plan_detail_photo").toString();
						if (tk_plan_detail_photo.contains("://")) {

						} else {
							// 根据tk_plan_photo_id查看
							String str = tk_plan_detail_photo.split(",")[0];
							tk_plan_detail_photo = TkPlanPhotoMapper.selectByPrimaryKey(str).getTkPlanPhotoUrl();
						}
						list.add(PlanDetailMap);
					}
				}
			}
			// 按任务开始时间升序
			Collections.sort(list, new Comparator<WyMap>() {

				@Override
				public int compare(WyMap o1, WyMap o2) {
					String o1_start = o1.get("tk_plan_detail_start").toString();
					String o2_start = o2.get("tk_plan_detail_start").toString();

					long i = 0;
					try {
						i = ToolsUtil.getAmongDateToLong(o1_start, o2_start);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					/*
					 * if(i == 0){ return o1.getAge() - o2.getAge(); }
					 */
					return (int) Math.ceil(i);

				}
			});
			for (WyMap m : list) {
				System.out.println(m.get("tk_plan_detail_start").toString());
				System.out.println(m.get("tk_plan_detail_title").toString());
			}
			retCode = RetCode.getSuccessCode(list);
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
			// 返回的任务list1
			java.util.List<WyMap> list1 = new ArrayList<>();
			for (WyMap object : list) {
				String str = object.get("tkPlanTime").toString();
				String str0 = object.get("tkPlanState").toString();
				// 该任务状态 1默认 2已经签到3已分配
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
		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		String src = sb.substring(0, sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex = Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " + hex);
		request.setAttribute("hex", hex);
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
			request.setAttribute("names", "plan");
			String names = request.getAttribute("names").toString();
			CacheChannel cache = J2Cache.getChannel();
			String hex2 = request.getAttribute("hex").toString();
			cache.set(names, hex2, retCode);
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("失败");
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	@Override
	public RetCode getPlanByTime(HttpServletRequest request, HttpServletResponse response) {

		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		String src = sb.substring(0, sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex = Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " + hex);
		request.setAttribute("hex", hex);
		RetCode retCode = RetCode.getErrorCode();
		try {
			request.setAttribute("names", "plan");
			String names = request.getAttribute("names").toString();
			CacheChannel cache = J2Cache.getChannel();
			String hex2 = request.getAttribute("hex").toString();
			if (cache.get(names, hex2).getValue() == null) {
				retCode = getPlan(request, response);
				cache.set(names, hex2, retCode);
			}
			retCode = (RetCode) cache.get(names, hex2).getValue();
			List<WyMap> list = (List) retCode.get("data");
			// 返回的集合
			List<WyMap> list2 = new ArrayList();
			for (WyMap map : list) {
				String tk_plan_detail_end = map.get("tk_plan_detail_end").toString();
				String now = ToolsUtil.getDate();
				System.out.println(map.get("tk_plan_detail_end"));
				Long Amongtime = ToolsUtil.getAmongDateToLong(tk_plan_detail_end, now);
				map.put("Amongtime", Amongtime);
				list2.add(map);
			}
			// 算出时间 排序
			Collections.sort(list2, new Comparator<WyMap>() {

				@Override
				public int compare(WyMap o1, WyMap o2) {
					long i = (long) o2.get("Amongtime") - (long) o1.get("Amongtime");
					/*
					 * if(i == 0){ return o1.getAge() - o2.getAge(); }
					 */
					return Integer.parseInt(i + "");
				}
			});
			retCode = RetCode.getSuccessCode(list2);
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

	@Override
	public RetCode getPlanByDistance(HttpServletRequest request, HttpServletResponse response) {

		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		String src = sb.substring(0, sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex = Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " + hex);
		request.setAttribute("hex", hex);
		RetCode retCode = RetCode.getErrorCode();
		try {
			request.setAttribute("names", "plan");
			String names = request.getAttribute("names").toString();
			CacheChannel cache = J2Cache.getChannel();
			String hex2 = request.getAttribute("hex").toString();
			if (cache.get(names, hex2).getValue() == null) {
				retCode = getPlan(request, response);
				cache.set(names, hex2, retCode);
			}
			retCode = (RetCode) cache.get(names, hex2).getValue();
			List<WyMap> list = (List) retCode.get("data");
			// 返回的集合
			List<WyMap> list2 = new ArrayList();
			TkLocation t1 = new TkLocation();
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			t1.setLatitude(latitude);
			t1.setLongitude(longitude);
			if (cache.get("default", "listTkLocation").getValue() == null) {
				List<TkLocation> listTkLocation = TkLocationDao.selectAll();
				cache.set("default", "listTkLocation", listTkLocation);
			}
			for (WyMap map : list) {
				String tk_location_id = map.get("tk_location_id").toString();
				System.out.println(map.get("tk_location_id"));
				// 查经纬度坐标
				TkLocation t2 = new TkLocation();
				List<TkLocation> TkLocationlist = (List<TkLocation>) cache.get("default", "listTkLocation").getValue();
				for (TkLocation tkLocation : TkLocationlist) {
					if (tkLocation.getTkLocationId().equals(tk_location_id)) {
						t2 = tkLocation;
					} else {
						t2 = TkLocationDao.selectByPrimaryKey(tk_location_id);
					}
				}
				Double distanceToDouble = ToolsUtil.getDistanceToDouble(t1, t2);
				map.put("distanceToDouble", distanceToDouble);
				map.put("distance", new java.text.DecimalFormat("#0.0").format(distanceToDouble) + "km");
				list2.add(map);
			}
			// 算出距离 排序

			Collections.sort(list2, new Comparator<WyMap>() {

				@Override
				public int compare(WyMap o1, WyMap o2) {
					double i = (double) o1.get("distanceToDouble") - (double) o2.get("distanceToDouble");
					/*
					 * if(i == 0){ return o1.getAge() - o2.getAge(); }
					 */
					return (int) Math.ceil(i);

				}
			});
			retCode = RetCode.getSuccessCode(list2);
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
					+ "tk_plan_detail_end" + "   " + "结束时间" + "   " + "distance" + "   " + "距离千米数" + "   "
					+ "distanceToDouble" + "   " + "距离千米浮点数", "");
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
		// 获取今日任务
		RetCode retCode = RetCode.getErrorCode();
		try {
			TkUser user = TkUserMapper.selectByPrimaryKey(paMap);
			String typeId = user.getTkUserTypeId();
			String userId = paMap.get("tkUserId").toString();
			switch (typeId) {
			case "0":
				// 0拓客人员或普通用户，1超级管理员2一级经理
				paMap.put("tk_user_id", userId);
				paMap.remove("tk_plan_user_id");
				break;
			case "1":
				// 0拓客人员或普通用户，1超级管理员2一级经理
				paMap.put("tk_plan_user_id", userId);
				paMap.remove("tk_user_id");
				break;
			case "2":
				paMap.put("tk_plan_user_id", userId);
				paMap.remove("tk_user_id");
				break;

			default:
				throw new Exception("用户不存在");

			}
			paMap.printStr();
			List<WyMap> TkPlanExecutelist = TkPlanDetailDao.getTodayTask(paMap);
			List<WyMap> TkPlanDetaillist = TkPlanDetailDao.getAll();
			List<WyMap> list = new ArrayList();
			for (WyMap wyMap : TkPlanExecutelist) {
				String tk_plan_id=wyMap.get("tk_plan_id").toString();
				for (WyMap wyMap2 : TkPlanDetaillist) {
					String tk_plan_detail_id=wyMap2.get("tk_plan_detail_id").toString();
					if(tk_plan_id.equals(tk_plan_detail_id)) {
						list.add(wyMap2);
					}
				}
			}
			System.out.println(list.size());
			if (list == null) {
				throw new Exception("今日任务暂无！TkPlanDetailDao.getTodayTask");
			}
			List<WyMap> relist = new ArrayList();
			for (WyMap wyMap : list) {
				// 获取任务
			/*	String tk_plan_id = wyMap.get("tk_plan_id").toString();
				wyMap.put("tk_plan_detail_id", tk_plan_id);
				wyMap = TkPlanDetailDao.getPlanDetailByPlanId(wyMap);*/
				String tk_plan_detail_photo = wyMap.get("tk_plan_detail_photo").toString();
				if (tk_plan_detail_photo.contains("://")) {
				} else {
					// 根据tk_plan_photo_id查看
					String tk_plan_detail_id = wyMap.get("tk_plan_detail_id").toString();
					System.out.println("tk_plan_detail_id="+tk_plan_detail_id);
					String str = tk_plan_detail_photo.split(",")[0];
					TkPlanPhoto t=TkPlanPhotoMapper.selectByPrimaryKey(str);
					if(t==null) {
						//图片不存在
					}
					if(t.getTkPlanPhotoUrl()==null||t.getTkPlanPhotoUrl().isEmpty()) {
						//图片url不存在
					}else {
						tk_plan_detail_photo = TkPlanPhotoMapper.selectByPrimaryKey(str).getTkPlanPhotoUrl();
					}
				}
				String tk_plan_detail_end = wyMap.get("tk_plan_detail_end").toString();
				String among = ToolsUtil.getAmongDate(tk_plan_detail_end, ToolsUtil.getDate());
				wyMap.put("amongDate", among);
				wyMap.put("tk_plan_detail_photo", tk_plan_detail_photo);

				relist.add(wyMap);
			}
			retCode = RetCode.getSuccessCode(relist);
		} catch (Exception e) {
			e.printStackTrace();
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
	public Object getPlanDetailByPlanId(HttpServletRequest request, HttpServletResponse response) {
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
	public Object getAreadyPlan(HttpServletRequest request) {
		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		String src = sb.substring(0, sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex = Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " + hex);
		request.setAttribute("hex", hex);
		RetCode retCode = RetCode.getErrorCode();
		try {
			WyMap wyMap = WyMap.getParameter(request);
			wyMap.put("tk_plan_detail_user_id", request.getParameter("tkUserId"));
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
			List<WyMap> list2 = new ArrayList();
			TkLocation t1 = new TkLocation();
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			t1.setLatitude(latitude);
			t1.setLongitude(longitude);
			CacheChannel cache = J2Cache.getChannel();
			if (cache.get("default", "listTkLocation").getValue() == null) {
				List<TkLocation> listTkLocation = TkLocationDao.selectAll();
				cache.set("default", "listTkLocation", listTkLocation);
			}
			for (WyMap map : list) {
				String tk_location_id = map.get("tk_location_id").toString();
				System.out.println(map.get("tk_location_id"));
				// 查经纬度坐标
				TkLocation t2 = new TkLocation();
				List<TkLocation> TkLocationlist = (List<TkLocation>) cache.get("default", "listTkLocation").getValue();
				for (TkLocation tkLocation : TkLocationlist) {
					if (tkLocation.getTkLocationId().equals(tk_location_id)) {
						t2 = tkLocation;
					} else {
						t2 = TkLocationDao.selectByPrimaryKey(tk_location_id);
					}
				}
				Double distanceToDouble = ToolsUtil.getDistanceToDouble(t1, t2);
				map.put("distanceToDouble", distanceToDouble);
				map.put("distance", new java.text.DecimalFormat("#0.0").format(distanceToDouble) + "km");
				String tk_plan_detail_end = map.get("tk_plan_detail_end").toString();
				String now = ToolsUtil.getDate();
				System.out.println(map.get("tk_plan_detail_end"));
				Long Amongtime = ToolsUtil.getAmongDateToLong(tk_plan_detail_end, now);
				map.put("Amongtime", Amongtime);
				list2.add(map);
			}
			retCode = RetCode.getSuccessCode(list2);
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
			request.setAttribute("names", "areadyPlan");
			String names = request.getAttribute("names").toString();
			String hex2 = request.getAttribute("hex").toString();
			cache.set(names, hex2, retCode);
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("失败");
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	@Override
	public RetCode getAreadyPlanByTime(HttpServletRequest request, HttpServletResponse response) {

		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		String src = sb.substring(0, sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex = Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " + hex);
		request.setAttribute("hex", hex);
		RetCode retCode = RetCode.getErrorCode();
		try {
			request.setAttribute("names", "areadyPlan");
			String names = request.getAttribute("names").toString();
			CacheChannel cache = J2Cache.getChannel();
			String hex2 = request.getAttribute("hex").toString();
			if (cache.get(names, hex2).getValue() == null) {
				retCode = (RetCode) getAreadyPlan(request);
				cache.set(names, hex2, retCode);
			}
			retCode = (RetCode) cache.get(names, hex2).getValue();
			List<WyMap> list = (List) retCode.get("data");
			// 返回的集合
			List<WyMap> list2 = new ArrayList();
			TkLocation t1 = new TkLocation();
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			t1.setLatitude(latitude);
			t1.setLongitude(longitude);
			if (cache.get("default", "listTkLocation").getValue() == null) {
				List<TkLocation> listTkLocation = TkLocationDao.selectAll();
				cache.set("default", "listTkLocation", listTkLocation);
			}
			for (WyMap map : list) {
				String tk_location_id = map.get("tk_location_id").toString();
				System.out.println(map.get("tk_location_id"));
				// 查经纬度坐标
				TkLocation t2 = new TkLocation();
				List<TkLocation> TkLocationlist = (List<TkLocation>) cache.get("default", "listTkLocation").getValue();
				for (TkLocation tkLocation : TkLocationlist) {
					if (tkLocation.getTkLocationId().equals(tk_location_id)) {
						t2 = tkLocation;
					} else {
						t2 = TkLocationDao.selectByPrimaryKey(tk_location_id);
					}
				}
				Double distanceToDouble = ToolsUtil.getDistanceToDouble(t1, t2);
				map.put("distanceToDouble", distanceToDouble);
				map.put("distance", new java.text.DecimalFormat("#0.0").format(distanceToDouble) + "km");
				String tk_plan_detail_end = map.get("tk_plan_detail_end").toString();
				String now = ToolsUtil.getDate();
				System.out.println(map.get("tk_plan_detail_end"));
				Long Amongtime = ToolsUtil.getAmongDateToLong(tk_plan_detail_end, now);
				map.put("Amongtime", Amongtime);
				list2.add(map);
			}
			// 算出时间 排序
			Collections.sort(list2, new Comparator<WyMap>() {

				@Override
				public int compare(WyMap o1, WyMap o2) {
					long i = (long) o1.get("Amongtime") - (long) o2.get("Amongtime");
					/*
					 * if(i == 0){ return o1.getAge() - o2.getAge(); }
					 */
					return Integer.parseInt(i + "");
				}
			});
			retCode = RetCode.getSuccessCode(list2);
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

	@Override
	public Object getAreadyPlanByDistance(HttpServletRequest request) {

		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		String src = sb.substring(0, sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex = Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " + hex);
		request.setAttribute("hex", hex);
		RetCode retCode = RetCode.getErrorCode();
		try {
			request.setAttribute("names", "areadyPlan");
			String names = request.getAttribute("names").toString();
			CacheChannel cache = J2Cache.getChannel();
			String hex2 = request.getAttribute("hex").toString();
			if (cache.get(names, hex2).getValue() == null) {
				retCode = (RetCode) getAreadyPlan(request);
				cache.set(names, hex2, retCode);
			}
			retCode = (RetCode) cache.get(names, hex2).getValue();
			List<WyMap> list = (List) retCode.get("data");
			// 返回的集合
			List<WyMap> list2 = new ArrayList();
			TkLocation t1 = new TkLocation();
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			t1.setLatitude(latitude);
			t1.setLongitude(longitude);
			if (cache.get("default", "listTkLocation").getValue() == null) {
				List<TkLocation> listTkLocation = TkLocationDao.selectAll();
				cache.set("default", "listTkLocation", listTkLocation);
			}
			for (WyMap map : list) {
				String tk_location_id = map.get("tk_location_id").toString();
				System.out.println(map.get("tk_location_id"));
				// 查经纬度坐标
				TkLocation t2 = new TkLocation();
				List<TkLocation> TkLocationlist = (List<TkLocation>) cache.get("default", "listTkLocation").getValue();
				for (TkLocation tkLocation : TkLocationlist) {
					if (tkLocation.getTkLocationId().equals(tk_location_id)) {
						t2 = tkLocation;
					} else {
						t2 = TkLocationDao.selectByPrimaryKey(tk_location_id);
					}
				}
				Double distanceToDouble = ToolsUtil.getDistanceToDouble(t1, t2);
				map.put("distanceToDouble", distanceToDouble);
				map.put("distance", new java.text.DecimalFormat("#0.0").format(distanceToDouble) + "km");
				list2.add(map);
			}
			// 算出距离 排序

			Collections.sort(list2, new Comparator<WyMap>() {

				@Override
				public int compare(WyMap o1, WyMap o2) {
					double i = (double) o1.get("distanceToDouble") - (double) o2.get("distanceToDouble");
					/*
					 * if(i == 0){ return o1.getAge() - o2.getAge(); }
					 */
					return (int) Math.ceil(i);

				}
			});
			retCode = RetCode.getSuccessCode(list2);
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
					+ "tk_plan_detail_end" + "   " + "结束时间" + "   " + "distance" + "   " + "距离千米数" + "   "
					+ "distanceToDouble" + "   " + "距离千米浮点数", "");
			retCode.put("msg", str);
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("失败");
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	// 获取当前任务最近位置
	@Override
	public Object getAreadyPlanDistance(HttpServletRequest request) {
		Enumeration<String> srcList = request.getParameterNames();
		StringBuilder sb = new StringBuilder("");
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			sb.append(key + ":" + value + ",");
		}
		String src = sb.substring(0, sb.length() - 1).toString();
		System.out.println(src);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageDigest.update(src.getBytes());
		byte[] shaBytes = messageDigest.digest();
		String hex = Hex.encodeHexString(shaBytes);
		System.out.println("jdk SHA 1: " + hex);
		request.setAttribute("hex", hex);
		RetCode retCode = RetCode.getErrorCode();
		try {
			request.setAttribute("names", "areadyPlan");
			String names = request.getAttribute("names").toString();
			CacheChannel cache = J2Cache.getChannel();
			String hex2 = request.getAttribute("hex").toString();
			if (cache.get(names, hex2).getValue() == null) {
				retCode = (RetCode) getAreadyPlan(request);
				cache.set(names, hex2, retCode);
			}
			retCode = (RetCode) cache.get(names, hex2).getValue();
			List<WyMap> list = (List) retCode.get("data");
			// 返回的集合
			List<WyMap> list2 = new ArrayList();
			TkLocation t1 = new TkLocation();
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			t1.setLatitude(latitude);
			t1.setLongitude(longitude);
			if (cache.get("default", "listTkLocation").getValue() == null) {
				List<TkLocation> listTkLocation = TkLocationDao.selectAll();
				cache.set("default", "listTkLocation", listTkLocation);
			}
			for (WyMap map : list) {
				String tk_location_id = map.get("tk_location_id").toString();
				System.out.println(map.get("tk_location_id"));
				// 查经纬度坐标
				TkLocation t2 = new TkLocation();
				List<TkLocation> TkLocationlist = (List<TkLocation>) cache.get("default", "listTkLocation").getValue();
				for (TkLocation tkLocation : TkLocationlist) {
					if (tkLocation.getTkLocationId().equals(tk_location_id)) {
						t2 = tkLocation;
					} else {
						t2 = TkLocationDao.selectByPrimaryKey(tk_location_id);
					}
				}
				Double distanceToDouble = ToolsUtil.getDistanceToDouble(t1, t2);
				map.put("distanceToDouble", distanceToDouble);
				map.put("distance", new java.text.DecimalFormat("#0.0").format(distanceToDouble) + "km");
				list2.add(map);
			}
			// 算出距离 排序

			Collections.sort(list2, new Comparator<WyMap>() {

				@Override
				public int compare(WyMap o1, WyMap o2) {
					double i = (double) o1.get("distanceToDouble") - (double) o2.get("distanceToDouble");
					/*
					 * if(i == 0){ return o1.getAge() - o2.getAge(); }
					 */
					return (int) Math.ceil(i);

				}
			});
			// 返回地址
			if(list2.size()<1) {
				throw new Exception("暂无数据");
			}
			String location = list2.get(0).get("tk_plan_detail_location").toString();
			// 获取距离最近的地址
			retCode = RetCode.getSuccessCode(new WyMap("tk_plan_detail_location", location));
			String str = String.format("tk_plan_detail_location" + "   " + "位置信息", "");
			retCode.put("msg", str);
		} catch (Exception e) {
			e.printStackTrace();
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
