package cn.orignzmn.shopkepper.stock.model.vo;

import cn.orignzmn.shopkepper.common.WebUtils;


public class PriceAdjustVO extends PagVo  {
	private Integer id;
	private String productNo;
	private Float price;
	private Float sellingPrice;
	private String productIconUrl;
	private String shortNo;
	private Long type;
	private String typeValue;
	
	//历史销售量。
	private Integer salesVolume;

	
	
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getShortNo() {
		if(this.shortNo == null){
			this.shortNo =   WebUtils.shortNo(this.getProductNo());
		}
		return this.shortNo;
	}
	public void setShortNo(String shortNo) {
		this.shortNo = shortNo;
	}
	public Float getSellingPrice() {
		return sellingPrice;
	}
	public Integer getId() {
		return id;
	}
	public String getProductIconUrl() {
		return productIconUrl;
	}
	public void setProductIconUrl(String productIconUrl) {
		this.productIconUrl = productIconUrl;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PriceAdjustVO [id=" + id + ", productNo=" + productNo
				+ ", price=" + price + ", sellingPrice=" + sellingPrice
				+ ", productIconUrl=" + productIconUrl + ", shortNo=" + shortNo
				+ ", type=" + type + ", typeValue=" + typeValue
				+ ", salesVolume=" + salesVolume + "]";
	}


}
