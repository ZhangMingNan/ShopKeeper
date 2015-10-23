package cn.orignzmn.shopkepper.stock.model.chart;

public class PieChartItem {
	private String name;
	private double  y;
	private boolean sliced;
	private boolean selected;
	
	
	public PieChartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PieChartItem(String name, double y, boolean sliced, boolean selected) {
		super();
		this.name = name;
		this.y = y;
		this.sliced = sliced;
		this.selected = selected;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public boolean isSliced() {
		return sliced;
	}
	public void setSliced(boolean sliced) {
		this.sliced = sliced;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
