package cn.orignzmn.shopkepper.weixin.model.response;


/**
 * 根据openid 获取的用户信息类。
 * @author 张明楠
 */
public class MemberInfo {
	   private Integer subscribe;

	    private String openid;

	    private String nickname;

	    private Integer sex;

	    private String language;

	    private String city;

	    private String province;

	    private String country;

	    private String headimgurl;

	    private Long subscribe_time;

	    private String unionid;

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

		public Long getSubscribe_time() {
			return subscribe_time;
		}

		public void setSubscribe_time(Long subscribe_time) {
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
			return "UserInfo [subscribe=" + subscribe + ", openid=" + openid
					+ ", nickname=" + nickname + ", sex=" + sex + ", language="
					+ language + ", city=" + city + ", province=" + province
					+ ", country=" + country + ", headimgurl=" + headimgurl
					+ ", subscribe_time=" + subscribe_time + ", unionid="
					+ unionid + "]";
		}
}
