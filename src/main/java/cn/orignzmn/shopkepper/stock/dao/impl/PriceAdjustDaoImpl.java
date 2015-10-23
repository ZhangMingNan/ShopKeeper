package cn.orignzmn.shopkepper.stock.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.common.WebUtils;
import cn.orignzmn.shopkepper.stock.dao.PriceAdjustDao;
import cn.orignzmn.shopkepper.stock.model.PriceAdjustGroupVO;
import cn.orignzmn.shopkepper.stock.model.vo.NewSellingPriceVO;
import cn.orignzmn.shopkepper.stock.model.vo.PriceAdjustVO;

import com.google.common.collect.Lists;

@Repository
public class PriceAdjustDaoImpl  implements PriceAdjustDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String findAllSql = "SELECT "
			+ "(SELECT spt.type FROM stock_product_type spt WHERE  spt.id = spn.type) typeValue, spn.id,spn.no  as productNo ,spn.price,spn.selling_price ,spn.sales_volume as salesVolume from stock_product_no  spn order by spn.type";
	private static final String totalSql = "SELECT count(*) from stock_product_no ";
	private static final String updatePriceByProductNoSql = "update stock_product_no set price = ?,selling_price = ? where id =?";
	@Override
	public List<PriceAdjustVO> findAll(PriceAdjustVO pv) {
		List<Object> params = Lists.newArrayList();
		return jdbcTemplate.query(whereSql(findAllSql,pv,params), new BeanPropertyRowMapper<>(PriceAdjustVO.class),params.toArray());
	}
	private String whereSql(String sql ,PriceAdjustVO pv,List<Object> params){
		StringBuilder sb = new StringBuilder(sql);
        sb.append( " limit ?,?");
        params.add(WebUtils.getStart(pv.getPage(), pv.getRows()));
        params.add(pv.getRows());
		return sb.toString();
	}
	@Override
	public Integer total(PriceAdjustVO pv) {
		// TODO Auto-generated method stub 
		List<Object> params = Lists.newArrayList();
		return jdbcTemplate.queryForObject(totalSql, Integer.class,params.toArray());
	}
	@Override
	public void updatePriceByProductId(PriceAdjustGroupVO pv) {
		// TODO Auto-generated method stub
					List<Object> params = Lists.newArrayList();
					params.add(pv.getPrice());
					params.add(pv.getSellingPrice());
					params.add(pv.getIds());
					List<Object[]> batchArgs = Lists.newArrayList();
					for(int id :pv.getIds()){
						batchArgs.add(new Object[]{pv.getPrice(),pv.getSellingPrice(),id});
					}
				jdbcTemplate.batchUpdate(updatePriceByProductNoSql, batchArgs);
	}
	@Override
	public void save(final PriceAdjustVO pvo) {
		// TODO Auto-generated method stub
		String saveSql = "insert into stock_product_no (no, price, selling_price, product_icon_url,short_no,type) values (?,?,?,?,?,?)";
		jdbcTemplate.update(saveSql , new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int parameterIndex = 1;
				ps.setString(parameterIndex++, pvo.getProductNo());
				ps.setFloat(parameterIndex++,pvo.getPrice());
				ps.setFloat(parameterIndex++,pvo.getSellingPrice());
				ps.setString(parameterIndex++, pvo.getProductIconUrl());
				ps.setString(parameterIndex++, pvo.getShortNo());
				ps.setLong(parameterIndex++, pvo.getType());
			}
		});
	}
	@Override
	public int countByNo(String key) {
		// TODO Auto-generated method stub
		String  countByNoSql = "select count(*) from stock_product_no where no= ?";
		return jdbcTemplate.queryForObject(countByNoSql, Integer.class,key);
	}
	@Override
	public String saleByNo(String key) {
		// TODO Auto-generated method stub
		String  saleByNoSql = "select selling_price from stock_product_no where no = ?";
		String price = null;
		try {
			price = jdbcTemplate.queryForObject(saleByNoSql, String.class,key);
		} catch (EmptyResultDataAccessException e) {}
	    return price;
	}
	@Override
	public void saveNewNo(Long type,String key) {
		// TODO Auto-generated method stub
		PriceAdjustVO pavo = new PriceAdjustVO();
		pavo.setProductNo(key);
		pavo.setPrice(0f);
		pavo.setSellingPrice(0f);
		pavo.setProductIconUrl("");
		pavo.setShortNo(WebUtils.shortNo(key));
		pavo.setType(type);
		this.save(pavo);
	}
	@Override
	public void updatePriceByProductNo(NewSellingPriceVO newSellingPriceVO) {
		// TODO Auto-generated method stub
		String updatePriceByProductNoSql = "update stock_product_no set selling_price = ? where no = ?";
		jdbcTemplate.update(updatePriceByProductNoSql,newSellingPriceVO.getSale(),newSellingPriceVO.getProductNo());
	}
}
