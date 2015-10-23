package cn.orignzmn.shopkepper.weixin.model.response;


 /**
  * 微信接口异常类。
  * @author 张明楠
  */
public class BaseModel {
    private String errcode;
    private String errmsg;
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "BaseResponse [errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}
}
