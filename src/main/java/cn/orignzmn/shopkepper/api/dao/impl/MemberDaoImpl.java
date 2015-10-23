package cn.orignzmn.shopkepper.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import cn.orignzmn.shopkepper.api.dao.MemberDao;
import cn.orignzmn.shopkepper.api.model.Member;
import cn.orignzmn.shopkepper.api.model.MemberQueryParams;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Member> query(MemberQueryParams params) {
		// TODO Auto-generated method stub
		String querySql = "select openid, nickname, sex, city, province, country, headimgurl, subscribe_time, subscribe, unionid ,score from wx_member wxm ";
		List<Object> arr  = Lists.newArrayList();
		if(params.getLevel() != -1){
			querySql = querySql +"where wxm.`level` = ?";
			arr.add(params.getLevel());
		} 
		return jdbcTemplate.query(querySql, new BeanPropertyRowMapper<>(Member.class),arr.toArray());
				
	}
}
