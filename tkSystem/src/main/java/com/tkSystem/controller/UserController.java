package com.tkSystem.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tkSystem.service.UserServiceInterface;
import com.tkSystem.tools.ImageUpload;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;
import com.tkSystem.tools.ToolsUtil;
import com.tkSystem.tools.WyMap;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserServiceInterface userService;

	/**
	 * 用户注册 tkUserName 账户 tkUserPassword 密码
	 */
	@RequestMapping("postUserRegistration")
	public void postUserRegistration(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("tkUserName") == null || request.getParameter("tkUserName").trim() == "") {
				throw new Exception("tkUserName格式有误");
			}
			if (request.getParameter("tkUserPassword") == null || request.getParameter("tkUserPassword").trim() == "") {
				throw new Exception("tkUserPassword格式有误");
			}
			WyMap paMap = WyMap.getParameter(request);
			RetCode retCode = userService.postUserRegistration(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=> tkUserNametk,UserPassword不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}

	}

	/**
	 * 修改密码
	 */
	@RequestMapping("putModifyPwd")
	public void putModifyPwd(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("tkUserName") == null || request.getParameter("tkUserName").trim() == "") {
				throw new Exception("tkUserName格式有误");
			}
			if (request.getParameter("tkUserPassword") == null || request.getParameter("tkUserPassword").trim() == "") {
				throw new Exception("tkUserPassword格式有误");
			}
			if (request.getParameter("newtkUserPassword") == null
					|| request.getParameter("tkUserPassword").trim() == "") {
				throw new Exception("newtkUserPassword格式有误");
			}
			WyMap paMap = WyMap.getParameter(request);
			RetCode retCode = userService.putModifyPwd(paMap);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "=> tkUserNametk,UserPassword,newtkUserPassword不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/*
	 * 用户登录
	 */@RequestMapping("userLogin")
	public void userLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("tkUserName") == null || request.getParameter("tkUserName").trim() == "") {
				throw new Exception("tkUserName格式有误");
			}
			if (request.getParameter("tkUserPassword") == null || request.getParameter("tkUserPassword").trim() == "") {
				throw new Exception("tkUserPassword格式有误");
			}
			ResponseUtil.write(response, userService.userLogin(request, response));
		} catch (Exception e) {
			String msg = "=> tkUserNametk,UserPassword不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/*
	 * 微信登录
	 */
	@RequestMapping("wechatLogin")
	public void wechatLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("nickName") == null || request.getParameter("nickName").trim() == "") {
				throw new Exception("nickName格式有误");
			}
			if (request.getParameter("avatarUrl") == null || request.getParameter("avatarUrl").trim() == "") {
				throw new Exception("avatarUrl格式有误");
			}
			if (request.getParameter("openid") == null || request.getParameter("openid").trim() == "") {
				throw new Exception("openid格式有误");
			}
			ResponseUtil.write(response, userService.wechatLogin(request, response));
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "=> nickName,avatarUrl,openid不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/*
	 * 微信登录获取openId
	 */
	@RequestMapping("getByCode")
	public void getByCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("code") == null || request.getParameter("code").trim() == "") {
				throw new Exception("code格式有误");
			}
			String url, result;
			url = "https://api.weixin.qq.com/sns/jscode2session";
			Map map = new HashMap();
			String appid, secret, js_code, grant_type;
			appid = "wx87bdf521f0573ad0";
			secret = "7633aa4a28bc49b77f1a289de916b95e";
			grant_type = "authorization_code";
			js_code = request.getParameter("code");
			map.put("appid", appid);
			map.put("secret", secret);
			map.put("grant_type", grant_type);
			map.put("js_code", js_code);
			result = ToolsUtil.sendPost(url, map);
			JSONObject jsonObject = JSONObject.fromObject(result);
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "=> nickName,avatarUrl,openid不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 获取用户头像
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getHead")
	public void getHead(HttpServletRequest request, HttpServletResponse response) {
		try {
			String path = request.getContextPath();
			String staticUrl = "/static/img/user/";
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String headPath = basePath + staticUrl + request.getParameter("tkUserId") + "/"
					+ request.getParameter("tkUserId") + ".PNG";

			System.out.println(headPath);
			ResponseUtil.write(response, RetCode.getErrorCode(headPath));
		} catch (Exception e) {
			String msg = " ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 上传用户头像
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("uploadHead")
	public void uploadHead(HttpServletRequest request, HttpServletResponse response) {

		try {
			if (request.getParameter("tkUserHead") == null || request.getParameter("tkUserHead").trim() == "") {
				throw new Exception("tkUserHead格式有误");
			}
			String adddres, fileName_img, imgBase;
			ImageUpload ImageUpload = new ImageUpload();
			adddres = request.getRealPath("\\static\\img\\user\\");
			fileName_img = request.getParameter("tkUserId");
			imgBase = request.getParameter("tkUserHead");
			String result = ImageUpload.uploadImg(adddres, fileName_img, imgBase);
			System.out.println(result);
			ResponseUtil.write(response, RetCode.getSuccessCode(result));
		} catch (Exception e) {
			String msg = "=> tkUserHead不能为空";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 发送验证嘛
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getVail")
	public void getVail(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getParameter("tkUserPhone") == null || request.getParameter("tkUserPhone").trim() == "") {
				throw new Exception("tkUserPhone格式有误");
			}
			ResponseUtil.write(response, userService.getVail(request, response));
		} catch (Exception e) {
			String msg = "tkUserPhone手机号码不能为空 ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 绑定手机号码 和姓名
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("putPhoneAndName")
	public void putPhoneAndName(HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] str= {"tkUserPhone"
					,"tkUserName"
					,"vail"
					};
			ToolsUtil.requestIsNull(request, str);
			ResponseUtil.write(response, userService.putPhoneAndName(request, response));
		} catch (Exception e) {
			String msg = "tkUserPhone手机号码,tkUserName用户名,vail验证码不能为空 ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

	/**
	 * 获取二维码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getQCode")
	public void getQCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			String filePath = request.getRealPath("/static/img\\photo/");
			String tkUserId = request.getParameter("tkUserId");
			String path = request.getContextPath();
			String staticUrl = "static/img/photo/";
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String url = basePath + staticUrl + tkUserId + ".png";
			String fileName = tkUserId + ".png";
			File file1 = new File(filePath +"/"+ fileName);
			if (file1.exists()) {// 判断文件目录的存在
				RetCode retCode = RetCode.getSuccessCode();
				retCode.put("url", url);
				ResponseUtil.write(response, retCode);
				return;
			}
			new File(filePath).mkdirs();
			WyMap json = new WyMap();
			json.put("tkUserId", tkUserId);
			System.out.println(ToolsUtil.MatrixToImageWriter(filePath, fileName, json));

			RetCode retCode = RetCode.getSuccessCode();
			retCode.put("url", url);
			ResponseUtil.write(response, retCode);
		} catch (Exception e) {
			String msg = "  ";
			ResponseUtil.write(response, RetCode.getErrorCode(e.getMessage() + msg));
		}
	}

}
