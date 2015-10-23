package cn.orignzmn.shopkepper.stock.model.vo;

public class CombotBoxVO {
	private String id;
	private String text;
	private String desc;
	private String groupField;
	private Boolean selected = Boolean.FALSE;
	
	
	
	public String getGroupField() {
		return groupField;
	}
	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public CombotBoxVO(String id, String text, String desc) {
		super();
		this.id = id;
		this.text = text;
		this.desc = desc;
	}
	public CombotBoxVO(String id, String text) {
		super();
		this.id = id;
		this.text = text;
		this.desc = text;
	}
	@Override
	public String toString() {
		return "CombotBoxVO [id=" + id + ", text=" + text + ", desc=" + desc
				+ ", groupField=" + groupField + ", selected=" + selected + "]";
	}
}
