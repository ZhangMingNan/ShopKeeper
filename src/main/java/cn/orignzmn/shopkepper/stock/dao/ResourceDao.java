package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.Resource;

public interface ResourceDao {

	List<Resource> findMenus();
	public Resource findOne(Long resourceId) ;

}
