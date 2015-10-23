package cn.orignzmn.shopkepper.stock.model.vo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import cn.orignzmn.shopkepper.common.Constants;

public class StockProductDgVO  extends PagVo{

	private Long typeId;
	private String productNo;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	//排序字段,如果是多字段排序
	private String sort;
	private String order  = Constants.ORDER_DESC;
	
	public Boolean isNotNull(){
		return this.getDate()!=null||this.getTypeId()!=null||StringUtils.isNotBlank(this.getProductNo())||StringUtils.isNotBlank(this.getSort());
	}
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "StockProductDgVO [typeId=" + typeId + ", productNo="
				+ productNo + ", date=" + date + ", sort=" + sort + ", order="
				+ order + ", toString()=" + super.toString() + "]";
	}
}
