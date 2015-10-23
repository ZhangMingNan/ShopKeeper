package cn.orignzmn.shopkepper.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.api.dao.MemberDao;
import cn.orignzmn.shopkepper.api.model.Member;
import cn.orignzmn.shopkepper.api.model.MemberQueryParams;
import cn.orignzmn.shopkepper.api.service.MemberService;
@Service
public class MemberServiceImpl  implements MemberService{

	@Autowired
	MemberDao memberDao;
	
	@Override
	public List<Member> query(MemberQueryParams params) {
		// TODO Auto-generated method stub
		return memberDao.query(params);
	}
}
