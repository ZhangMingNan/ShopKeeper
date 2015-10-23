package cn.orignzmn.shopkepper.weixin.model.response;


 /**
  * 获取所有关注者信息
  * @author 张明楠
  *http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96%E5%85%B3%E6%B3%A8%E8%80%85%E5%88%97%E8%A1%A8
  */
public class UsersInfo {
	   private Long total;

	    private Integer count;

	    private String openid;

	    private String next_openid;

		public Long getTotal() {
			return total;
		}

		public void setTotal(Long total) {
			this.total = total;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getNext_openid() {
			return next_openid;
		}

		public void setNext_openid(String next_openid) {
			this.next_openid = next_openid;
		}

		@Override
		public String toString() {
			return "UsersInfo [total=" + total + ", count=" + count
					+ ", openid=" + openid + ", next_openid=" + next_openid
					+ "]";
		}
}
