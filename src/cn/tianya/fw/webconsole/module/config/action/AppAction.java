package cn.tianya.fw.webconsole.module.config.action;

import cn.tianya.fw.webconsole.module.config.service.AppService;

public final class AppAction {
		
	/**
	 * 应用服务
	 */
	private AppService service = new AppService();

	/**
	 * 获取应用ID列表Grid数据响应
	 */
	public void queryAppList() {
		
		service.handleListQuery();
	}
	
	/**
	 * 获取父应用下拉列表html输出
	 */
	public void queryParentAppSelect() {
		
		service.handleSelectHtmlQuery();
	}
	
	/**
	 * 编辑应用
	 */
	public void editAppItem() {
		
		service.handleEditApp();
	}
	
	/**
	 * 新增应用
	 */
	public void addAppItem() {
		
		service.handleAddApp();
	}
	
	/**
	 * 删除应用
	 */
	public void deleteAppItem() {
		
		service.handleDeleteApp();
	}
}
