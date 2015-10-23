package cn.orignzmn.shopkepper.api.service;

import java.util.List;

import cn.orignzmn.shopkepper.api.model.Brand;

public interface BrandService {

	public abstract void save(Brand brand);

	public abstract void deleteById(Long id);

	public abstract List<Brand> queryAll();

}