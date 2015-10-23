package cn.orignzmn.shopkepper.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.api.dao.EmployeeDetailDao;
import cn.orignzmn.shopkepper.api.model.EmployeeDetail;

@Repository
public class EmployeeDetailDaoImpl implements EmployeeDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final static String getEmpDetailByIdSQL ="SELECT se.id,\n" +
			"(SELECT count(str.id) FROM stock_trading_record str where se.id = str.employee_id) totalCount,\n" +
			"(SELECT count(str.id) FROM stock_trading_record str where se.id = str.employee_id and MONTH(str.date) =  MONTH(NOW())) monthCount,\n" +
			"(SELECT sum(str.profit) FROM stock_trading_record str where se.id = str.employee_id and MONTH(str.date) =  MONTH(NOW())) monthProfit,\n" +
			"(SELECT sum(str.sale) FROM stock_trading_record str where se.id = str.employee_id and MONTH(str.date) =  MONTH(NOW())) monthSale\n" +
			"FROM stock_employee se where se.id = ?";
	@Override
	public EmployeeDetail getEmpDetailById(Integer id){	

		return jdbcTemplate.queryForObject(getEmpDetailByIdSQL,new  BeanPropertyRowMapper<>(EmployeeDetail.class),id);
	}
}
