package com.tkSystem.system;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tkSystem.tools.CLID;
import com.tkSystem.tools.RedisUtil;
import com.tkSystem.tools.ResponseUtil;
import com.tkSystem.tools.RetCode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebFilter(filterName = "UploadFilter", urlPatterns = "/*")
public class UploadFilter implements Filter {
	Logger log = Logger.getLogger("com.tkSystem.tools.UploadFilter");
	public void destroy() {
	}
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String FileUploadServlet = request.getContextPath() + "/FileUploadServlet";
		if (request.getRequestURI().toString().equals(FileUploadServlet)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list;
			RetCode retCode = RetCode.getErrorCode();
			try {
				String tkUserToken = "", tkUserId = "", message = "";
				list = (List<FileItem>) upload.parseRequest(request);
				FileItem file = null;
				for (FileItem item : list) {
				
					// 获取表单的属性名字
					String name = item.getFieldName();
					// 如果获取的 表单信息是普通的 文本 信息
					System.out.println(name);
					if (name.trim().equals("tkUserToken")) {
						tkUserToken = item.getString();
					}
					if (name.trim().equals("tkUserId")) {
						tkUserId = item.getString();
					}
					if (name.trim().equals("file")) {
						file = item;
					}

				}
				if(tkUserId.isEmpty()) {
					message = "请返回重新登陆，tkUserId == null";
					ResponseUtil.write(response, RetCode.getErrorCode(message));
					return;
				}
				if(tkUserToken.isEmpty()) {
					message = "请返回重新登陆，tkUserToken == null";
					ResponseUtil.write(response, RetCode.getErrorCode(message));
					return;
				}
				if (!RedisUtil.valiToken(tkUserToken, tkUserId)) {
					message = "请返回重新登陆，token失效";
					ResponseUtil.write(response, RetCode.getErrorCode(message));
					return;
				}
				if (file == null) {
				} else {
					byte[] bytes = file.get();
					for (int i = 0; i < bytes.length; ++i) {
						if (bytes[i] < 0) {// 调整异常数据
							bytes[i] += 256;
						}
					}
					String imgFilePath, url, clid;
					clid = CLID.getID();
					
					  imgFilePath = request.getRealPath("/static/img/photo/"); 
					  new File(imgFilePath).mkdirs();
					 
					//imgFilePath = "C:\\xampp\\htdocs\\img";
					String imgName = imgFilePath + "/" + clid   ;
					OutputStream out = new FileOutputStream(imgName);
					out.write(bytes);
					out.flush();
					out.close();
					url = request.getScheme() + "://" + request.getServerName()+ ":" +request.getServerPort()+ request.getContextPath()+ "/" + "static/img/photo" + "/" + clid  ;
					//url = request.getScheme() + "://" + request.getServerName() + "/" + "img" + "/" + clid + ".PNG";
					retCode = RetCode.getSuccessCode("上传成功");
					retCode.put("url", url);
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			ResponseUtil.write(response, retCode);
			return;
		}else {
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig config) throws ServletException {
		log.info("com.tkSystem.tools.UploadFilter reload");
	}

}