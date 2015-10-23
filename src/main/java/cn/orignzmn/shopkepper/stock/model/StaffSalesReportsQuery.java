package cn.orignzmn.shopkepper.stock.model;

import cn.orignzmn.shopkepper.common.QueryDateType;

public class StaffSalesReportsQuery {

	private StaffSalesReports staffSalesReports;
	
	private QueryDateType type;
	private String date;
	
	
	
	public StaffSalesReports getStaffSalesReports() {
		return staffSalesReports;
	}
	public void setStaffSalesReports(StaffSalesReports staffSalesReports) {
		this.staffSalesReports = staffSalesReports;
	}
	public QueryDateType getType() {
		return type;
	}
	public void setType(QueryDateType type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "StaffSalesReportsQuery [staffSalesReports=" + staffSalesReports
				+ ", type=" + type + ", data=" + date + "]";
	}
}
