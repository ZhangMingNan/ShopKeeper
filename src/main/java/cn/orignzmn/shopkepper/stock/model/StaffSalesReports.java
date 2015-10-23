package cn.orignzmn.shopkepper.stock.model;

/**
 * 员工销售报表实体
 * @author lenovo
 */
public class StaffSalesReports {

	private String ename;
	private String total;
	private String aggregateAmount;
	
	@Override
	public String toString() {
		return "StaffSalesReports [ename=" + ename + ", total=" + total
				+ ", aggregateAmount=" + aggregateAmount + "]";
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAggregateAmount() {
		return aggregateAmount;
	}
	public void setAggregateAmount(String aggregateAmount) {
		this.aggregateAmount = aggregateAmount;
	}
}
