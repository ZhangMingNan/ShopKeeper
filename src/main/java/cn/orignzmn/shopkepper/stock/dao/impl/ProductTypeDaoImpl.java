package cn.orignzmn.shopkepper.stock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.stock.dao.ProductTypeDao;
import cn.orignzmn.shopkepper.stock.model.ProductType;

@Repository
public class ProductTypeDaoImpl implements ProductTypeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public void saveProductType(ProductType productType) {
		// TODO Auto-generated method stub
		String saveProductTypeSql = "insert into stock_product_type(type)values(?)";		
		jdbcTemplate.update(saveProductTypeSql,productType.getType());
	}

}
