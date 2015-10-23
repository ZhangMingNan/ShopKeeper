package cn.orignzmn.shopkepper.stock.model.vo;

import java.io.Serializable;

public class DateVO implements Serializable {
	private static final long serialVersionUID = -7588801521190410518L;
	private String year;
	private String month;
	private String day;
	
	public DateVO(String year) {
		super();
		this.year = year;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "DateVO [year=" + year + ", month=" + month + ", day=" + day
				+ "]";
	}
	public DateVO(String year, String month) {
		super();
		this.year = year;
		this.month = month;
	}
	public DateVO() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
