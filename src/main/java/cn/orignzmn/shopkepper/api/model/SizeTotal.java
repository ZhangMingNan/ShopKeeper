package cn.orignzmn.shopkepper.api.model;

public class SizeTotal {

	private String sizeLabel;
	private Integer number;
	public String getSizeLabel() {
		return sizeLabel;
	}
	public void setSizeLabel(String sizeLabel) {
		this.sizeLabel = sizeLabel;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public SizeTotal(String sizeLabel, Integer number) {
		super();
		this.sizeLabel = sizeLabel;
		this.number = number;
	}
	public SizeTotal() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SizeTotal [sizeLabel=" + sizeLabel + ", number=" + number + "]";
	}
	
}
