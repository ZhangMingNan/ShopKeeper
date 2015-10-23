package cn.orignzmn.shopkepper.api.model;

public class Brand {

	private Integer id;
	// 品牌名称
	private String name;

	private String logoImag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoImag() {
		return logoImag;
	}

	public void setLogoImag(String logoImag) {
		this.logoImag = logoImag;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + "]";
	}
}
