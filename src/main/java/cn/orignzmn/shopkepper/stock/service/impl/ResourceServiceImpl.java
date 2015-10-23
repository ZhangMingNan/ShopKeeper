package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.ResourceDao;
import cn.orignzmn.shopkepper.stock.model.Resource;
import cn.orignzmn.shopkepper.stock.service.ResourceService;
@Service
public class ResourceServiceImpl  implements ResourceService{
	@Autowired
	ResourceDao resourceDao;

	@Override
	public List<Resource> findMenus() {
		// TODO Auto-generated method stub
		return resourceDao.findMenus();
	}

	public Resource findOne(Long resourceId) {
		return resourceDao.findOne(resourceId);
	}
}
