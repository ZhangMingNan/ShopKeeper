package cn.orignzmn.shopkepper.weixin.service;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
@Transactional
public interface MemberScoreService {
	public String findCustomScore(HashMap<String,Object> model);
	public String warrantyInfo(HashMap<String,Object> model);
	public long saveMember(GetUserInfoResponse me);
}
