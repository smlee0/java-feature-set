package com.example.smlee0.library.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRGeneratorUtil {

	public static byte[] generateQrCodeImage(String content, int width, int height) throws Exception {
		var qrCodeWriter = new QRCodeWriter();
		Map<EncodeHintType, Object> hintsMap = new HashMap<>();
		hintsMap.put(EncodeHintType.MARGIN, 0);
		hintsMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix;

		try {
			bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hintsMap);
		} catch (WriterException e) {
			throw new IOException("Failed to generate QR code image.", e);
		}

		var outputStream = new ByteArrayOutputStream();
		var qrImage = toBufferedImage(bitMatrix);
		try {
			ImageIO.write(qrImage, "png", outputStream);
		} catch (IOException e) {
			throw new IOException("Failed to write QR code image to output stream.", e);
		}

		return outputStream.toByteArray();
	}

	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		var graphics = (Graphics2D)image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (matrix.get(x, y)) {
					graphics.fillRect(x, y, 1, 1);
				}
			}
		}
		return image;
	}

}
