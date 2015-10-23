package cn.orignzmn.shopkepper.stock.service;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.Resource;

public interface ResourceService {

	List<Resource> findMenus();
	public Resource findOne(Long resourceId) ;
}
