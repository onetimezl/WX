package cn.com.bugu.weixin.untils;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {
	/**
	 * 获取应用的根路径
	 * @return
	 */
	public static String getRootDir(){
		String rootDir = getClassPath().concat("../");
		return rootDir;
	}
	
	/**
	 * 获取应用的classpath
	 * @return
	 */
	public static String getClassPath(){
		String classPath = PathUtil.class.getResource("/").getPath();
		return classPath;
	}
	
	/**
	 * 获取项目路径
	 * @param request
	 * @return
	 */
	public static String getProjectUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath(); 
		return request.getScheme()+"://"+request.getServerName()+":"+
         request.getServerPort()+contextPath+"/";
	}
	
	/**
	 * 获取请求的URL
	 * @param request
	 * @return
	 */
	public static String getReqUrl(HttpServletRequest request) {
		StringBuffer reqUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			reqUrl.append("?");
			reqUrl.append(request.getQueryString());
		}
		return reqUrl.toString();
	}
}
