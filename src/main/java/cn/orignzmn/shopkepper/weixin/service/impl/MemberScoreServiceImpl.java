package cn.orignzmn.shopkepper.weixin.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.weixin.dao.MemberScoreDao;
import cn.orignzmn.shopkepper.weixin.model.CustomScore;
import cn.orignzmn.shopkepper.weixin.model.CustomScoreMessage;
import cn.orignzmn.shopkepper.weixin.service.MemberScoreService;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;


@Service
public class MemberScoreServiceImpl implements MemberScoreService {
	@Autowired
	private MemberScoreDao memberScoreDao;

	@Override
	public String findCustomScore(HashMap<String,Object>  model){
		String 	toUser = model.get("tousername").toString().trim();
		String	fromUser = model.get("fromusername").toString().trim();
		String	createTime = String.valueOf(System.currentTimeMillis() / 1000);
		CustomScore cs =	memberScoreDao.findCustomScoreByUserName(toUser);
		CustomScoreMessage csm = new CustomScoreMessage(toUser, fromUser, createTime, "text", cs.toString());
		return csm.getReplytmep();
	}

	@Override
	public String warrantyInfo(HashMap<String,Object> model){
		String 	toUser = model.get("tousername").toString().trim();
		String	fromUser = model.get("fromusername").toString().trim();
		String	createTime = String.valueOf(System.currentTimeMillis() / 1000);
		CustomScoreMessage csm = new CustomScoreMessage(toUser, fromUser, createTime, "text", info);
		return csm.getReplytmep();
	}
	
	String info = "亲，凡持有效购物凭证、三包期间（天然皮革面料的鞋类自售出之日起三个月，非天然皮革面料的鞋类自售出之日起二个月）内出现非人为因素质量问题的我们会为您负责到底哦。 另外未经穿过的新鞋，如发现不成对、大小不一致的现象，七天内可换。 还有哦，凡在正常穿着情况下一周内胶粘皮鞋发生开胶（帮底结合处脱落或弹开长度2公分、深度0.5公分以上者）；鞋面严重掉浆、泛硝之一者，我们会为您更换为同款新鞋。 最后，凡属“三包”的质量问题，一个月内同一个部位修理两次无效的，可酌情按原售价的0.5%收取折旧费予以调换哦。";



	@Override
	public long saveMember(GetUserInfoResponse me) {
		// TODO Auto-generated method stub
	    long id = memberScoreDao.saveMember(me);
		memberScoreDao.saveStockMember(id,String.valueOf(System.currentTimeMillis()));
		return id;
	}
}
