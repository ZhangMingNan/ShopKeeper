package cn.orignzmn.shopkepper.stock.model;

public class StockInDetail {

	private Long id;
	private String sizeList;
	private String no;
	private Integer total;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSizeList() {
		return sizeList;
	}
	public void setSizeList(String sizeList) {
		this.sizeList = sizeList;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "StockInDetail [id=" + id + ", sizeList=" + sizeList + ", no="
				+ no + ", total=" + total + "]";
	}
}
