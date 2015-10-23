package cn.orignzmn.shopkepper.api.dao;

import java.util.List;

import cn.orignzmn.shopkepper.api.model.Brand;

public interface BrandDao {

	public abstract void save(Brand brand);

	public abstract void deleteById(Long id);

	public abstract List<Brand> queryAll();

}