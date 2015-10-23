package cn.orignzmn.shopkepper.stock.model.vo;


/**
 * 入库,出库数据统计数据的模型
 * @author zhangmingnan
 */
public class StockProductTotalVO {
	//货号
	private String productNo;
	//尺码
	private String sizeDetail;
	private Integer total;
	//出库尺码列表
	private String stockOutSizeDetail;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	private String sale = "";
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getSizeDetail() {
		return sizeDetail;
	}
	public void setSizeDetail(String sizeDetail) {
		this.sizeDetail = sizeDetail;
	}
	public String getStockOutSizeDetail() {
		return stockOutSizeDetail;
	}
	public void setStockOutSizeDetail(String stockOutSizeDetail) {
		this.stockOutSizeDetail = stockOutSizeDetail;
	}
	@Override
	public String toString() {
		return "StockProductTotalVO [productNo=" + productNo + ", sizeDetail="
				+ sizeDetail + ", total=" + total + ", stockOutSizeDetail="
				+ stockOutSizeDetail + ", sale=" + sale + "]";
	}
}
