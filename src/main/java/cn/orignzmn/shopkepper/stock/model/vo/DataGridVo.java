package cn.orignzmn.shopkepper.stock.model.vo;

import java.util.List;
import java.util.Map;

public class DataGridVo<T> {

	private Integer total;
	private List<T> rows;
	private List<Map<String,String>> footer;
	
	public List<Map<String, String>> getFooter() {
		return footer;
	}
	public void setFooter(List<Map<String, String>> footer) {
		this.footer = footer;
	}
	public DataGridVo() {
		super();
	}
	public DataGridVo(Integer total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
