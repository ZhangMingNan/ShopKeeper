package cn.orignzmn.shopkepper.stock.model;

//销售网点。
public class Shop {
	private Long id;
	//网点名称。
	private String name;
	//网点地址。
	private String address;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", address=" + address
				+ "]";
	}
}
