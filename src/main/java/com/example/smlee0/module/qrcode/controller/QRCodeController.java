package com.example.smlee0.module.qrcode.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.smlee0.module.qrcode.service.QRCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * QR 코드
 *
 * @author LEESEMIN
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class QRCodeController {

	private final QRCodeService qrCodeService;

	/**
	 * QR 코드 메인 화면
	 *
	 * @param map
	 * @return
	 */
	@GetMapping(value = "/qrcode")
	public String index(ModelMap map) {
		return "/qrcode/index";
	}

	/**
	 * QR 코드 생성기 화면
	 *
	 * @param map
	 * @return
	 */
	@GetMapping(value = "/qrcode/generate")
	public String generate(ModelMap map) {
		return "/qrcode/generate";
	}

	/**
	 * QR 코드 이미지 생성기
	 *
	 * @param map
	 * @return
	 */
	@GetMapping(value = "/qrcode/image-generate", produces = {
		MediaType.IMAGE_JPEG_VALUE,
		MediaType.IMAGE_PNG_VALUE,
	})
	public ResponseEntity<?> generateImage(ModelMap map) throws Exception {
		return ResponseEntity.ok(this.qrCodeService.generateQrCodeImage());
	}

	/**
	 * QR 코드 스캐너 화면
	 *
	 * @param map
	 * @return
	 */
	@GetMapping(value = "/qrcode/scanner")
	public String scanner(ModelMap map) {
		return "/qrcode/scanner";
	}

}
