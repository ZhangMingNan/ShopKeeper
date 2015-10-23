package cn.orignzmn.shopkepper.stock.model.vo;

public class ReportVO {

	
	//时间
	private String date;
	//数量 
	private String count;
	//销售额
	private String totalSale;
	 //利润 
	private String profit;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(String totalSale) {
		this.totalSale = totalSale;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public ReportVO(String date, String count, String totalSale, String profit) {
		super();
		this.date = date;
		this.count = count;
		this.totalSale = totalSale;
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "ReportVO [date=" + date + ", count=" + count + ", totalSale="
				+ totalSale + ", profit=" + profit + "]";
	}
	public ReportVO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
