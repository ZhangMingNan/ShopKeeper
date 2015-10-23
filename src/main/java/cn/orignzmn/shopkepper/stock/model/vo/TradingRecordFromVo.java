package cn.orignzmn.shopkepper.stock.model.vo;

import cn.orignzmn.shopkepper.stock.model.StockProduct;

public class TradingRecordFromVo {
	private Long payment;
	private Long employeeId;
	private StockProduct stockProduct;
	private String memberBarCode = String.valueOf(0);

	public String getMemberBarCode() {
		return memberBarCode;
	}
	public void setMemberBarCode(String memberBarCode) {
		if(memberBarCode!=null){
		this.memberBarCode = memberBarCode;
		}
	}
	public Long getPayment() {
		return payment;
	}
	public void setPayment(Long payment) {
		this.payment = payment;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public StockProduct getStockProduct() {
		return stockProduct;
	}
	public void setStockProduct(StockProduct stockProduct) {
		this.stockProduct = stockProduct;
	}
	@Override
	public String toString() {
		return "TradingRecordFromVo [payment=" + payment + ", employeeId="
				+ employeeId + ", stockProduct=" + stockProduct
				+ ", memberBarCode=" + memberBarCode + "]";
	}
}
