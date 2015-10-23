package cn.orignzmn.shopkepper.stock.model.vo;


public class StockReprotVO  extends PagVo{
	
	private String shortNo;
	private Long type;
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getShortNo() {
		return shortNo;
	}
	public void setShortNo(String shortNo) {
		this.shortNo = shortNo;
	}
	@Override
	public String toString() {
		return "StockReprotVO [shortNo=" + shortNo + ", type=" + type + "]";
	}
}
