package cn.orignzmn.shopkepper.weixin.dao;

import cn.orignzmn.shopkepper.weixin.model.CustomScore;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;

public interface MemberScoreDao {

	String findCustomScoreByUserNameSql = "select wcs.wx_username as wxUserName,wcs.score,(SELECT wcl.rank FROM wx_custom_level wcl WHERE wcs.level = wcl.id) as rank from wx_custom_score wcs WHERE wcs.wx_username = ?";
	public CustomScore findCustomScoreByUserName(String wxUserName);
	public long saveMember(GetUserInfoResponse me);
	public long saveStockMember(long id, String barcode);
}
