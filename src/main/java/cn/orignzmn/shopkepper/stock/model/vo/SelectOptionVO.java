package cn.orignzmn.shopkepper.stock.model.vo;

public class SelectOptionVO {

	private String value;
	private String text;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public SelectOptionVO(Object value, String text) {
		super();
		this.value = String.valueOf(value);
		this.text = text;
	}
	@Override
	public String toString() {
		return "SelectOptionVO [value=" + value + ", text=" + text + "]";
	}
	
}
