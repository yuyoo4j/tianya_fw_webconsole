package cn.tianya.fw.webconsole.data;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Map;

import cn.tianya.fw.webconsole.sys.BeanFactory;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public final class FreemarkerFactory {
 	
	private static Configuration cfg = new Configuration();

	static {
		
		try {
			
			ClassTemplateLoader ctl = new ClassTemplateLoader(FreemarkerFactory.class, "/freemarker");
			ClassTemplateLoader ctlBase = new ClassTemplateLoader(FreemarkerFactory.class, "/");
			
			File f = new File("freemarker");
			if (!f.exists()) {
				f = new File(".");
			}
			FileTemplateLoader ftl = new FileTemplateLoader(f); 
			FileTemplateLoader base = new FileTemplateLoader(new File(".")); 
			
			SpringMapBeanTemplateLoader smb = new SpringMapBeanTemplateLoader();
			
			TemplateLoader[] loaders = new TemplateLoader[] {  ctl, ftl, smb, ctlBase, base};			
			MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
	
			cfg.setTemplateLoader(mtl);			
	        cfg.setObjectWrapper(new DefaultObjectWrapper());

		} catch (Exception ex) {
			throw new RuntimeException("配置freemarker异常", ex);
		}
	}
	
	public static StringBuffer process(String templeteName, Object rootMap, String encoding) {
		
		try {
			Template temp = cfg.getTemplate(templeteName);
			StringWriter out = new StringWriter();
			temp.setEncoding(encoding);
			temp.process(rootMap, out);
			return out.getBuffer();
		} catch (Exception ex) { 
			throw new RuntimeException("处理freemarker模板异常", ex);
		}
	}
	
	public static StringBuffer process(String templeteName, Object rootMap) {
		
		return process(templeteName, rootMap, "utf-8");
	}
	
	/**
	 * 自定义spring map bean加载器
	 * @author yuyoo (yuyoo4j@163.com)
	 * @date 2010-1-12 下午04:14:05
	 */
	private static class SpringMapBeanTemplateLoader implements TemplateLoader {
		
		private String ftlsBeanName = "freemarker.ftl.collection";
		
		private StringTemplateLoader inner = new StringTemplateLoader();
		
		@SuppressWarnings("unchecked")
		SpringMapBeanTemplateLoader() {
			
			Map<String, String> sqls = (Map<String, String>) BeanFactory.getBean(ftlsBeanName);
			for (String key : sqls.keySet()) {
				inner.putTemplate(key, sqls.get(key));
			}
		}

		public void closeTemplateSource(Object templateSource) throws IOException {
			
			inner.closeTemplateSource(templateSource);
		}

		public Object findTemplateSource(String name) throws IOException {

			Object rtv = inner.findTemplateSource(name); 
			return rtv;
		}

		public long getLastModified(Object templateSource) {
			
			return inner.getLastModified(templateSource);
		}

		public Reader getReader(Object templateSource, String encoding) throws IOException {
			
			return inner.getReader(templateSource, encoding);
		}
		
	}
}
