package com.sao.easyui.test.controller.interceptor;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor{
	private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception err)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView mov) throws Exception {
		String host = request.getRemoteHost();
		String port = String.valueOf(request.getRemotePort());
		logger.info(String.format("远程用户[%s]结束访问 URL -> %s", host + ":" + port, request.getRequestURI()));
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object objcet) throws Exception {
		String host = request.getRemoteHost();
		String port = String.valueOf(request.getRemotePort());
		logger.info(String.format("远程用户[%s]正在访问 URL -> %s", host + ":" + port, request.getRequestURI()));

		printParameters(request);
		
    	String path = request.getServletContext().getContextPath();
    	String basePath = request.getScheme() + "://" 
    			+ request.getServerName() + ":" 
    			+ request.getServerPort() + path + "/";
    	request.setAttribute("basePath", basePath);
		
		return true;
	}
	
	/**
	 * 打印请求参数
	 * @param request
	 */
	private void printParameters(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		for(Entry entry : params.entrySet()){
			logger.info(String.format("param key:[%s], value:[%s]", entry.getKey(), Arrays.toString((String[])entry.getValue())));
		}
	}

}
