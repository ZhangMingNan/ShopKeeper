package cn.orignzmn.shopkepper.stock.model.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import cn.orignzmn.shopkepper.stock.model.StockProduct;

public class StockProductVO {
	//id
	private Long id;
	private Long productTypeId;
	private Long productSizeId;
	private String barCode;
	//种类
	private String type;
	private String productNoId;
	//货号
	private String productNo;
	//颜色
	private String color;
	//尺码
	private String size;
	//进货价格
	private Float price;
	//设定的销售价格。
	private Float sellingPrice;
	//入库时间
 
	private Date date;
	
	//已经入库的天数。
	@SuppressWarnings("unused")
	private String  inventoryDay; 

	public String getInventoryDay() throws ParseException {
		return String.valueOf(daysBetween( date,new Date()));
	}
	public void setInventoryDay(String inventoryDay) {
		this.inventoryDay = inventoryDay;
	}
	public StockProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }  
	public StockProductVO(StockProduct pro) {
		super();
		this.id = pro.getId();
		this.productSizeId = pro.getProductSize().getId();
		this.productTypeId = pro.getProductType().getId();
		this.barCode = pro.getBarCode();
		this.type = pro.getProductType().getType();
		this.productNoId = pro.getProductNo().getId()+"";
		this.productNo = pro.getProductNo().getNo();
		this.color = pro.getColor();
		this.size = pro.getProductSize().getSize();
		this.price = pro.getPrice();
		this.sellingPrice = pro.getSellingPrice();
		this.date = pro.getDate();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProductNoId() {
		return productNoId;
	}
	public void setProductNoId(String productNoId) {
		this.productNoId = productNoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getColor() {
		return color;
	}
	
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public Long getProductSizeId() {
		return productSizeId;
	}
	public void setProductSizeId(Long productSizeId) {
		this.productSizeId = productSizeId;
	}
	@Override
	public String toString() {
		return "StockProductVO [id=" + id + ", productTypeId=" + productTypeId
				+ ", productSizeId=" + productSizeId + ", barCode=" + barCode
				+ ", type=" + type + ", productNoId=" + productNoId
				+ ", productNo=" + productNo + ", color=" + color + ", size="
				+ size + ", price=" + price + ", sellingPrice=" + sellingPrice
				+ ", date=" + date + "]";
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	@JSONField (format="yyyy-MM-dd") 
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
