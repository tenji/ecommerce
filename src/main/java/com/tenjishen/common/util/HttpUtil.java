package com.tenjishen.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http工具类
 * 
 * @author TENJI
 *
 */
public class HttpUtil {
	
	/**
	 * 获取IPv4
	 * 
	 * @param request 
	 * @return
	 */
	public static String getIpv4(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 根据动态页面生成静态页面
	 * 
	 * @param url 动态页面URL
	 * @param fileName 输出的静态文件名
	 * @throws IOException
	 */
	public static void createHTML(String url, String fileName) throws IOException {
		URL urlC = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlC
                .openConnection();
        InputStream ips = connection.getInputStream();
        FileOutputStream fos = new FileOutputStream(fileName);
        challage(ips, fos);
        ips.close();
        fos.close();
	}
	
	private static void challage(InputStream ips, OutputStream ops) throws IOException {
		byte[] contents = new byte[1024];
        int len = 0;
        while ((len = ips.read(contents)) != -1) {
            ops.write(contents, 0, len);
        }
	}
	
	/**
	 * 判断该请求是否AJAX请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With"); // 获取请求类型
		if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 通过response流输出字符串
	 * 
	 * @author TENJI
	 * @param responseString 需要输出的字符串
	 */
	public static void responseJson(String responseString, HttpServletResponse response) {
		// 设置报头
		response.setContentType("text/plain; charset=UTF-8");
		
		try {
			// 将JSON字符串输出
			response.getWriter().write(responseString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
