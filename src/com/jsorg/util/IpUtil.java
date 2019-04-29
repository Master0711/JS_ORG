package com.jsorg.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	public String getip(HttpServletRequest request) {
		String register_ip = request.getHeader("x-forwarded-for");
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getHeader("Proxy-Client-register_ip");
		}
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getHeader("WL-Proxy-Client-register_ip");
		}
		if(register_ip == null || register_ip.length() == 0 ||"unknown".equalsIgnoreCase(register_ip)) {
			register_ip = request.getRemoteAddr();
		}
		return register_ip;
	}
}
