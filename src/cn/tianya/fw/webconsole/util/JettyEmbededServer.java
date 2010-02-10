package cn.tianya.fw.webconsole.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jetty.deploy.ContextDeployer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.start.Classpath;
import org.eclipse.jetty.start.FilenameComparator;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JettyEmbededServer {

	private static final Logger log = LoggerFactory.getLogger(JettyEmbededServer.class);

	private static Server server = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		startServer();
	}

	public static void startServer() {
		
		try {

			server = new Server();

			String jettyHome = "./jetty";
			System.setProperty("jetty.home", jettyHome);
  
			Classpath cp = new Classpath();
			addJars(cp, new File(jettyHome + "/lib"), true);
			Thread.currentThread().setContextClassLoader(cp.getClassLoader());

			XmlConfiguration configuration = new XmlConfiguration(JettyEmbededServer.class.getResource("/baseConfig/jetty.xml"));
			configuration.configure(server);
			WebAppContext webapp = new WebAppContext();
	        webapp.setContextPath("/webconsole");
	        webapp.setResourceBase("./WebContent");
	        server.setHandler(webapp);
			server.start();
			List<ContextDeployer> cd = server.getBeans(ContextDeployer.class);
			for (ContextDeployer item : cd) {
				ContextHandlerCollection chc = item.getContexts();
				Handler[] hs = chc.getHandlers();
				for (Handler handler : hs) {
					if (handler instanceof ContextHandler) {
						ContextHandler ch = (ContextHandler) handler;
						log.debug("ContextHandler:{}", ch); 
						log.debug("ContextHandler contextpath:{}", ch.getContextPath());   
					}
				}
			} 
			
		} catch (Exception ex) {
			log.error("start fail.", ex);
		}
	}
	
	public void stopServer() {
 
		try { 
			server.stop(); 
		} catch (Exception ex) { 
			log.error("stop fail.", ex);
		}
		server = null;
	}

	private static void addJars(Classpath cp, File dir, boolean recurse) throws IOException {
		
		List<File> entries = new ArrayList<File>();
		File[] files = dir.listFiles();
		if (files == null) {
			// No files found, skip it.
			return;
		}
		entries.addAll(Arrays.asList(files));
		Collections.sort(entries, FilenameComparator.INSTANCE);

		for (File entry : entries) {
			if (entry.isDirectory()) {
				if (recurse)
					addJars(cp, entry, recurse);
			} else {
				String name = entry.getName().toLowerCase();
				if (name.endsWith(".jar") || name.endsWith(".zip")) {
					cp.addComponent(entry);
				}
			}
		} 
	}
}
