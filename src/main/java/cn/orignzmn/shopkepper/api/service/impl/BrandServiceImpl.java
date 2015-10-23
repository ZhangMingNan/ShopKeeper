package cn.orignzmn.shopkepper.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.api.dao.BrandDao;
import cn.orignzmn.shopkepper.api.model.Brand;
import cn.orignzmn.shopkepper.api.service.BrandService;
@Service
public class BrandServiceImpl implements BrandService {

	
	@Autowired
	BrandDao brandDao;
	
	/* (non-Javadoc)
	 * @see cn.orignzmn.shopkepper.api.service.impl.BrandService#save(cn.orignzmn.shopkepper.api.model.Brand)
	 */
	@Override
	public  void save(Brand brand){
		brandDao.save(brand);
	}

	/* (non-Javadoc)
	 * @see cn.orignzmn.shopkepper.api.service.impl.BrandService#deleteById(java.lang.Long)
	 */
	@Override
	public  void deleteById(Long id){
		brandDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see cn.orignzmn.shopkepper.api.service.impl.BrandService#queryAll()
	 */
	@Override
	public  List<Brand> queryAll(){
		return brandDao.queryAll();
	}
}
