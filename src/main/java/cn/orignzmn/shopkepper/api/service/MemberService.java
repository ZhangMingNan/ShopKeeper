package cn.orignzmn.shopkepper.api.service;

import java.util.List;

import cn.orignzmn.shopkepper.api.model.Member;
import cn.orignzmn.shopkepper.api.model.MemberQueryParams;

public interface MemberService {

	List<Member> query(MemberQueryParams params);

}
