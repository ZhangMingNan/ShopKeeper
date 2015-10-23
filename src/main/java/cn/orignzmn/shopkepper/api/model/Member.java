package cn.orignzmn.shopkepper.api.model;

import java.util.Date;

/**
 * 会员信息
 * @author 张明楠  
 *
 */
public class Member {
	private Long id;
	private Integer subscribe;

	private String openid;

	private String nickname;

	private Integer sex;

	private String language;

	private String city;

	private String province;

	private String country;

	private String headimgurl;

	private Date subscribe_time;

	private String unionid;
	
	//用户的等级id
	private Long level;
	//用户积分
	private Integer score;
	
	

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}



	public Date getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", subscribe=" + subscribe + ", openid="
				+ openid + ", nickname=" + nickname + ", sex=" + sex
				+ ", language=" + language + ", city=" + city + ", province="
				+ province + ", country=" + country + ", headimgurl="
				+ headimgurl + ", subscribe_time=" + subscribe_time
				+ ", unionid=" + unionid + ", level=" + level + ", score="
				+ score + "]";
	}
}
