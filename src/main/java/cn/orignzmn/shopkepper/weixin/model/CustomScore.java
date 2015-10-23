package cn.orignzmn.shopkepper.weixin.model;

public class CustomScore {

	
	private String wxUserName;
	private Integer score;
	private String rank;
	public String getWxUserName() {
		return wxUserName;
	}
	public void setWxUserName(String wxUserName) {
		this.wxUserName = wxUserName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "会员等级："+this.rank+"会员，会员积分："+this.score+"!";
	}
	public CustomScore() {
		super();
		// TODO Auto-generated constructor stub
	}
}
