package cn.orignzmn.shopkepper.stock.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.common.WebUtils;
import cn.orignzmn.shopkepper.stock.dao.StockMemberDao;
import cn.orignzmn.shopkepper.stock.model.StockMember;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;

import com.google.common.collect.Lists;

@Repository
public class StockMemberDaoImpl implements StockMemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	final static String getDataSql = "select sm.id , sm.phone,sm.score from stock_member sm";
	@Override
	public List<StockMember> getData(PagVo pv) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(getDataSql);
		List<Object> params = Lists.newArrayListWithCapacity(2);
		if(pv.getPage()!=null&&pv.getRows()!=null){
			sb.append(" limit ?,? ");
			params.add(WebUtils.getStart(pv.getPage(), pv.getRows()));
			params.add(pv.getRows());
		}
		return jdbcTemplate.query(sb.toString(), new StockMemberRowMapper(),params.toArray());
	}
	private static final class StockMemberRowMapper implements RowMapper<StockMember> {
		@Override
		public StockMember mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			StockMember obj = new StockMember();
	    	int columnIndex = 1;
	    	obj.setId(rs.getLong(columnIndex++));
	    	obj.setPhone(rs.getString(columnIndex++));
	    	obj.setScore(rs.getInt(columnIndex++));
			return obj;
		}
	}


	final static String totalSql = "select count(sm.id) from stock_member sm";
	@Override
	public Integer total() {
		return jdbcTemplate.queryForObject(totalSql, Integer.class);
	}
	@Override
	public void save(final StockMember member) {
		// TODO Auto-generated method stub
		String saveSql = "insert into `stock_member` (`phone`,`score`)values(?,0)";
		jdbcTemplate.update(saveSql, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int parameterIndex = 1;
				ps.setString(parameterIndex++, member.getPhone());
			}
		});
	}
	static final  String deleteSql = "delete from `stock_member` where `id`=?";
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(deleteSql,id);
	}
}
