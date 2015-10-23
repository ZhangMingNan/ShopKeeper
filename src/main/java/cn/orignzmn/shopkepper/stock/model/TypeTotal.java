package cn.orignzmn.shopkepper.stock.model;

public class TypeTotal {
	private String type;
	private Integer total;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "TypeTotal [type=" + type + ", total=" + total + "]";
	}
	
}
