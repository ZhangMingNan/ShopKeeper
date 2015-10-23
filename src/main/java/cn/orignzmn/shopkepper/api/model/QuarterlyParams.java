package cn.orignzmn.shopkepper.api.model;

public class QuarterlyParams {

	private String type;
	private String time;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public QuarterlyParams(String type, String time) {
		super();
		this.type = type;
		this.time = time;
	}
	public QuarterlyParams() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "QuarterlyParams [type=" + type + ", time=" + time + "]";
	}
	
	
}
