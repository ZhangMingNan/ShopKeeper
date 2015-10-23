package cn.orignzmn.shopkepper.stock.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.stock.dao.ChartsDao;
import cn.orignzmn.shopkepper.stock.model.StaffSalesReports;
import cn.orignzmn.shopkepper.stock.model.StaffSalesReportsQuery;
@Repository
public class ChartsDaoImpl implements ChartsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String sql = "SELECT (SELECT se.name FROM stock_employee se where se.id = str.employee_id) as ename,COUNT(*) as total,"
			+ "SUM((SELECT (SELECT spn.selling_price from stock_product_no spn where spn.id = ssp.product_no) from stock_stock_product ssp "
			+ "where ssp.id = str.stock_product_id)) as aggregateAmount FROM stock_trading_record str  "
			+ " where DATE_FORMAT(str.date,?) = ?  GROUP BY ename ORDER BY aggregateAmount desc;";
	@Override
	public List<StaffSalesReports> allStaffSalesReports(StaffSalesReportsQuery query) {
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StaffSalesReports.class),query.getType().format(),query.getDate());
	}
}
