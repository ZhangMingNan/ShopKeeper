package cn.orignzmn.shopkepper.api.model;

public class EmployeeDetail {
	private Integer id;
	private Integer monthCount;
	private Integer totalCount;
	private Double monthProfit;
	private Double monthSale;
	public Double getMonthProfit() {
		return monthProfit;
	}
	public void setMonthProfit(Double monthProfit) {
		this.monthProfit = monthProfit;
	}
	public Double getMonthSale() {
		return monthSale;
	}
	public void setMonthSale(Double monthSale) {
		this.monthSale = monthSale;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(Integer monthCount) {
		this.monthCount = monthCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "EmployeeDetail [id=" + id + ", monthCount=" + monthCount
				+ ", totalCount=" + totalCount + ", monthProfit=" + monthProfit
				+ ", monthSale=" + monthSale + "]";
	}
}
