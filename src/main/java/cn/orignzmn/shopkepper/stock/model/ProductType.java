package cn.orignzmn.shopkepper.stock.model;


/**
 * 商品款式。
 * @author 张明楠
 */

public class ProductType {


	private Long id;
	private String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ProductType(Long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public ProductType() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	public String toString() {
		return "ProductType [id=" + id + ", type=" + type + "]";
	}
}
