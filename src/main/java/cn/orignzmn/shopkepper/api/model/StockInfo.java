package cn.orignzmn.shopkepper.api.model;

import java.util.List;

public class StockInfo {
	private String productIconUrl;
	private String productNo;
	private Long productNoId;
	private Integer stockNumber;
	private Double sellingPrice;
	private List<SizeTotal> sizeList;
	private String sizeDetail;
	
	
	
	public String getSizeDetail() {

		return sizeDetail;
	}
	public void setSizeDetail(String sizeDetail) {
		this.sizeDetail = sizeDetail;
	}
	public String getProductIconUrl() {
		return productIconUrl;
	}
	public void setProductIconUrl(String productIconUrl) {
		this.productIconUrl = productIconUrl;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	public Long getProductNoId() {
		return productNoId;
	}
	public void setProductNoId(Long productNoId) {
		this.productNoId = productNoId;
	}
	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}



	public Double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public List<SizeTotal> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<SizeTotal> sizeList) {
		StringBuilder sb = new StringBuilder();
		for(SizeTotal st:sizeList){
			sb.append(st.getSizeLabel()+"<sup>"+st.getNumber()+"</sup>&nbsp;&nbsp;");
		}
		this.sizeDetail = sb.toString();
		this.sizeList = sizeList;
	}
	public StockInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "StockInfo [productIconUrl=" + productIconUrl + ", productNo="
				+ productNo + ", productNoId=" + productNoId + ", stockNumber="
				+ stockNumber + ", sellingPrice=" + sellingPrice
				+ ", sizeList=" + sizeList + "]";
	}
}
