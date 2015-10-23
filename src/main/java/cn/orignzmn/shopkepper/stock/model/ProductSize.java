package cn.orignzmn.shopkepper.stock.model;

/**
 * 
 * @author 尺码类。
 */
public class ProductSize {
	private Long id;
	private String size;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public ProductSize(Long id, String size) {
		super();
		this.id = id;
		this.size = size;
	}
	public ProductSize() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	public String toString() {
		return "ProductSize [id=" + id + ", size=" + size + "]";
	}
}
