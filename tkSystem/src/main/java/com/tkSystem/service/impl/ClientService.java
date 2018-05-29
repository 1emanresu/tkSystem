package com.tkSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.tkSystem.dao.entity.TkClientPhoto;
import com.tkSystem.service.ClientServiceInterface;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Service("clientService")
public class ClientService implements ClientServiceInterface {
	@Resource
	private com.tkSystem.dao.mapper.TkSystemMapper TkSystemMapper;
	@Resource
	private com.tkSystem.dao.mapper.TkClientPhotoMapper TkClientPhotoMapper;
	@Resource
	private com.tkSystem.dao.mapper.TkPlanDetailMapper TkPlanDetailMapper;
	@Resource
	private com.tkSystem.dao.mapper.TkUserMapper TkUserMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.vcxv#getClientInfo(javax.servlet.http.
	 * HttpServletRequest)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.ClientServiceInterface#getClientInfo(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public RetCode getClientInfo(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			java.util.List<WyMap> list = TkSystemMapper.selectByUserId(paMap);
			retCode = RetCode.getSuccessCode(list);
			retCode.put("msg",
					"tk_client_id as '客户信息编号id',tk_client_name as '客户姓名',tk_client_phone as '客户手机号码'，tk_client_location as '位置信息' ");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.vcxv#getWorkNumber(javax.servlet.http.
	 * HttpServletRequest)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.ClientServiceInterface#getWorkNumber(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public RetCode getWorkNumber(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode = RetCode.getErrorCode();
		try {
			java.util.List<WyMap> list = TkSystemMapper.getWorkNumber(paMap);
			for (WyMap wyMap : list) {
				String tk_plan_detail_end = wyMap.get("tk_plan_detail_end").toString();
				String among = ToolsUtil.getAmongDate(tk_plan_detail_end, ToolsUtil.getDate());
				wyMap.put("amongDate", among);
			}
			retCode = RetCode.getSuccessCode(list);
			retCode.put("msg",
					"[本月业务接口] tk_plan_detail_title => '任务名' ,no => '排名' ,tk_plan_detail_location_detail =>'任务详细地址',tk_plan_detail_good_name =>'任务礼品名字',tk_plan_detail_user_id =>'任务发布者id',tk_plan_detail_remark=>任务备注信息,tk_plan_detail_contacts_sex=>联系人性别,tk_plan_detail_target=>任务目标,tk_plan_detail_location=>位置,tk_plan_detail_contacts_name=>联系人名称,tk_plan_detail_start=>任务开始时间,tk_plan_detail_photo=>联系人电话,tk_plan_detail_tkuser_id=>任务执行者,tk_plan_detail_id=>任务id,tk_plan_detail_good_amount=>任务礼品数量,tk_plan_detail_end=>任务截至日期, tk_plan_detail_target_achieve=>目标完成数");
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
	 * com.tkSystem.service.vcxv#getTeamIndex(javax.servlet.http.HttpServletRequest)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.ClientServiceInterface#getTeamIndex(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public RetCode getTeamIndex(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode = RetCode.getErrorCode();
		try {
			int TeamIndex = 0;
			String tkUserId = paMap.get("tkUserId").toString().trim();
			java.util.List<WyMap> list = TkSystemMapper.getTeamIndex(paMap);
			java.util.List<WyMap> list1 = new ArrayList<>();
			int i = 0;
			for (WyMap object : list) {
				i++;
				object.put("no", i);
				list1.add(object);

			}
			retCode = RetCode.getSuccessCode(list1);
			retCode.put("msg",
					"【组别名次】tk_user_head => '用户头像' ,no => '排名' ,tk_user_name =>'用户名',tk_user_id =>'用户编号',tk_client_amount =>'拓客数量'");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.vcxv#getClientInfoByPlanId(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.ClientServiceInterface#getClientInfoByPlanId(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getClientInfoByPlanId(HttpServletRequest request, HttpServletResponse response) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode = RetCode.getErrorCode();
		try {
			int TeamIndex = 0;
			String tkUserId = paMap.get("tkUserId").toString().trim();
			java.util.List<WyMap> ClientInfolist = TkSystemMapper.getClientInfoByPlanId(paMap);
			java.util.List<WyMap> TkUserlist1 = TkSystemMapper.getTkUserByPlanId(paMap);
			int i = 0;
			for (WyMap wyMap : ClientInfolist) {
				String user_id = wyMap.get("tk_client_user_id").toString();
				for (WyMap wyMap2 : TkUserlist1) {
					String tk_user_id = wyMap2.get("tk_user_id").toString();
					if (user_id.equals(tk_user_id)) {
						ClientInfolist.get(i).put("tk_user_name", wyMap2.get("tk_user_name").toString());
						break;
					}
				}
				i++;
			}
			retCode = RetCode.getSuccessCode(ClientInfolist);
			retCode.put("msg",
					"【组别名次】tk_user_head => '用户头像' ,no => '排名' ,tk_user_name =>'用户名',tk_user_id =>'用户编号',tk_client_amount =>'拓客数量'");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.vcxv#getCompanyIndex(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.ClientServiceInterface#getCompanyIndex(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getCompanyIndex(HttpServletRequest request, HttpServletResponse response) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode = RetCode.getErrorCode();
		try {
			java.util.List<WyMap> list = TkSystemMapper.getCompanyIndex(paMap);
			String tkUserId = paMap.get("tkUserId").toString().trim();
			int CompanyIndex = 0;
			java.util.List<WyMap> list1 = new ArrayList<>();
			int i = 0;
			for (WyMap object : list) {
				i++;
				object.put("no", i);
				list1.add(object);

			}
			retCode = RetCode.getSuccessCode(list1);
			retCode.put("msg",
					"【公司排名】  tk_user_head => '用户头像' ,no => '排名' ,tk_user_name =>'用户名',tk_user_id =>'用户编号',tk_client_amount =>'拓客数量'");
		} catch (Exception e) {
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.vcxv#getTkChannel(javax.servlet.http.HttpServletRequest)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.ClientServiceInterface#getTkChannel(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public RetCode getTkChannel(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode = RetCode.getErrorCode();
		try {
			java.util.List<WyMap> list = TkSystemMapper.getTkChannel(paMap);
			retCode = RetCode.getSuccessCode(list);
			retCode.put("msg", "【渠道类型】  tk_channel_id => '渠道类型id' ,tk_channel_name => '渠道名' ,tk_user_id =>'用户编号'  ");
		} catch (Exception e) {
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.vcxv#postClientInfo(javax.servlet.http.
	 * HttpServletRequest)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.ClientServiceInterface#postClientInfo(javax.servlet.http
	 * .HttpServletRequest)
	 */
	@Override
	public RetCode postClientInfo(HttpServletRequest request) {
		String tkClientPhoto = "";
		String clid = CLID.getID();
		if (request.getParameterValues("tk_client_photo") == null) {
			if (request.getParameter("tk_client_photo") == null
					|| request.getParameter("tk_client_photo").trim().equals("")) {
			} else {

			}
		} else {
			String[] photo = request.getParameterValues("tk_client_photo");
			System.out.println(photo.length);
			for (String string : photo) {
				String id = CLID.getID();
				TkClientPhoto record = new TkClientPhoto();
				record.setTkClientId(clid);
				record.setTkClientPhotoUrl(string);
				record.setTkClientPhotoId(id);
				int iii = TkClientPhotoMapper.insert(record);
				tkClientPhoto = id + ",";
			}
		}
		tkClientPhoto = tkClientPhoto.substring(0, tkClientPhoto.length() - 1);
		WyMap paMap = WyMap.getParameter(request);
		WyMap paMap1 = WyMap.getParameter(request);
		RetCode retCode = RetCode.getErrorCode();
		try {
			String tk_client_joindate = ToolsUtil.getDate();
			paMap.put("tk_client_photo", tkClientPhoto);
			paMap.put("tk_client_joindate", ToolsUtil.getDate());
			paMap.put("tk_client_fresh_date", tk_client_joindate);
			paMap1.put("tk_client_goods_id", CLID.getID());
			paMap1.put("tk_client_id", clid);
			/**
			 * tk_channel_id根据任务id获取
			 */
			paMap.put("tk_channel_id", "1");
			paMap.put("tk_client_type_id", "1");
			paMap.put("tk_client_id", clid);

			int ret = TkSystemMapper.postClientInfo(paMap);
			TkSystemMapper.postClientGoodsInfo(paMap1);
			WyMap wyMap = new WyMap();
			wyMap.put("tkPlanId", request.getParameter("tk_plan_id"));
			wyMap.put("tk_plan_detail_id", request.getParameter("tk_plan_id"));
			String tk_plan_detail_target_achieve = "";
			tk_plan_detail_target_achieve = TkPlanDetailMapper.getTargetAchieve(wyMap)
					.get("tk_plan_detail_target_achieve").toString();
			Integer target_achieve = Integer.parseInt(tk_plan_detail_target_achieve);
			target_achieve += 1;
			// 任务客户数+1
			wyMap.put("tk_plan_detail_target_achieve", target_achieve);
			TkPlanDetailMapper.updateTargetAchieve(wyMap);
			if (ret > 0) {
				retCode = RetCode.getSuccessCode(paMap);
			}
			// 拓客人数+1
			List<WyMap> TkUserMapperList = TkUserMapper.selectAll();
			for (WyMap wyMap2 : TkUserMapperList) {
				String tk_user_id = wyMap2.get("tk_user_id").toString();
				if (tk_user_id.equals(request.getParameter("tkUserId"))) {
					Integer amount = Integer.parseInt( wyMap2.get("tk_client_amount").toString());
					amount++;
					wyMap2.put("tk_client_amount", amount);
					TkUserMapper.updateClientAmount(wyMap2);
					break;
				}
			}
			retCode.put("msg", " ");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.vcxv#getClientNumByChannel(javax.servlet.http.
	 * HttpServletRequest)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.ClientServiceInterface#getClientNumByChannel(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	public RetCode getClientNumByChannel(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode = RetCode.getErrorCode();
		try {
			List list = TkSystemMapper.getNumGroupChannelByUserId(paMap);
			retCode = RetCode.getSuccessCode(list);
			retCode.put("msg", " tk_channel_name=>渠道名，num=>客户数" + "");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tkSystem.service.vcxv#putClientInfo(javax.servlet.http.
	 * HttpServletRequest)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tkSystem.service.ClientServiceInterface#putClientInfo(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public Object putClientInfo(HttpServletRequest request) {
		// 查看客户信息，修改tk_client_type_id tk_client_fresh_date tk_client_user_id
		String tk_client_type_id, tk_client_fresh_date, tk_client_user_id, tk_client_id;
		tk_client_user_id = request.getParameter("tkUserId");
		// 跟进时间
		tk_client_fresh_date = ToolsUtil.getDate();
		// 客户 待跟进状态
		tk_client_type_id = "3";
		// 客户id
		tk_client_id = request.getParameter("tk_client_id");

		WyMap paMap = new WyMap();
		paMap.put("tk_client_fresh_date", tk_client_fresh_date);
		paMap.put("tk_client_user_id", tk_client_user_id);
		paMap.put("tk_client_type_id", tk_client_type_id);
		paMap.put("tk_client_id", tk_client_id);
		RetCode retCode = RetCode.getErrorCode();
		try {
			int list = TkSystemMapper.putClientInfo(paMap);
			if (list > 0)
				retCode = RetCode.getSuccessCode(paMap);
			retCode.put("msg", " tk_client_type_id=>客户类型编号（3表示待跟进状态），tk_client_user_id=>跟进人id(用户id)" + ""
					+ " tk_client_fresh_date=>发起跟进开始时间，tk_client_id=>客户编号" + "");
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return retCode;
	}

}
