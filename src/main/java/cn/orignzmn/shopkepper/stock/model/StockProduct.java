package cn.orignzmn.shopkepper.stock.model;

import java.io.Serializable;
import java.util.Date;

import cn.orignzmn.shopkepper.stock.model.vo.ProductNoVO;

public class StockProduct implements Serializable{
	private static final long serialVersionUID = -6750511582102016383L;
	//id
	private Long id;
	//商品条码。
	private String barCode;
	//种类
	private ProductType productType = new ProductType();
	//货号
	private ProductNoVO productNo = new ProductNoVO();
	//颜色
	private String color;
	//尺码
	private ProductSize productSize = new ProductSize();
	//进货价格
	private Float price;
	//设定的销售价格。
	private Float sellingPrice;
	//入库时间
	private Date date;
	
	public Float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public ProductNoVO getProductNo() {
		return productNo;
	}
	public void setProductNo(ProductNoVO productNo) {
		this.productNo = productNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public ProductSize getProductSize() {
		return productSize;
	}
	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}
	

	public StockProduct(Long id, String barCode, ProductType productType,
			ProductNoVO productNo, String color, ProductSize productSize,
			Float price, Float sellingPrice, Date date) {
		super();
		this.id = id;
		this.barCode = barCode;
		this.productType = productType;
		this.productNo = productNo;
		this.color = color;
		this.productSize = productSize;
		this.price = price;
		this.sellingPrice = sellingPrice;
		this.date = date;
	}
	public StockProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	@Override
	public String toString() {
		return this.productNo.getNo();
	}
	
}
