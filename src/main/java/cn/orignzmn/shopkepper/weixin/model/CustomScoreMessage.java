package cn.orignzmn.shopkepper.weixin.model;

import org.apache.commons.lang.StringUtils;

public class CustomScoreMessage extends Message {
	private String Content;
	public CustomScoreMessage(String toUserName, String fromUserName,
			String createTime, String msgType,String content) {
		super(toUserName, fromUserName, createTime, msgType);
		this.Content = content;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	private static final String replyTmep = "<Content><![CDATA[content]]></Content>";
	public  String getReplytmep() {
		return super.wrap(StringUtils.replace(replyTmep, "content", this.Content));
	}
}
