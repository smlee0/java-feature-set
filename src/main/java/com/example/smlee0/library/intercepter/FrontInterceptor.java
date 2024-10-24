package com.example.smlee0.library.intercepter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.smlee0.library.util.AppUtil;
import com.example.smlee0.library.util.ClientUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 프론트 인터셉터 클래스
 *
 * @author LEESEMIN
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FrontInterceptor implements HandlerInterceptor {

	/**
	 * 애플리케이션 활용
	 */
	private final AppUtil appUtil;

	/**
	 * 이전 핸들
	 *
	 * @param request 현재 HTTP 요청
	 * @param response 현재 HTTP 응답

	 * @return 부울
	 * @throws Exception 모든 예외
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 관심 있는 것은 Controller에 있는 메서드이므로 HandlerMethod 타입인지 체크
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		String requestURI = request.getRequestURI(); // 요청 URI
		String requestMethod = request.getMethod(); // 요청 메소드
		String clientIp = ClientUtil.getClientIp(); // 클라이언트 IP

		String userAgent = ClientUtil.getClientUserAgent(); // 클라이언트 에이전트 정보
		String clientOS = ClientUtil.getClientOS(); // 클라이언트 OS
		String clientBrowser = ClientUtil.getClientBrowser(); // 클라이언트 브라우저 정보
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); // ISO 8601
		String currentServerDateTime = sdf.format(date); // 서버 시간

		log.info("======================= FrontInterceptor[preHandle] =======================");
		log.info("requestURI: {}", requestURI);
		log.info("requestMethod: {}", requestMethod);
		log.info("clientIp: {}", clientIp);
		log.info("userAgent: {}", userAgent);
		log.info("clientOS: {}", clientOS);
		log.info("clientBrowser: {}", clientBrowser);
		log.info("currentServerDateTime: {}", currentServerDateTime);

		return true;
	}

}
