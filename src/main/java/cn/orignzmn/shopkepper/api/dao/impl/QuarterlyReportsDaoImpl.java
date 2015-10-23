package cn.orignzmn.shopkepper.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.api.dao.QuarterlyReportsDao;
import cn.orignzmn.shopkepper.api.model.QuarterlyReport;
@Repository
public class QuarterlyReportsDaoImpl implements QuarterlyReportsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String getMonthReportDataSql = "SELECT  DATE(date) as date,sum(sale) as totalSale,sum(sales_volume) as count FROM stock_trading_record WHERE YEAR(date) = ? AND MONTH(date) = ?  GROUP BY DAY(date)";
	private static final String getDayReportDataSql = "SELECT  DATE(date) as date,sum(sale) as totalSale,sum(sales_volume) as count FROM stock_trading_record WHERE YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?";
	private static final String getYearReportDataSql = "SELECT YEAR(date) as date,sum(sale) as totalSale,sum(sales_volume) as count FROM stock_trading_record  GROUP BY YEAR(date)";

	@Override
	public List<QuarterlyReport> getData(int flag) {
		// TODO Auto-generated method stub
		
		return null;
	}
}
