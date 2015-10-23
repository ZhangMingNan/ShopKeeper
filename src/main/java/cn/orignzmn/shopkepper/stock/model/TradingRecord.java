package cn.orignzmn.shopkepper.stock.model;

import java.util.Date;

/**
 * 交易记录,记录每笔销售记录。
 * @author 张明楠
 */
public class TradingRecord {
	private Long id;
	private String memberBarCode = String.valueOf(0);//没有会员码时
	private Date date;
	private Integer salesVolume;
	private Float sale;
	private StockProduct stockProduct;
	private Employee employee;
	private StockProductPayment productPayment;
	
	public TradingRecord() {
		super();
		// TODO Auto-generated constructor stub
		employee = new Employee();
		stockProduct = new StockProduct();
		productPayment= new StockProductPayment();
	}
	public String getMemberBarCode() {
		return memberBarCode;
	}
	public void setMemberBarCode(String memberBarCode) {
		this.memberBarCode = memberBarCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public StockProduct getStockProduct() {
		return stockProduct;
	}
	public void setStockProduct(StockProduct stockProduct) {
		this.stockProduct = stockProduct;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public StockProductPayment getProductPayment() {
		return productPayment;
	}
	public void setProductPayment(StockProductPayment productPayment) {
		this.productPayment = productPayment;
	}
	@Override
	public String toString() {
		return "TradingRecord [id=" + id + ", date=" + date + ", salesVolume="
				+ salesVolume + ", sale=" + sale + ", stockProduct="
				+ stockProduct + ", employee=" + employee + ", productPayment="
				+ productPayment + "]";
	}
}
