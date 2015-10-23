package cn.orignzmn.shopkepper.stock.model;

public class MarkLineData {

	private String type;
	private String name;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MarkLineData(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	
}
