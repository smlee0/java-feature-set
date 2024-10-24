package com.example.smlee0.module.qrcode.service;

import org.springframework.stereotype.Service;

import com.example.smlee0.library.util.QRGeneratorUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QRCodeService {

	public byte[] generateQrCodeImage() throws Exception {
		// String encodedContent = KeyPairGeneratorUtil.encryptData(realUser.getPuuid(), PropertiesUtil.get("qr.public.key"));

		return QRGeneratorUtil.generateQrCodeImage("test", 356, 356);
	}

}
