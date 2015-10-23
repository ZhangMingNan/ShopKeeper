package cn.orignzmn.shopkepper.stock.model;

public class StockMember {
	private Long id;
	private String phone;
	private Integer score;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Member [phone=" + phone + ", score=" + score + "]";
	}
}
