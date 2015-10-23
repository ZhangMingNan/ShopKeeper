package cn.orignzmn.shopkepper.stock.model;

public class PriceAdjustGroupVO {

	private Integer[] ids;
	private Float price;
	private Float sellingPrice;


	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	@Override
	public String toString() {
		return "PriceAdjustGroupVO [ids=" + ids + ", price=" + price
				+ ", sellingPrice=" + sellingPrice + "]";
	}
	
	
}
