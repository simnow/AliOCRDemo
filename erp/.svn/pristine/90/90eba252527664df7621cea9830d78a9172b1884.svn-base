/**
 * 
 */
package cn.caecc.erp.support.captcha.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author weizhne
 *
 */
public interface CaptchaService {

	public void getCaptcha(HttpServletRequest request, HttpServletResponse response, int width,
			int height) throws IOException;

	public boolean checkCaptcha(String code);

}
