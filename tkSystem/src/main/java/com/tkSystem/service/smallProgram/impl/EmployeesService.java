package com.tkSystem.service.smallProgram.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.tkSystem.dao.entity.TkGoodApplyGood;
import com.tkSystem.dao.entity.TkPlanExecuteClient;
import com.tkSystem.dao.entity.TkPlanPhoto;
import com.tkSystem.dao.entity.TkPunchcard;
import com.tkSystem.dao.entity.TkPunchcardFeedback;
import com.tkSystem.dao.entity.TkPunchcardPhoto;
import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.dao.mapper.TkGoodApplyGoodMapper;
import com.tkSystem.dao.mapper.TkPlanDetailMapper;
import com.tkSystem.dao.mapper.TkPlanExecuteClientMapper;
import com.tkSystem.dao.mapper.TkPlanExecuteMapper;
import com.tkSystem.dao.mapper.TkPlanMapper;
import com.tkSystem.dao.mapper.TkPunchcardFeedbackMapper;
import com.tkSystem.dao.mapper.TkPunchcardMapper;
import com.tkSystem.dao.mapper.TkPunchcardPhotoMapper;
import com.tkSystem.dao.mapper.TkTargetReportMapper;
import com.tkSystem.dao.mapper.TkUserMapper;
import com.tkSystem.service.smallProgram.EmployeesServiceInterface;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.ImageUpload;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Service
public class EmployeesService implements EmployeesServiceInterface {

	CLID clid = new CLID();
	ToolsUtil toolsUtil = new ToolsUtil();

	@Autowired
	TkGoodApplyGoodMapper tkGoodApplyGoodMapper;
	@Autowired
	TkUserMapper tkUserMapper;
	@Autowired
	TkPunchcardPhotoMapper TkPunchcardPhotoMapper;
	@Autowired
	TkPunchcardMapper tkPunchcardMapper;
	@Autowired
	TkPlanMapper tkPlanMapper;
	@Autowired
	TkPlanDetailMapper tkPlanDetailMapper;
	@Autowired
	TkPunchcardFeedbackMapper tkPunchcardFeedbackMapper;
	@Autowired
	TkPlanExecuteClientMapper tkPlanExecuteClientMapper;
	@Autowired
	TkPlanExecuteMapper tkPlanExecuteMapper;
	@Autowired
	TkTargetReportMapper tkTargetReportMapper;

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#postApplicationMaterials(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode postApplicationMaterials(WyMap paMap) {
		RetCode retCode = RetCode.getErrorCode("申请失败,有事与管理员联系！");
		try {
			WyMap plan = tkPlanDetailMapper
					.getPlanDetailByPlanId(new WyMap("tk_plan_detail_id", paMap.get("tkPlanId").toString()));
			if (plan == null) {
				throw new Exception("tkPlanDetail信息中当前的任务不存在");
			}
			String tkGoodApplyId = CLID.getID();
			String tk_good_apply_good_time = ToolsUtil.getDate();
			String tk_good_apply_state = "-1";
			paMap.put("tkGoodApplyId", tkGoodApplyId);
			paMap.put("tk_good_apply_good_time", tk_good_apply_good_time);
			paMap.put("tk_good_apply_state", tk_good_apply_state);
			String str[] = { "tkGoodApplyId", "tkGoodName", "tkUserId", "tkGoodApplyGoodAmount", "tkPlanId", "remark",
					"tk_good_apply_good_time", "tk_good_apply_state" };
			ToolsUtil.MapIsNull(paMap, str);
			Integer ret = tkGoodApplyGoodMapper.postApplicationMaterials(paMap);
			if (ret > 0)
				retCode = retCode.getSuccessCode("申请成功");
			retCode.put("data", paMap);
		} catch (Exception e) {
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#getApplicationMaterials(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode getApplicationMaterials(WyMap paMap) {
		RetCode retCode = null;
		try {
			retCode = retCode.getSuccessCode(tkGoodApplyGoodMapper.selectAll());
			retCode.put("msg",
					"tkGoodApplyUserId as '申请用户的编号',tkGoodApplyName as '申请物料名称',tk_good_apply_remark as '备注信息'");
		} catch (Exception e) {
			return retCode = RetCode.getErrorCode("申请失败,有事与管理员联系！");
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#getApplicationMaterialss(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode getApplicationMaterialss(WyMap wyMap1) {
		RetCode retCode = RetCode.getErrorCode("申请失败,有事与管理员联系！");
		try {
			List<WyMap> list = tkGoodApplyGoodMapper.selectAllByTkUserId(wyMap1);
			if (list != null) {
				String tkuserName, tkUserhead;
				TkUser use = tkUserMapper.selectByPrimaryKey(wyMap1);
				tkuserName = use.getTkUserName();
				tkUserhead = use.getTkUserHead();
				for (WyMap map : list) {
					map.put("tkuserName", tkuserName);
					map.put("tkUserhead", tkUserhead);
					map.put("tk_plan_detail_id", map.get("tk_good_apply_plan_id").toString().trim());
					WyMap MapTkPlanDetail = tkPlanDetailMapper.getPlanDetailByPlanId(map);
					if (MapTkPlanDetail == null) {
						map.put("tk_plan_detail_title", "null");
					} else {
						map.put("tk_plan_detail_title", MapTkPlanDetail.get("tk_plan_detail_title").toString().trim());
					}
				}

			}
			System.out.println(list.size());
			retCode = RetCode.getSuccessCode(list);
			retCode.put("msg",
					"tkGoodApplyUserId as '申请用户的编号',tkGoodApplyName as '申请物料名称',tk_good_apply_remark as '备注信息'");
		} catch (Exception e) {
			retCode.put("msg", "");
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#postClockIn(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode postClockIn(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode = null;
		try {
			String id = CLID.getID();
			WyMap paMap = WyMap.getParameter(request);
			String tkPunchCardPhoto = ",";
			if (request.getParameterValues("tkPunchcardPhoto") == null) {
				if (request.getParameter("tkPunchcardPhoto") == null
						|| request.getParameter("tkPunchcardPhoto").trim().equals("")) {
					throw new Exception("tkPunchcardPhoto格式有误");
				}
			} else {
				String[] str2 = request.getParameterValues("tkPunchcardPhoto");
				for (String string : str2) {
					System.out.println(string);
					TkPunchcardPhoto record = new TkPunchcardPhoto();
					record.setTkPunchcardPhotoId(CLID.getID());
					record.setTkPunchcardPhotoUrl(string);
					record.setTkPunchcardId(id);
					int iii = TkPunchcardPhotoMapper.insert(record);
					tkPunchCardPhoto = string + ",";
				}
			}
			TkPunchcard tkPunchcard = new TkPunchcard();
			tkPunchcard.setTkPunchcardId(id);
			String uid = paMap.get("tkUserId").toString();
			String tkPunchcardFeedbackPlanId = paMap.get("tkPlanId").toString();
			tkPunchcard.setTkPunchcardTime(ToolsUtil.getDate());// 打卡时间
			tkPunchcard.setTkPunchcardLoc(paMap.get("tkPunchcardLoc").toString());// 打卡位置，上传经纬度
			tkPunchcard.setTkPunchCardPhoto("TkPunchCardPhoto");// 工作照片
			tkPunchcard.setTkPlanId(tkPunchcardFeedbackPlanId);
			Integer ret = tkPunchcardMapper.insertSelective(tkPunchcard);
			TkPunchcardFeedback wyMap1 = new TkPunchcardFeedback();
			wyMap1.setTkPunchcardFeedbackId(id);
			wyMap1.setTkPunchcardFeedbackPlanId(tkPunchcardFeedbackPlanId);
			wyMap1.setTkPunchcardFeedbackUserId(uid);
			Integer ret1 = tkPunchcardFeedbackMapper.insertSelective(wyMap1);
			if (ret > 0 && ret1 > 0) {
				retCode = retCode.getSuccessCode("打卡成功");
				/**
				 * 打卡成功后修改任务状态
				 */
				String PlanId = tkPunchcardFeedbackPlanId;
				WyMap wyMap = new WyMap();
				wyMap.put("tkPlanId", PlanId);
				wyMap.put("tkPlanState", "2");
				tkPlanMapper.updateByPrimaryKeySelective(wyMap);
				tkPlanDetailMapper.updateTkPlanState(wyMap);
			}

			else {
				retCode = RetCode.getErrorCode("打卡失败,有事与管理员联系！");

			}

		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("打卡失败,有事与管理员联系！");
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#postClockInFeedback(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode postClockInFeedback(WyMap paMap) {
		RetCode retCode = null;
		try {
			TkPunchcardFeedback tkPunchcardFeedback = new TkPunchcardFeedback();
			tkPunchcardFeedback.setTkPunchcardFeedbackId(CLID.getID());
			tkPunchcardFeedback.setTkPunchcardFeedbackUserId(paMap.get("tkPunchcardFeedbackUserId").toString());// 打卡人ID
			tkPunchcardFeedback.setTkPunchcardFeedbackTime(paMap.get("tkPunchcardFeedbackTime").toString());// 打卡时间
			tkPunchcardFeedback.setTkPunchcardFeedbackPlanId(paMap.get("tkPunchcardFeedbackPlanId").toString());// 任务ID
			tkPunchcardFeedback.setTkPunchcardFeedbackLatetime(paMap.get("tkPunchcardFeedbackLatetime").toString());// 迟到时间
			Integer ret = tkPunchcardFeedbackMapper.insertSelective(tkPunchcardFeedback);
			if (ret > 0) {
				retCode = retCode.getSuccessCode("打卡成功");
			} else {
				retCode = RetCode.getErrorCode("迟到反馈失败,有事与管理员联系！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			retCode = RetCode.getErrorCode("迟到反馈失败,有事与管理员联系！");
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#postSaveCustomerInformation(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode postSaveCustomerInformation(WyMap paMap) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			String tkPlanExecuteClientId = clid.getID();
			int ret = tkPlanExecuteClientMapper.insert(paMap);
			if (ret > 0)
				retCode = RetCode.getSuccessCode();
		} catch (Exception e) {
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#getTaskSchedule(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode getTaskSchedule(WyMap paMap) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			retCode = RetCode.getSuccessCode(tkPlanExecuteMapper.selectByPrimaryKey(paMap));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#postTaskFeedback(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode postTaskFeedback(WyMap paMap) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			retCode = RetCode.getSuccessCode(tkTargetReportMapper.insertSelective(paMap));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#getTaskFeedback(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode getTaskFeedback(WyMap paMap) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			List<WyMap> list = tkTargetReportMapper.selectByUserId(paMap);
			List<WyMap> listtkTarget = new ArrayList();
			for (WyMap wyMap : list) {
				if(wyMap.get("tk_plan_id")==null) {
					throw new Exception(String.format("%s关联的任务为空",paMap.get("tkUserId")));
				}
				String tk_plan_id = wyMap.get("tk_plan_id").toString().trim();
				wyMap.put("tk_plan_detail_id", tk_plan_id);
				WyMap MapTkPlanDetail = tkPlanDetailMapper.getPlanDetailByPlanId(wyMap);
				if (MapTkPlanDetail == null) {
					wyMap.put("tk_plan_detail_title", "null");
				} else {
					wyMap.put("tk_plan_detail_title", MapTkPlanDetail.get("tk_plan_detail_title").toString().trim());
				}
				TkUser MaptkUser = tkUserMapper.selectByPrimaryKey(wyMap);
				if (MaptkUser == null) {
					wyMap.put("TkUserHead", "null");
					wyMap.put("TkUserName", "null");
				} else {
					wyMap.put("TkUserHead", MaptkUser.getTkUserHead());
					wyMap.put("TkUserName", MaptkUser.getTkUserName());
					}
				listtkTarget.add(wyMap);
			}
			retCode = RetCode.getSuccessCode(listtkTarget);
		 } catch (Exception e) {
			 retCode.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#getClockIn(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getClockIn(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode = RetCode.getErrorCode();
		WyMap paMap = WyMap.getParameter(request);
		try {
			List<WyMap> PlanNamelist = tkPlanMapper.getPlanNameByUserId(paMap);
			List<WyMap> ClockInlist = tkPunchcardMapper.getClockInRecord(paMap);
			int i = 0;
			for (WyMap wyMap : ClockInlist) {
				String tkPlanId = wyMap.get("tkPlanId").toString();
				for (WyMap wyMap2 : PlanNamelist) {
					String tkPlanId2 = wyMap2.get("tk_plan_id").toString();
					if (tkPlanId.equals(tkPlanId2)) {
						ClockInlist.get(i).put("tk_plan_name", wyMap2.get("tk_plan_name").toString());
						String tk_plan_time = wyMap2.get("tk_plan_time").toString();
						String tk_clock_time = wyMap.get("tkPunchCardTime").toString();
						SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date dttk_plan_time = sDateFormat.parse(tk_plan_time);
						Date dttk_clock_time = sDateFormat.parse(tk_clock_time);
						if (dttk_plan_time.getTime() > dttk_clock_time.getTime()) {
							ClockInlist.get(i).put("clockState", "true");
						} else if (dttk_plan_time.getTime() < dttk_clock_time.getTime()) {
							ClockInlist.get(i).put("clockState", "false");
						} else {
						}

						break;
					}
				}
				i++;
			}
			retCode = RetCode.getSuccessCode(ClockInlist);

			retCode.put("msg",
					"tkPunchCardTime => '打卡时间',tk_plan_name => '任务名称',clockState => '打卡是否成功',tkPlanId => '打卡任务编号' ，tkPunchCardId =>‘打卡id’");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#postTaskFeedback(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode postTaskFeedback(HttpServletRequest request) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			String tkTargetReportId, tkTargetReportName, tkTargetReportTime, tkUserId, tkPlanId,
					tk_target_report_remark, tk_target_reuser_id;
			/**
			 * 獲取 tk_target_report_remark,tkPlanId,tkUserId
			 */
			WyMap paMap = WyMap.getParameter(request);
			tkPlanId = request.getParameter("tkPlanId");
			tkTargetReportTime = ToolsUtil.getDate();
			tkTargetReportId = CLID.getID();
			paMap.put("tkTargetReportId", tkTargetReportId);
			paMap.put("tkTargetReportTime", tkTargetReportTime);
			paMap.put("tk_plan_id", tkPlanId);
			/**
			 * 根據任務id獲取 tkTargetReportName,tk_target_reuser_id
			 */
			WyMap plan = tkPlanDetailMapper.getPlanDetailByPlanId(new WyMap("tk_plan_detail_id", tkPlanId));
			if (plan == null) {
				throw new Exception("tkPlanDetail信息中当前的任务不存在");
			}
			tkTargetReportName = plan.get("tk_plan_detail_title").toString().trim() + "的报备";
			tk_target_reuser_id = plan.get("tk_plan_detail_user_id").toString().trim();
			paMap.put("tk_target_reuser_id", tk_target_reuser_id);
			paMap.put("tkTargetReportName", tkTargetReportName);
			String str[] = { "tkTargetReportId", "tkTargetReportName", "tkTargetReportTime", "tkUserId", "tkPlanId",
					"tk_target_report_remark", "tk_target_reuser_id" };
			ToolsUtil.MapIsNull(paMap, str);
			retCode = RetCode.getSuccessCode(tkTargetReportMapper.insertSelective(paMap));
		} catch (Exception e) {
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.smallProgram.EmployeesServiceInterface#getPlanByUserId(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode getPlanByUserId(HttpServletRequest request) {
		RetCode retCode = RetCode.getErrorCode();
		try {
			String tkUserId = null;
			WyMap paMap = WyMap.getParameter(request);
			String str[] = { "tk_plan_detail_tkuser_id" };
			paMap.put("tk_plan_detail_tkuser_id", request.getParameter("tkUserId"));
			ToolsUtil.MapIsNull(paMap, str);
			List<WyMap> list = tkPlanDetailMapper.getPlanByTkUserId(paMap);
			retCode = RetCode.getSuccessCode(list);
		} catch (Exception e) {
			retCode.put("msg", e.getMessage());
		}
		return retCode;
	}

}
