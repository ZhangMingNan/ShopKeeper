package cn.orignzmn.shopkepper.stock.model;

public class StockProductPayment {

	private Long id;
	private String payment;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "StockProductPayment [id=" + id + ", payment=" + payment + "]";
	}
}
