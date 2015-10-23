package cn.orignzmn.shopkepper.api.model;

public class QuarterlyReport {

	private String time;
	private Integer number;
	private Double sales;
	private Double profit;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	@Override
	public String toString() {
		return "QuarterlyReport [time=" + time + ", number=" + number
				+ ", sales=" + sales + ", profit=" + profit + "]";
	}
	public QuarterlyReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuarterlyReport(String time, Integer number, Double sales,
			Double profit) {
		super();
		this.time = time;
		this.number = number;
		this.sales = sales;
		this.profit = profit;
	}
}
