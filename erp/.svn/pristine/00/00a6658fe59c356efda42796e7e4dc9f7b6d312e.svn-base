package cn.caecc.erp.support.aop.monitor;
import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;


/**
 * @author weizhen
 *
 */
//@Aspect
public class MethodMonitor {

	private final static Logger logger = LoggerFactory.getLogger(MethodMonitor.class);
	private static ThreadLocal<StopWatch> connThreadLocal = new ThreadLocal<StopWatch>(); 
	/**
	 * 
	 */
	public MethodMonitor() {
		// TODO Auto-generated constructor stub
	}
	
	@Pointcut("execution(* cn.caecc.erp.modules.service.serviceImpl.**.userLogin(..))")
    public void method() {
    }
	@Before("method()")
	public void startWatch() {
		StopWatch stopWatch = new StopWatch();
		connThreadLocal.set(stopWatch);
        stopWatch.start();

	}

	@After("method()")
    public void endWatch() {
		StopWatch stopWatch = connThreadLocal.get();
		stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }
}
