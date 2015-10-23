package cn.orignzmn.shopkepper.stock.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class StockIn {
	private Long id;
	@JSONField (format="yyyy-MM-dd") 
	private Date date;
	private Integer total;
	private Long typeId;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "StockIn [id=" + id + ", date=" + date + ", total=" + total
				+ ", typeId=" + typeId + ", type=" + type + "]";
	}
}
