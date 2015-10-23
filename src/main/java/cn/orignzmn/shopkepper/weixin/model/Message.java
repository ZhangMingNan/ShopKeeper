package cn.orignzmn.shopkepper.weixin.model;

import org.apache.commons.lang.StringUtils;

public class Message {
	
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	
	private static final String messageTemp  = 
			 "<ToUserName><![CDATA[toUser]]></ToUserName>"+
			 "<FromUserName><![CDATA[fromUser]]></FromUserName>"+
			 "<CreateTime>createTime</CreateTime>"+
			 "<MsgType><![CDATA[text]]></MsgType>";

	public String wrap(String str){
		StringBuilder sb = new StringBuilder("<xml>");
		sb.append(this.getMessagetemp());
		sb.append(str);
		sb.append("</xml>");
		return sb.toString();
	}
	public Message(String toUserName, String fromUserName, String createTime,
			String msgType) {
		super();
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public  String getMessagetemp() {
		return StringUtils.replaceEach(messageTemp, new String[]{"toUser","fromUser","createTime"},new String[]{this.FromUserName,this.ToUserName,this.CreateTime});	
	}
}
