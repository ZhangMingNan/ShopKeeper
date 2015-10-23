package cn.orignzmn.shopkepper.stock.model;


public class Employee {

	private Long id;
	private String name;
	private String phone;
	private String avataImg;//头像图片
	public String getAvataImg() {
		return avataImg;
	}
	public void setAvataImg(String avataImg) {
		this.avataImg = avataImg;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phone=" + phone
				+ "]";
	}
}
