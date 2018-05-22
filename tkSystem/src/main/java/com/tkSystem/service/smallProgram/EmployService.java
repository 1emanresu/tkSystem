package com.tkSystem.service.smallProgram;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.dao.entity.TkUserGrade;
import com.tkSystem.dao.mapper.TkGoodMapper;
import com.tkSystem.dao.mapper.TkPlanMapper;
import com.tkSystem.dao.mapper.TkUserGradeMapper;
import com.tkSystem.dao.mapper.TkUserMapper;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.RedisUtil;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Service("employService")
public class EmployService {
	@Resource
	private TkUserMapper TkUserDao;
	@Resource
	private TkUserGradeMapper TkUserGradeMapper;

	/**
	 * 拓客人员管理 (绑定拓客人员,解除拓客人员)
	 * 
	 */
	/**
	 * 绑定拓客人员
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public RetCode postEmployee(HttpServletRequest request) {
		RetCode retCode;
		WyMap paMap = WyMap.getParameter(request);
		try {
			String tkUserGradeId, tkUserGradeName, tkUserGradeTime, tkUserGradePid, tkUserGradeGnid;
			tkUserGradeId = paMap.get("userId").toString();
			tkUserGradeTime = ToolsUtil.getDate();
			tkUserGradePid = paMap.get("tkUserId").toString();
			TkUserGrade record = new TkUserGrade();
			record.setTkUserGradeId(tkUserGradeId);
			int ret = TkUserGradeMapper.insertSelective(record);
			retCode = RetCode.getSuccessCode(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}

		return retCode;
	}

	public RetCode getEmployee(HttpServletRequest request) {
		RetCode retCode;
		try {

			WyMap paMap = WyMap.getParameter(request);

			java.util.List<WyMap> list = TkUserDao.getClientAmount(paMap);
			java.util.List<WyMap> list1 = new ArrayList<>();
			for (WyMap object : list) {
				object.put("isSelect", false);
				list1.add(object);
			}
			retCode = RetCode.getSuccessCode(list1);

			retCode.put("msg",
					"isSelect=>是否选中,tk_client_amount=>拓客数量,tk_user_head=>头像地址,tk_user_phone=>用户手机号码,tk_user_name=>用户名字,tk_user_id=>用户id");
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}

		return retCode;
	}

	public RetCode getEmployeeByName(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode;

		try {

			WyMap paMap = WyMap.getParameter(request);
			if (paMap.get("tkUserName") == null) {
				paMap.put("tkUserName", "");
			}
			String tkUserName = "%" + paMap.get("tkUserName").toString() + "%";
			paMap.put("tkUserName", tkUserName);
			retCode = RetCode.getSuccessCode(TkUserDao.getEmployeeByName(paMap));
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}

		return retCode;
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public RetCode deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode;
		WyMap paMap = WyMap.getParameter(request);
		try {
			int ret = TkUserDao.deleteByPrimaryKey(paMap);
			retCode = RetCode.getSuccessCode(ret);
		} catch (Exception e) {
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/**
	 * 解除拓客人员
	 * 
	 * @param request
	 * @return
	 */
	public RetCode updateGradePid(HttpServletRequest request) {
		RetCode retCode;
		WyMap paMap = WyMap.getParameter(request);
		try {
			int ret = TkUserGradeMapper.updateGradePid(paMap);
			retCode = RetCode.getSuccessCode(ret);
		} catch (Exception e) {
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public RetCode userLogin(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode;
		String md5Pwd, tkUserLoginIp, tkUserTime, tkUserToken;
		WyMap Map, paMap = WyMap.getParameter(request);
		try {
			md5Pwd = ToolsUtil.getMD5(paMap.get("tkUserPassword").toString());
			paMap.put("tkUserPassword", md5Pwd);
			TkUser ret = TkUserDao.userlogin(paMap);
			/**
			 * 用户登陆成功保存token 或登陆ip 时间等信息
			 */
			tkUserTime = new Date().toString();
			tkUserToken = ToolsUtil.getUUID();
			tkUserLoginIp = request.getRemoteAddr();
			Map = new WyMap();
			Map.put("tkUserToken", tkUserToken);
			Map.put("tkUserLoginIp", tkUserLoginIp);
			Map.put("tkUserTime", tkUserTime);
			Map.put("tkUserId", ret.getTkUserId());
			int retc = TkUserDao.updateByPrimaryKeySelective(Map);

			if (retc > 0) {
				ret.setTkUserToken(tkUserToken);
				retCode = RetCode.getSuccessCode(ret);
			} else {
				retCode = RetCode.getErrorCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	public Object getTeamIndex(HttpServletRequest request) {
		WyMap paMap = WyMap.getParameter(request);
		RetCode retCode;
		try {
			java.util.List<WyMap> list1 = new ArrayList();

			java.util.List<WyMap> list = TkUserDao.getTeamIndex(paMap);
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

}
