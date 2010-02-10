package cn.tianya.fw.webconsole.module.config.action;

import cn.tianya.fw.webconsole.module.config.service.ResourceService;

public final class ResourceAction {

	private ResourceService service = new ResourceService();
	
	public void queryRoot() {
		
		service.handleRootQuery();
	}
	
	public void queryChildren() {
		
		service.handleChildrenQuery();
	}
}
