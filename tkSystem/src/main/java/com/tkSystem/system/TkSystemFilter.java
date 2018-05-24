package com.tkSystem.system;

import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tkSystem.tools.RedisUtil;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebFilter(filterName = "TokenFilter", urlPatterns = "/*")
public class TkSystemFilter implements Filter {
	Logger log = Logger.getLogger("com.tkSystem.system.TkSystemFilter");

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		String Method = request.getMethod();
		log.info("请求url" + request.getRequestURI().toString());
		Enumeration<String> srcList = request.getParameterNames();
		while (srcList.hasMoreElements()) {
			String key, value;
			key = srcList.nextElement();
			value = request.getParameter(key).trim();
			log.info("请求参数键值对" + key + "==" + value);
		}
		
		if (loginUrl(request, response)) {
		} else {
			  tokenFilter(request, response );
		}
		
		chain.doFilter(request, response);
		System.out.println(response.getStatus());
	}

	public void init(FilterConfig config) throws ServletException {
		log.info("com.tkSystem.tools.TkSystemFilter reload");

	}

	public void tokenFilter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Method=request.getMethod();
		if (request.getQueryString()==null) {

		}else {
			String message = "请发送post请求,url不附带参数";
			ResponseUtil.write(response, RetCode.getErrorCode(message));
			return;
		}
		if (Method.equals("GET")) {
			String message = "请发送post请求";
			ResponseUtil.write(response, RetCode.getErrorCode(message));
			return;
		} else if (Method.equals("POST")) {

		} else {

		}
		String message;
		String tkUserToken = request.getParameter("tkUserToken");
		String tkUserId = request.getParameter("tkUserId");
		if (tkUserToken == null || tkUserToken.trim().equals("")) {
			message = "请返回重新登陆，tkUserToken == null";
			ResponseUtil.write(response, RetCode.getErrorCode(message));
			return;
		}
		if (tkUserId == null || tkUserToken.trim().equals("")) {
			message = "请返回重新登陆，tkUserId == null";
			ResponseUtil.write(response, RetCode.getErrorCode(message));
			return;
		}
		if (!RedisUtil.valiToken(tkUserToken, tkUserId)) {
			message = "请返回重新登陆，token失效";
			ResponseUtil.write(response, RetCode.getErrorCode(message));
			return;
		}
		
	}

	public boolean loginUrl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURL = request.getRequestURI().toString();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String LoginURL = path + "/user/userLogin";
		String postUserRegistrationURL = path + "/user/postUserRegistration";
		String putModifyPwdURL = path + "/user/putModifyPwd";
		String wechatLogin = path + "/user/wechatLogin";
		String getByCode = path + "/user/getByCode";
		String staticimg = path + "/static/img";
		response.setContentType("image/jpg");
		String FileUploadServlet = path + "/FileUploadServlet";
		String containsstr[] = { staticimg };
		String equalsstr[] = { path + "/", LoginURL, postUserRegistrationURL, putModifyPwdURL, wechatLogin, getByCode,
				FileUploadServlet, staticimg };
		if (contains(requestURL, containsstr) || equals(requestURL, equalsstr))
			return true;
		return false;
	}

	private boolean contains(String requestURL, String... str) {
		for (String string : str) {
			if (requestURL.contains(string))
				return true;
		}
		return false;
	}

	private boolean equals(String requestURL, String... str) {
		for (String string : str) {
			if (requestURL.equals(string))
				return true;
		}
		return false;
	}

}