package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.StaffSalesReports;
import cn.orignzmn.shopkepper.stock.model.StaffSalesReportsQuery;

public interface ChartsDao {

	/**
	 * 获取指定一个时间的所有员工的销售情况
	 */
	public List<StaffSalesReports> allStaffSalesReports(StaffSalesReportsQuery query);
}
