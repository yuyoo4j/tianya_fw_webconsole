package cn.tianya.fw.webconsole.module.system.action;

import cn.tianya.fw.webconsole.module.system.service.SecurityService;

public final class SecurityAction {

	private SecurityService service = new SecurityService();
	
	public void queryUserList() {
		
		service.handleListQuery();
	}
	
	public void editUserItem() {
		
		service.handleEditUser();
	}
	
	public void addUserItem() {
		
		service.handleAddUser();
	}
	
	public void deleteUserItem() {
	
		service.handleDeleteUser();
	}
}
