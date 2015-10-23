package cn.orignzmn.shopkepper.stock.model.chart;

import java.util.ArrayList;
import java.util.List;

public class StockPieChart {

	private String type = "pie";
	private String name ;
	private List<PieChartItem> data;
	
	public StockPieChart(String type, String name, List<PieChartItem> data) {
		super();
		this.type = type;
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<PieChartItem> getData() {
		return data;
	}
	public void setData(List<PieChartItem> pciList) {
		this.data = pciList;
	}
	public StockPieChart(String type, ArrayList<PieChartItem> data) {
		super();
		this.type = type;
		this.data = data;
	}
	public StockPieChart() {
		super();
		// TODO Auto-generated constructor stub
	}
}
