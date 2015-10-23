package cn.orignzmn.shopkepper.stock.model;

import java.io.Serializable;

public class Resource implements Serializable {

	private static final long serialVersionUID = -6930609253986012120L;
	private Long id; //编号
    private String name; //资源名称
    private String type;
    private String url; //资源路径
    private Long  parentId; //父编号
    private Boolean available = Boolean.TRUE;
    private String icon;
    
    public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Resource(String name) {
		// TODO Auto-generated constructor stub
    	this.name = name;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", type=" + type
				+ ", url=" + url + ", parentId=" + parentId + ", available="
				+ available + "]";
	}
	public Resource() {
    	super();
	}
}
