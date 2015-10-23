package cn.orignzmn.shopkepper.stock.model.vo;

public class ProductGroup {
	private String codeList;
	private Long typeId;
	public String getCodeList() {
		return codeList;
	}
	public void setCodeList(String codeList) {
		this.codeList = codeList;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "ProductGroup [codeList=" + codeList + ", typeId=" + typeId
				+ "]";
	}
}
