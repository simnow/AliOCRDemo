package cn.caecc.erp.support.qrcode.service;

import java.awt.image.BufferedImage;

/**
 * @author weizhen
 *
 */
public interface QrCoderService {
	
	public BufferedImage getQrCode(String contents);
	
	public BufferedImage getBarCode(String contents);


}
