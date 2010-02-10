package cn.tianya.fw.webconsole.sys;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean 工厂
 * @author yuyoo (yuyoo4j@163.com)
 * @date 2010-1-5 上午10:00:00
 */
public final class BeanFactory {

	private static String springConfigLocation = "classpath*:/spring/**/*.xml";
	
	/**
	 * spring 应用上下文
	 */
	private static ApplicationContext ac = null;
	
	private static ApplicationContext currentApplicationContext() {
		
		if (null == ac) {
			ac = new ClassPathXmlApplicationContext(springConfigLocation); 
		}
		return ac;
	}
	
	/**
	 * 获取spring bean
	 * @param beanId -- bean ID
	 * @return -- spring bean
	 */
	public static Object getBean(String beanId) {
		
		return currentApplicationContext().getBean(beanId);
	}
	
	/**
	 * 获取spring bean
	 * @param <T>
	 * @param beanId  -- bean ID
	 * @param returnType -- 返回类型
	 * @return --  spring bean
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId, Class<T> returnType) {
		
		return (T) currentApplicationContext().getBean(beanId, returnType);
	}
	
	/**
	 * 是否存在bean
	 * @param beanId
	 * @return true, 如果存在bean配置
	 */
	public static boolean containBean(String beanId) {
		
		return currentApplicationContext().containsBean(beanId);
	}
}
