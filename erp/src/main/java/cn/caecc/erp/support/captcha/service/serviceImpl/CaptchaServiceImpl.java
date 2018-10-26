/**
 * 
 */
package cn.caecc.erp.support.captcha.service.serviceImpl;

import java.awt.Color;
import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import cn.caecc.erp.support.captcha.service.CaptchaService;
import cn.caecc.erp.support.captcha.service.entity.CaptchaEntity;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;

/**
 * @author weizhen
 *
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

	public static final String CAPTCHA_CODE = "CAPTCHA_CODE";

	@Autowired
	private HttpSession session;
	/**
	 * 
	 */
	public CaptchaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.captcha.service.CaptchaService#generalCaptcha()
	 */
	@Override
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response, int width,
			int height) throws IOException {
     //   System.out.println(session.getId());
        
		// TODO Auto-generated method stub
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        RandomFontFactory ff = new RandomFontFactory();
        ff.setMinSize(30);
        ff.setMaxSize(30);
        RandomWordFactory rwf = new RandomWordFactory();
        rwf.setMinLength(4);
        rwf.setMaxLength(4);
        cs.setWordFactory(rwf);
        cs.setFontFactory(ff);
        cs.setHeight(height);
        cs.setWidth(width);
        
        
        long currentTime = DateUtil.getcurrentDateTime().getTime();
        
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Last-Modified", currentTime);
        response.setDateHeader("Date", currentTime);
        response.setDateHeader("Expires", currentTime);
        response.setContentType("image/png");
        
        ServletOutputStream stream = response.getOutputStream();
        String captchaCode = EncoderHelper.getChallangeAndWriteImage(cs,
                "png", stream);        
        CaptchaEntity captchaEntity = (CaptchaEntity)session.getAttribute(CAPTCHA_CODE);
        if (captchaEntity == null) {
        	captchaEntity = new CaptchaEntity();
        }
        captchaEntity.setCode(captchaCode);
        captchaEntity.setTime(currentTime);
        session.setAttribute(CAPTCHA_CODE, captchaEntity);
         
     //   stream.flush(); //如果加上这句话 session每次都会是新的，千万不要加
        stream.close();
        return;

	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.captcha.service.CaptchaService#checkCaptcha()
	 */
	@Override
	public boolean checkCaptcha(String code) {
		// TODO Auto-generated method stub
		boolean ret = false;
    //    System.out.println(session.getId());

        CaptchaEntity captchaEntity = (CaptchaEntity)session.getAttribute(CAPTCHA_CODE);
        if (captchaEntity == null) {
        	throw new CommonException("请先获取验证码");
        } else {
        	String generalCode = captchaEntity.getCode();
        	if (!code.equals(generalCode)) {
            	throw new CommonException("验证码错误");
        	} else {
	        	long generalTime = captchaEntity.getTime();
				Long currentTime = DateUtil.getcurrentDateTime().getTime();
				Long diff = currentTime - generalTime;
				if (diff / (1000) >= 60) {
					throw new CommonException("验证码超时，请重新获取");
				} else {
					ret = true;
				}
        	}
        }
		return ret;
	}

}
