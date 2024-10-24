package com.example.smlee0.library.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 클라이언트 관련 유틸리티 클래스
 *
 * @author LEESEMIN
 *
 */
@Slf4j
public class ClientUtil {

	/**
	 * ip 반환<br>
	 *
	 * @return ip
	 */
	public static String getClientIp() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = request.getHeader("x-original-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-FORWARDED-FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 접속 브라우저 반환<br>
	 *
	 * @return 브라우저 정보
	 */
	public static String getClientBrowser() {
		String userAgent = getClientUserAgent();

		String browser = "Unknown Browser";
		try {
			if (userAgent.indexOf("naver") > -1) { // 네이버 인앱 브라우저
				browser = "Naver";
			} else if (userAgent.indexOf("kakaotalk") > -1) { // 카카오톡 인앱 브라우저
				browser = "KakaoTalk";
			} else if (userAgent.indexOf("samsungbrowser") > -1) { // 삼성 브라우저
				browser = "Samsung";
			} else if (StringUtils.indexOf(userAgent, "edge/") > -1 || StringUtils.indexOf(userAgent, "edg/") > -1) {
				browser = "Edge";
			} else if (StringUtils.indexOf(userAgent, "trident/7.0") > -1) {
				browser = "IE11";
			} else if (StringUtils.indexOf(userAgent, "msie 10") > -1) {
				browser = "IE10";
			} else if (StringUtils.indexOf(userAgent, "msie 9") > -1) {
				browser = "IE9";
			} else if (StringUtils.indexOf(userAgent, "msie 8") > -1) {
				browser = "IE8";
			} else if (StringUtils.indexOf(userAgent, "msie 7") > -1) {
				if (StringUtils.indexOf(userAgent, "trident/4.0") > -1) {
					browser = "IE8";
				} else if (StringUtils.indexOf(userAgent, "trident/5.0") > -1) {
					browser = "IE9";
				} else if (StringUtils.indexOf(userAgent, "trident/6.0") > -1) {
					browser = "IE10";
				} else if (StringUtils.indexOf(userAgent, "trident/7.0") > -1) {
					browser = "IE11";
				}
			} else if (userAgent.indexOf("whale") > -1) { // whale 브라우저
				browser = "Whale";
			} else if (StringUtils.indexOf(userAgent, "firefox/") > -1) {
				browser = "Firefox";
			} else if (StringUtils.indexOf(userAgent, "chrome/") > -1 && StringUtils.indexOf(userAgent, "safari/") > -1
				&& StringUtils.indexOf(userAgent, "opr/") > -1) {
				browser = "Opera";
			} else if (StringUtils.indexOf(userAgent, "chrome/") > -1 && StringUtils.indexOf(userAgent, "safari/") > -1) {
				browser = "Google Chrome";
			} else if (StringUtils.indexOf(userAgent, "safari/") > -1) {
				browser = "Safari";
			} else if (StringUtils.indexOf(userAgent, "thunderbird") > -1) {
				browser = "Thunderbird";
			}
		} catch (Exception e) {
			log.error("Error: {}", e.toString(), e);
		}

		return browser;
	}

	/**
	 * 접속 OS 반환<br>
	 *
	 * @return OS 정보
	 */
	public static String getClientOS() {
		String userAgent = getClientUserAgent();

		String os = "Unknown OS";

		try {
			if (StringUtils.indexOf(userAgent, "windows nt 10.0") > -1) {
				os = "Windows10";
			} else if (StringUtils.indexOf(userAgent, "windows nt 6.1") > -1) {
				os = "Windows7";
			} else if (StringUtils.indexOf(userAgent, "windows nt 6.2") > -1 || StringUtils.indexOf(userAgent, "windows nt 6.3") > -1) {
				os = "Windows8";
			} else if (StringUtils.indexOf(userAgent, "windows nt 6.0") > -1) {
				os = "WindowsVista";
			} else if (StringUtils.indexOf(userAgent, "windows nt 5.1") > -1) {
				os = "WindowsXP";
			} else if (StringUtils.indexOf(userAgent, "windows nt 5.0") > -1) {
				os = "Windows2000";
			} else if (StringUtils.indexOf(userAgent, "windows nt 4.0") > -1) {
				os = "WindowsNT";
			} else if (StringUtils.indexOf(userAgent, "windows 98") > -1) {
				os = "Windows98";
			} else if (StringUtils.indexOf(userAgent, "windows 95") > -1) {
				os = "Windows95";
			} else if (StringUtils.indexOf(userAgent, "iphone") > -1) {
				os = "iPhone";
			} else if (StringUtils.indexOf(userAgent, "ipad") > -1) {
				os = "iPad";
			} else if (StringUtils.indexOf(userAgent, "android") > -1) {
				os = "android";
			} else if (StringUtils.indexOf(userAgent, "mac") > -1) {
				os = "mac";
			} else if (StringUtils.indexOf(userAgent, "linux") > -1) {
				os = "Linux";
			}
		} catch (Exception e) {
			log.error("Error: {}", e.toString(), e);
		}

		return os;
	}

	/**
	 * 클라이언트 유저 에이전트 조회
	 *
	 * @return 클라이언트 유저 에이전트
	 */
	public static String getClientUserAgent() {
		HttpServletRequest request = null;
		String userAgent = "";
		try {
			request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			userAgent = StringUtils.defaultIfBlank(request.getHeader("user-agent"), "").toLowerCase();
		} catch (Exception e) {
			log.error("Error: {}", e.toString(), e);
		}

		return userAgent;
	}
}
