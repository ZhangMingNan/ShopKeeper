package cn.orignzmn.shopkepper.stock.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.stock.dao.ProductPaymentDao;
import cn.orignzmn.shopkepper.stock.model.StockProductPayment;

@Repository
public class ProductPaymentDaoImpl implements ProductPaymentDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<StockProductPayment> getData(StockProductPayment payment) {
		// TODO Auto-generated method stub
		String sql = "select id,payment from stock_product_payment";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StockProductPayment.class));
	}
}
