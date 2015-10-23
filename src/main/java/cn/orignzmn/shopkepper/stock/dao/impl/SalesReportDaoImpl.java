package cn.orignzmn.shopkepper.stock.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.stock.dao.SalesReportDao;
import cn.orignzmn.shopkepper.stock.model.vo.DateVO;
import cn.orignzmn.shopkepper.stock.model.vo.ReportVO;

@Repository
public class SalesReportDaoImpl implements SalesReportDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String allYearItemSql = "SELECT YEAR(date) d from stock_trading_record GROUP BY d ORDER BY YEAR(date) DESC";
	private static final String findMonthItemByYearSql = "SELECT DISTINCT MONTH(date)  from stock_trading_record where YEAR(date) = ? ORDER BY MONTH(date) DESC";
	private static final String getMonthReportDataSql = "SELECT  DATE(date) as date,sum(sale) as totalSale,sum(sales_volume) as count , sum(profit) as profit FROM stock_trading_record WHERE YEAR(date) = ? AND MONTH(date) = ?  GROUP BY DAY(date)";
	private static final String getDayReportDataSql = "SELECT  DATE(date) as date,sum(sale) as totalSale,sum(sales_volume) as count,sum(profit) as profit  FROM stock_trading_record WHERE YEAR(date) = ? AND MONTH(date) = ? AND DAY(date) = ?";
	private static final String getYearReportDataSql = "SELECT CONCAT(YEAR(date),'-',MONTH(date)) as date,sum(sale) as totalSale,sum(sales_volume) as count ,sum(profit) as profit FROM stock_trading_record WHERE YEAR(date) = ?  GROUP BY MONTH(date)";
	@Override
	public List<String> allYearItem() {
		return jdbcTemplate.queryForList(allYearItemSql, String.class);
	}
	@Override
	public List<String> findMonthItemByYear(String year) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForList(findMonthItemByYearSql,String.class,year);
	}
	@Override
	public List<ReportVO> getMonthReportData(DateVO dv) {
		// TODO Auto-generated method stub		
		return jdbcTemplate.query(getMonthReportDataSql, new BeanPropertyRowMapper<>(ReportVO.class),dv.getYear(),dv.getMonth());
	}
	@Override
	public List<ReportVO> getYearReportData(DateVO dv) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(getYearReportDataSql, new BeanPropertyRowMapper<>(ReportVO.class),dv.getYear());
	}
	@Override
	public List<ReportVO> getDayReportData(DateVO dv) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(getDayReportDataSql, new BeanPropertyRowMapper<>(ReportVO.class),dv.getYear(),dv.getMonth(),dv.getDay());
	}
}
