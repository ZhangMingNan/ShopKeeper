package cn.orignzmn.shopkepper.stock.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.common.WebUtils;
import cn.orignzmn.shopkepper.stock.dao.TradingRecordDao;
import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;

import com.google.common.collect.Lists;
@Repository
public class TradingRecordDaoImpl   implements TradingRecordDao{

	private static final 	String updatePostSql = "update stock_trading_record set post=0 where id=?";
	private static final  String allTradingRecordSql = "SELECT str.id,"
			+ "str.date,"
			+ "str.sale,"
			+ "str.sales_volume,"
			+ "(SELECT se.id from stock_employee se where se.id = str.employee_id) as   eid,"
			+ "(SELECT se.name from stock_employee se where se.id = str.employee_id),"
			+ "(select (select prn.no from stock_product_no prn where ssp.product_no = prn.id) from stock_stock_product ssp  where id = stock_product_id ),"
			+ "(select ssp.product_no from stock_stock_product ssp  where id = stock_product_id ),"
			+ "(select (select spt.type from stock_product_type spt where spt.id = ssp.type)from stock_stock_product ssp  where id = stock_product_id ),"
			+ "(select (select sps.size from stock_product_size sps where sps.id = ssp.size) from stock_stock_product ssp  where id = stock_product_id),"
			+ "(SELECT spp.id FROM stock_product_payment spp where spp.id = str.payment), "
			+ "(SELECT spp.payment FROM stock_product_payment spp where spp.id = str.payment) , "
			+ "(select (select prn.price from stock_product_no prn where ssp.product_no = prn.id) from stock_stock_product ssp  where id = stock_product_id ),"
			+ "(select ssp.id from stock_stock_product ssp  where id = stock_product_id)"
			+ "FROM stock_trading_record str ";                                                                                                                                                                                                                                                                                                                                                            

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<TradingRecord> findAll(TradingRecordVO trv) {
		List<Object> params = Lists.newArrayList();
		StringBuilder sb = new StringBuilder(allTradingRecordSql);
		if(trv.getDate()!=null){
			sb.append(" WHERE DATE(str.date) = DATE(?) ");
			params.add(trv.getDate());
		}
		if (trv.getPost()!=null){
			if(trv.getDate()!=null){
				sb.append("and str.post = ?");
			}else{
				sb.append("WHERE  str.post = ?");
			}
			params.add(trv.getPost());
		} 
		if(trv.getPage()!=null&&trv.getRows()!=null){
			sb.append(" limit ?,? ");
			params.add(WebUtils.getStart(trv.getPage(), trv.getRows()));
			params.add(trv.getRows());
		}
		return jdbcTemplate.query(sb.toString(), new TradingRecordMapper(),params.toArray());
	}
	private static final class TradingRecordMapper implements RowMapper<TradingRecord> {
	    public TradingRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	TradingRecord tr = new TradingRecord();
	    	int columnIndex = 1;
	    	tr.setId(rs.getLong(columnIndex++));
	    	tr.setDate(rs.getTimestamp(columnIndex++));
	        tr.setSale(rs.getFloat(columnIndex++));
	    	tr.setSalesVolume(rs.getInt(columnIndex++));
	    	tr.getEmployee().setId(rs.getLong(columnIndex++));
	    	tr.getEmployee().setName(rs.getString(columnIndex++));
	    	tr.getStockProduct().getProductNo().setNo(rs.getString(columnIndex++));
	    	tr.getStockProduct().getProductNo().setId(rs.getLong(columnIndex++));
	    	tr.getStockProduct().getProductType().setType(rs.getString(columnIndex++));
	    	tr.getStockProduct().getProductSize().setSize(rs.getString(columnIndex++));
	    	tr.getProductPayment().setId(rs.getLong(columnIndex++));
	    	tr.getProductPayment().setPayment(rs.getString(columnIndex++));
	    	tr.getStockProduct().getProductNo().setPrice(rs.getFloat(columnIndex++));
	    	tr.getStockProduct().setId(rs.getLong(columnIndex++));
	    	return tr;
	    }
	}
	@Override
	public void createTradingRecord(final TradingRecord record) {
		// TODO Auto-generated method stub
		final String sql = "INSERT INTO stock_trading_record (member_barcode,`date`, `sales_volume`, `sale`, `stock_product_id`, `employee_id`, `payment`,post,profit) "
				+ "VALUES (?,?,?,?,?,?,?,1,(SELECT spn.profit FROM stock_product_no spn , stock_stock_product ssp where ssp.product_no = spn.id and ssp.id =?))";
		final String updateDayReportSql = "UPDATE  stock_day_report sdr set sdr.volumen = sdr.volumen + ? ,\n" +
				"sdr.saleroom = sdr.saleroom + ? , \n" +
				"sdr.profit = sdr.profit + (SELECT spn.profit FROM stock_product_no spn,stock_stock_product ssp where spn.id = ssp.product_no and \n" +
				"ssp.id = ?) where date(sdr.date) = date(?)";
		final String updateMemberScoreSql = "update stock_member sm set sm.score= sm.score + ? where sm.member_barcode = ?";
		
		//更新用户积分
		//根据会员码更新会员积分
		jdbcTemplate.update(updateMemberScoreSql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int parameterIndex = 1;
				ps.setInt(parameterIndex++, record.getSale().intValue());
				ps.setString(parameterIndex++, record.getMemberBarCode());
			}
		});
		
		
		
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int parameterIndex = 1;
				ps.setString(parameterIndex++, record.getMemberBarCode());
				ps.setTimestamp(parameterIndex++, new Timestamp(System.currentTimeMillis()));
				ps.setInt(parameterIndex++, record.getSalesVolume());
				ps.setDouble(parameterIndex++, record.getSale());
				ps.setLong(parameterIndex++, record.getStockProduct().getId());
				ps.setLong(parameterIndex++, record.getEmployee().getId());
				ps.setLong(parameterIndex++, record.getProductPayment().getId());
				ps.setLong(parameterIndex++, record.getStockProduct().getId());
			}
		});
		
		//如果没有当日的日报记录就插入一条
		if(0==jdbcTemplate.queryForObject("SELECT COUNT(*) FROM stock_day_report where DATE(date) = DATE(NOW())", Integer.class)){
			String insertSql  = "INSERT INTO stock_day_report(volumen,saleroom,profit,date) VALUES(0,0,0,now())";
			jdbcTemplate.update(insertSql);
		}
		
		jdbcTemplate.update(updateDayReportSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int parameterIndex = 1;
				ps.setInt(parameterIndex++, record.getSalesVolume());
				ps.setDouble(parameterIndex++, record.getSale());
				ps.setLong(parameterIndex++, record.getStockProduct().getId());
				ps.setTimestamp(parameterIndex++, new Timestamp(System.currentTimeMillis()));
			}
		});
	}
	@Override
	public void deleteTradingRecord(Long id) {
		// TODO Auto-generated method stub
		String updateDayReportSql = "update stock_day_report sdr set \n" +
				"sdr.volumen = sdr.volumen - (SELECT str.sales_volume FROM stock_trading_record str WHERE str.id = ?),\n" +
				"sdr.saleroom = sdr.saleroom - (SELECT str.sale FROM stock_trading_record str WHERE str.id = ?),\n" +
				"sdr.profit = sdr.profit - (SELECT str.profit FROM stock_trading_record str WHERE  str.id = ?)";
		jdbcTemplate.update(updateDayReportSql, id,id,id);
		String deleteTradingRecordSql = "delete from  stock_trading_record where id = ?";
		jdbcTemplate.update(deleteTradingRecordSql, id);
	}
	@Override
	public int total(TradingRecordVO trv) {
		// TODO Auto-generated method stub
		List<Object> params = Lists.newArrayList();
		StringBuilder sb = new StringBuilder("select count(*) from stock_trading_record");
		if(trv.getDate()!=null){
			sb.append(" WHERE DATE(date) = DATE(?) ");
			params.add(trv.getDate());
		}
		return jdbcTemplate.queryForObject(sb.toString(),Integer.class,params.toArray());
	}
	@Override
	public void updatePost(TradingRecord tr) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(updatePostSql, tr.getId());
	}
	@Override
	public List<TradingRecord> getRecordByEmpId(Long employeeId,String month) {
		// TODO Auto-generated method stub
		String[] arr = StringUtils.split(month, "-");
		String sql = allTradingRecordSql +   " where  str.employee_id =?  and YEAR(str.date) = ? and MONTH(str.date) = ?";
		return 	 jdbcTemplate.query(sql, new TradingRecordMapper(),employeeId,arr[0],arr[1]);
	}
	
	public String getMixDate(){
		String getMixDateSql = "SELECT date(date) FROM stock_trading_record ORDER BY date asc LIMIT 0,1";		
		String date = null;
try {
	date = jdbcTemplate.queryForObject(getMixDateSql, String.class);
} catch (EmptyResultDataAccessException e) {
	// TODO: handle exception
	return null;
}
return date;
	}
	@Override
	public void updateProductStatus(Long id,int status) {
		// TODO Auto-generated method stub
		String  updateProductStatusSql = "update stock_stock_product set status= ?  where id=?";
		jdbcTemplate.update(updateProductStatusSql,status, id);
	}

}
