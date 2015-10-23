package cn.orignzmn.shopkepper.stock.model.vo;


public class CombotreeVo<T> {

	private Long id;
	private String text;
	private String state;
	private Boolean checked = false;
	

	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public CombotreeVo(Long id,String text,String state,Boolean che) {
		super();
		this.checked = che;
		this.id =id;
		this.text = text;
		this.state = state;
	}
	public CombotreeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CombotreeVo [id=" + id + ", text=" + text + "]";
	}
}
