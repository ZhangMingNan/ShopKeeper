package cn.orignzmn.shopkepper.stock.model;

public class StockToken {
	private 	String token;
	private Integer id;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public StockToken(String token, Integer id) {
		super();
		this.token = token;
		this.id = id;
	}
	public StockToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "StockToken [token=" + token + ", id=" + id + "]";
	}
}
