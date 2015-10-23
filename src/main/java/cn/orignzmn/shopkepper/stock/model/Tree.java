package cn.orignzmn.shopkepper.stock.model;

import java.util.List;

import com.google.common.collect.Lists;

public class Tree {

	private Long id;//节点ID，对加载远程数据很重要。
	private String text;//显示节点文本。
	private String state;//节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
	private boolean checked;//表示该节点是否被选中。
	//private boolean attributes;被添加到节点的自定义属性。
	private List<Tree> children = Lists.newArrayListWithCapacity(5);// 一个节点数组声明了若干节点。
	private TreeAttributes attributes;
	private String iconCls = "icon-c";
	


	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public TreeAttributes getAttributes() {
		return attributes;
	}
	public void setAttributes(TreeAttributes attributes) {
		this.attributes = attributes;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Tree(Long id, String text, String state, boolean checked) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "Tree [id=" + id + ", text=" + text + ", state=" + state
				+ ", checked=" + checked + ", children=" + children
				+ ", attributes=" + attributes + "]";
	}
	
}
