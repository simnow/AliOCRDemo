package cn.caecc.erp.support.qrcode.service.serviceImpl;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import cn.caecc.erp.support.qrcode.service.QrCoderService;
/**
 * @author weizhen
 *
 */
@Service
public class QrCoderServiceImpl implements QrCoderService {

	public static final int QRWIDTH = 300;

	public static final int QRHEIGHT = 300;

	public static final int BARWIDTH = 105;

	public static final int BARHEIGHT = 50;

	private final static Logger logger = LoggerFactory.getLogger(QrCoderServiceImpl.class);

	@Override
	public BufferedImage getQrCode(String contents) {
		// TODO Auto-generated method stub
		BufferedImage bufferedImage = null;

		try {
			Map<EncodeHintType, Object> hints = new Hashtable<>();
			// 指定纠错等级
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			// 指定编码格式
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, QRWIDTH, QRHEIGHT,
					hints);
			bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return bufferedImage;
	}

	@Override
	public BufferedImage getBarCode(String contents) {
		// TODO Auto-generated method stub
		BufferedImage bufferedImage = null;
		try {
			int codeWidth = 3 + // start guard
					(7 * 6) + // left bars
					5 + // middle guard
					(7 * 6) + // right bars
					3; // end guard
			codeWidth = Math.max(codeWidth, BARWIDTH);
			
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, codeWidth, BARHEIGHT,
					null);
			bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return bufferedImage;
	}
	
/*
	private ByteArrayInputStream toByteArrayInputStream(BitMatrix bitMatrix) {
		ByteArrayInputStream byteArrayInputStream = null;
		try {
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageOutputStream imageOutput = ImageIO.createImageOutputStream(byteArrayOutputStream);
			ImageIO.write(bufferedImage, "png", imageOutput);
			byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		} catch (Exception ex) {

		}
		return byteArrayInputStream;
	}
*/
}
