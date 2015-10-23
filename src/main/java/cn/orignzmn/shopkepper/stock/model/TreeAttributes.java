package cn.orignzmn.shopkepper.stock.model;

public class TreeAttributes {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TreeAttributes(String url) {
		super();
		this.url = url;
	}

	@Override
	public String toString() {
		return "TreeAttributes [url=" + url + "]";
	}
	
}
