package cn.tianya.fw.webconsole.mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.tianya.fw.webconsole.sys.BeanFactory;

/**
 * http请求方法工厂
 * @author yuyoo (yuyoo4j@163.com)
 * @date 2010-1-6 上午09:37:39
 */
public final class RequestMethodFactory {
	
	/**
	 * 方法缓存
	 */
	private static Map<String, MethodMeta> methods = new HashMap<String, MethodMeta>(512, 0.9F);
	
	/**
	 * 获取方法元数据
	 * 备注: 优先通过缓存获取,如果没有就查找
	 * @param action -- 方法行为标示符(beanId + '.' + methodName)
	 * @return 方法对象
	 */
	public static MethodMeta getMethodMeta(String action) {
		
		MethodMeta mm = methods.get(action);
		if (null == mm) {
		
			int index = action.lastIndexOf(".");
			if (index < 0) {
				throw new IllegalArgumentException("方法行为标示符不正确");
			}
	
			String beanId = action.substring(0, index);
			String beanMethod = action.substring(index + 1);
			mm = lookupMethodMeta(beanId, beanMethod);
			if (null != mm) {
				methods.put(action, mm);
			} 
		}		
		return mm;
	}

	/**
	 * 查找方法元数据
	 * @param beanId -- 对象(bean)ID
	 * @param method -- 对象方法
	 * @return -- 方法对象
	 */
	@SuppressWarnings("unchecked")
	public static MethodMeta lookupMethodMeta(String beanId, String method) {
		
		Object bean = BeanFactory.getBean(beanId);
		Method m = null;
		if (null != bean) {			
			Class[] pt = new Class[]{ };
			try {
				m = bean.getClass().getMethod(method, pt); 
			} catch (Exception ex) {
				throw new RuntimeException("查找java对象(bean)的方法异常", ex);
			}
		}

		MethodMeta mm = new MethodMeta();
		mm.bean = bean;
		mm.method = m;
		return mm;
	}
	
	/**
	 * 调用方法
	 * @param action -- 方法行为
	 * @param args -- 方法参数列表
	 * @return -- 方法返回对象
	 */
	public static Object invokeMethod(String action, Object[] args) {
		
		MethodMeta mm = getMethodMeta(action);
		if (null != mm) {
			return invokeMethod(mm, args);
		}
		return null;
	}
	
	/**
	 * 调用方法
	 * @param mm -- 方法元数据
	 * @param args -- 方法参数列表
	 * @return -- 方法返回对象
	 */
	public static Object invokeMethod(MethodMeta mm, Object[] args) {
		
		if (null != mm.bean && null != mm.method) {
			try {
				return mm.method.invoke(mm.bean, args);
			} catch (Exception ex) {
				throw new RuntimeException("方法行为调用异常", ex);
			}
		}
		return null;
	}
	
	/**
	 * 方法元数据
	 * @author yuyoo (yuyoo4j@163.com)
	 * @date 2010-1-6 上午09:30:15
	 */
	public static class MethodMeta {
		
		private Object bean = null;
		private Method method = null;
	}
}
