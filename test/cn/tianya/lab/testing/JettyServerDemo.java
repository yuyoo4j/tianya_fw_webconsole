package cn.tianya.lab.testing;

import cn.tianya.fw.properties.SystemPropertiesLocator;
import cn.tianya.fw.webconsole.util.JettyEmbededServer;

public class JettyServerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SystemPropertiesLocator.init();
		JettyEmbededServer.main(args);
	}

}
