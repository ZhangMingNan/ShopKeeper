package cn.orignzmn.shopkepper.stock.model.vo;

public class NewSellingPriceVO {

	private String productNo;
	private String sale;
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	@Override
	public String toString() {
		return "NewSellingPriceVO [productNo=" + productNo + ", sale=" + sale
				+ "]";
	}
}
