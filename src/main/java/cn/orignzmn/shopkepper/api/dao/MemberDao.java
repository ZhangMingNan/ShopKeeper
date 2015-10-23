package cn.orignzmn.shopkepper.api.dao;

import java.util.List;

import cn.orignzmn.shopkepper.api.model.Member;
import cn.orignzmn.shopkepper.api.model.MemberQueryParams;

public interface MemberDao {

	List<Member> query(MemberQueryParams params);

}
