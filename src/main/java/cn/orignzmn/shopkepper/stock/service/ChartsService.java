package cn.orignzmn.shopkepper.stock.service;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.StaffSalesReports;
import cn.orignzmn.shopkepper.stock.model.StaffSalesReportsQuery;

public interface ChartsService {
	public List<StaffSalesReports> allStaffSalesReports(StaffSalesReportsQuery query);
}
