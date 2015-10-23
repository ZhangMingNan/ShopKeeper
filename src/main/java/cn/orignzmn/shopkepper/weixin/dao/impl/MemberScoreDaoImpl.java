package cn.orignzmn.shopkepper.weixin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.weixin.dao.MemberScoreDao;
import cn.orignzmn.shopkepper.weixin.model.CustomScore;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;

@Repository
public class MemberScoreDaoImpl implements MemberScoreDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public CustomScore findCustomScoreByUserName(String wxUserName){
		return jdbcTemplate.queryForObject(findCustomScoreByUserNameSql,  new BeanPropertyRowMapper<>(CustomScore.class),wxUserName);
	}

	
 
	
	@Override
	public long saveMember(final GetUserInfoResponse me) {
		// TODO Auto-generated method stub
		final String sql = "insert into wx_member (openid,nickname,sex,city,province,country,headimgurl,subscribe_time,subscribe,unionid)values(?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                int count = 1;
                psst.setString(count++,me.getOpenid());
                psst.setString(count++,me.getNickname());
                psst.setInt(count++,me.getSex());
                psst.setString(count++,me.getCity());
                psst.setString(count++,me.getProvince());
                psst.setString(count++,me.getCountry());
                psst.setString(count++,me.getHeadimgurl());
                psst.setTimestamp(count++, new Timestamp(me.getSubscribeTime()*1000));
                psst.setInt(count++, me.getSubscribe());
                psst.setString(count++, me.getUnionid());
                return psst;
            }
        },keyHolder);
        final long wxKey = keyHolder.getKey().longValue();
        return wxKey;
	}




	@Override
	public long saveStockMember(final long id,final String barcode) {
		// TODO Auto-generated method stub
	      final String insertMemberSql = "insert into stock_member(wx_id,member_barcode) values(?,?)";
	        jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement psst = connection.prepareStatement(insertMemberSql, new String[]{"id"});
	                int count = 1;
	                psst.setLong(count++,id);
	                psst.setString(count++, barcode);
	                return psst;
	            }
	        });
	        return id;
	}
}
