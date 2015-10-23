package cn.orignzmn.shopkepper.stock.model.vo;

import java.util.Collection;
import java.util.List;

import cn.orignzmn.shopkepper.stock.model.Resource;


/**
 * 封装菜单在页面显示。
 * @author 张明楠。
 */
public class MenusVO {

	private String groupName;
	private Collection<Resource> menus;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Collection<Resource> getMenus() {
		return menus;
	}
	public void setMenus(List<Resource> menus) {
		this.menus = menus;
	}
	public MenusVO(String groupName, Collection<Resource> collection) {
		super();
		this.groupName = groupName;
		this.menus = collection;
	}
	@Override
	public String toString() {
		return "MenusVO [groupName=" + groupName + ", menus=" + menus + "]";
	}
}
