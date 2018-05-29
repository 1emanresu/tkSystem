package com.tkSystem.service.impl;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkSystem.dao.entity.TkUser;
import com.tkSystem.dao.entity.WechatUser;
import com.tkSystem.dao.mapper.TkUserMapper;
import com.tkSystem.dao.mapper.WechatUserMapper;
import com.tkSystem.service.UserServiceInterface;
import com.tkSystem.tools.CLID;
import com.tkSystem.tools.RedisUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ShortMessageService;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
	TkUserMapper tkUserMapper;
	@Autowired
	WechatUserMapper wechatUserMapper;

	ToolsUtil toolsUtil = new ToolsUtil();

	/* (non-Javadoc)
	 * @see com.tkSystem.service.UserServiceInterface#postUserRegistration(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode postUserRegistration(WyMap paMap) {
		RetCode retCode = null;
		try {
			paMap.put("tkUserId", CLID.getID());
			paMap.put("tkUserPassword", toolsUtil.getMD5(paMap.get("tkUserPassword").toString()));// 登录密码
			paMap.put("tkUserTime", toolsUtil.getDate());// 注册时间
			Integer ret = tkUserMapper.insert(paMap);
			if (ret > 0) {
				retCode = retCode.getSuccessCode("用户注册成功");
			} else {
				retCode = retCode.getErrorCode("用户名已存在,有事与管理员联系！");
			}
		} catch (Exception e) {
			return retCode = RetCode.getErrorCode("用户名已存在,有事与管理员联系！");
		}

		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.UserServiceInterface#putModifyPwd(com.tkSystem.tools.WyMap)
	 */
	@Override
	public RetCode putModifyPwd(WyMap paMap) {
		RetCode retCode = null;
		try {
			TkUser tkUser = new TkUser();
			paMap.put("tkUserPassword", toolsUtil.getMD5(paMap.get("tkUserPassword").toString()));// 登录密码
			paMap.put("tkUserTime", toolsUtil.getDate());// 注册时间
			Integer ret = tkUserMapper.updateByPasswordSelective(paMap);
			if (ret > 0) {
				retCode = retCode.getSuccessCode("修改成功");
			} else {
				retCode = retCode.getErrorCode("修改失败,有事与管理员联系！");
			}
		} catch (Exception e) {
			retCode = retCode.getErrorCode("修改失败,有事与管理员联系！");
			return retCode;
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.UserServiceInterface#userLogin(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode userLogin(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode = RetCode.getErrorCode();
		String md5Pwd, tkUserLoginIp, tkUserTime, tkUserToken;
		WyMap Map, paMap = WyMap.getParameter(request);
		try {
			md5Pwd = ToolsUtil.getMD5(paMap.get("tkUserPassword").toString());
			paMap.put("tkUserPassword", md5Pwd);
			TkUser ret = tkUserMapper.userlogin(paMap);
			/**
			 * 用户登陆成功保存token 或登陆ip 时间等信息
			 */
			tkUserTime = ToolsUtil.getDate();
			tkUserToken = ToolsUtil.getUUID();
			tkUserLoginIp = request.getRemoteAddr();
			Map = new WyMap();
			Map.put("tkUserToken", tkUserToken);
			Map.put("tkUserLoginIp", tkUserLoginIp);
			Map.put("tkUserTime", tkUserTime);
			Map.put("tkUserId", ret.getTkUserId());
			int retc = tkUserMapper.updateByPrimaryKeySelective(Map);
			RedisUtil.saveToken(tkUserToken, ret.getTkUserId());
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

	/* (non-Javadoc)
	 * @see com.tkSystem.service.UserServiceInterface#wechatLogin(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode wechatLogin(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode = RetCode.getErrorCode();
		String md5Pwd, tkUserLoginIp, tkUserTime, tkUserToken, nickName, avatarUrl, openid;
		WyMap Map, paMap = WyMap.getParameter(request);
		try {
			openid = request.getParameter("openid").trim();
			avatarUrl = request.getParameter("avatarUrl").trim();
			nickName = request.getParameter("nickName").trim();
			WechatUser wechatUser = wechatUserMapper.selectByPrimaryKey(openid);
			int ret = 0;
			if (wechatUser == null) {
				wechatUser = new WechatUser();
				wechatUser.setAvatarUrl(avatarUrl);
				wechatUser.setNickName(nickName);
				wechatUser.setOpenid(openid);
				ret = wechatUserMapper.insert(wechatUser);
			} else {
				wechatUser = new WechatUser();
				wechatUser.setAvatarUrl(avatarUrl);
				wechatUser.setNickName(nickName);
				wechatUser.setOpenid(openid);
				ret = wechatUserMapper.updateByPrimaryKeySelective(wechatUser);
			}
			if (ret > 0) {

			} else {
				throw new Exception();
			}

			TkUser ret1 = tkUserMapper.wechatLogin(paMap);
			int retc = 0;
			tkUserTime = ToolsUtil.getDate();
			tkUserToken = ToolsUtil.getUUID();
			tkUserLoginIp = request.getRemoteAddr();
			md5Pwd = ToolsUtil.getMD5("");
			Map = new WyMap();
			Map.put("tkUserToken", tkUserToken);
			Map.put("tkUserPassword", md5Pwd);
			Map.put("tkUserLoginIp", tkUserLoginIp);
			Map.put("tkUserTime", tkUserTime);
			Map.put("openid", openid);
			Map.put("tkUserHead", avatarUrl);
			Map.put("tk_user_type_id", "0");
			if (ret1 == null) {
				Map.put("tkUserId", CLID.getID());
				Map.put("tkUserTypeId", "0");
				Map.put("tk_client_amount", "0");
				retc = tkUserMapper.insertSelective(Map);
				Map.put("tkUserName", null);
				Map.put("tkUserPhone", null);
				ret1 = new TkUser();
				ret1.setTkUserId(Map.get("tkUserId").toString());
			} else {
				Map.put("tkUserId", ret1.getTkUserId());
				retc = tkUserMapper.updateByPrimaryKeySelective(Map);
				Map.put("tkUserName", ret1.getTkUserName());
				Map.put("tkUserPhone", ret1.getTkUserPhone());
				Map.put("tkUserTypeId", ret1.getTkUserTypeId());
			}
			/**
			 * 用户登陆成功保存token 或登陆ip 时间等信息
			 */

			RedisUtil.saveToken(tkUserToken, ret1.getTkUserId());
			if (retc > 0) {
				ret1.setTkUserToken(tkUserToken);
				retCode = RetCode.getSuccessCode(Map);
			} else {
				retCode = RetCode.getErrorCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RetCode.getErrorCode();
		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.UserServiceInterface#getVail(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode getVail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RetCode retCode = RetCode.getErrorCode();
		String vail, tkUserPhone;
		String[] str = { "tkUserPhone" };
		WyMap paMap;
		paMap = WyMap.getParameter(request, str);
		HashMap Map;
		try {
			tkUserPhone = paMap.get("tkUserPhone").toString().trim();
			int yzm = ToolsUtil.getVerificationCode();
			Map = ShortMessageService.sendSms(tkUserPhone, new Byte("2"), String.valueOf(yzm));
			if (!Map.get("state").toString().equals("OK")) {
				throw new Exception();
			}
			vail = Map.get("context").toString();
			if (vail == null || vail.trim() == "") {
				throw new Exception();
			} else {
				RedisUtil.set(tkUserPhone, vail, 60);
			}
			retCode = RetCode.getSuccessCode("发送验证码已成功");
		} catch (Exception e) {

		}
		return retCode;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.UserServiceInterface#postVail(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Boolean postVail(HttpServletRequest request, HttpServletResponse response) {
		String vail, tkUserPhone;
		Boolean bool = false;
		WyMap paMap = WyMap.getParameter(request);
		try {
			tkUserPhone = paMap.get("tkUserPhone").toString().trim();
			vail = paMap.get("vail").toString().trim();
			bool = RedisUtil.valiToken(vail, tkUserPhone);
		} catch (Exception e) {

		}
		return bool;
	}

	/* (non-Javadoc)
	 * @see com.tkSystem.service.UserServiceInterface#putPhoneAndName(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public RetCode putPhoneAndName(HttpServletRequest request, HttpServletResponse response) {
		RetCode retCode = RetCode.getErrorCode();
		String[] str= {"tkUserPhone"
				,"tkUserName"
				,"vail"
				};
		WyMap paMap = WyMap.getParameter(request,str);
		try {
			if (postVail(request, response)) {
				int ret = tkUserMapper.updateByPrimaryKeySelective(paMap);
				if (ret > 0) {
					retCode = RetCode.getSuccessCode("绑定手机号码和姓名成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retCode;
	}
}
