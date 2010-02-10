package cn.tianya.fw.webconsole.module.config.action;

import cn.tianya.fw.webconsole.module.config.service.BizEntityService;
import cn.tianya.fw.webconsole.module.config.service.ResourceService;

public final class BizEntityAction {
	
	private BizEntityService service = new BizEntityService();

	public void queryRoot() {
		
		service.handleRootQuery();
	}
	
	public void queryChildren() {
		
		service.handleChildrenQuery();
	}
}
