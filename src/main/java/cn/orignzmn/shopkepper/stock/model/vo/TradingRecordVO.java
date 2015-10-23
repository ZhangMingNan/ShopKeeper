package cn.orignzmn.shopkepper.stock.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.orignzmn.shopkepper.stock.model.TradingRecord;

import com.alibaba.fastjson.annotation.JSONField;

public class TradingRecordVO extends PagVo {
	private Long id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date = new Date();
	private Integer salesVolume;
	private Float sale;
	private Float price;//销售价格
	private Long stockProductId;
	private Long employeeId;
	private String employeeName;
	private Long paymentId;
	private String paymentStr;
	private String productInfo;
	private String size;
	private String type;
	//1 未发送通知,0已经发送通知
	private Integer post;
	public TradingRecordVO(TradingRecord tr) {
		super();
		// TODO Auto-generated constructor stub
		this.id = tr.getId();
		this.date = tr.getDate();
		this.salesVolume = tr.getSalesVolume();
		this.sale = tr.getSale();
		this.stockProductId = tr.getStockProduct().getId();
		this.employeeId = tr.getEmployee().getId();
		this.employeeName = tr.getEmployee().getName();
		this.paymentId = tr.getProductPayment().getId();
		this.paymentStr = tr.getProductPayment().getPayment();
		this.productInfo = tr.getStockProduct().toString();
		this.price = tr.getStockProduct().getProductNo().getPrice();
		this.size = tr.getStockProduct().getProductSize().getSize();
		this.type = tr.getStockProduct().getProductType().getType();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public TradingRecordVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProductInfo() {
		return productInfo;
	}
	public Integer getPost() {
		return post;
	}
	public void setPost(Integer post) {
		this.post = post;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	//@JsonSerialize(using = CustomDateSerializer.class)
	@JSONField (format="MM-dd HH:mm:ss")  
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public Float getSale() {
		return sale;
	}
	public void setSale(Float sale) {
		this.sale = sale;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Long getStockProductId() {
		return stockProductId;
	}
	public void setStockProductId(Long stockProductId) {
		this.stockProductId = stockProductId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentStr() {
		return paymentStr;
	}
	public void setPaymentStr(String paymentStr) {
		this.paymentStr = paymentStr;
	}
	@Override
	public String toString() {
		return "TradingRecordVO [id=" + id + ", date=" + date
				+ ", salesVolume=" + salesVolume + ", sale=" + sale
				+ ", price=" + price + ", stockProductId=" + stockProductId
				+ ", employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", paymentId=" + paymentId + ", paymentStr="
				+ paymentStr + ", productInfo=" + productInfo + ", size="
				+ size + ", type=" + type + ", post=" + post + "]";
	}
}
