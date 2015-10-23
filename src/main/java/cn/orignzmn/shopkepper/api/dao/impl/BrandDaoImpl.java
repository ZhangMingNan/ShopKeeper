package cn.orignzmn.shopkepper.api.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.api.dao.BrandDao;
import cn.orignzmn.shopkepper.api.model.Brand;

@Repository
public class BrandDaoImpl implements BrandDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void save(final Brand brand){
		String saveSql = "insert `stock_brand`(`name`,`logoImag`) values(?,?)";
		jdbcTemplate.update(saveSql, new PreparedStatementSetter(){
			int parameterIndex = 1;
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(parameterIndex++, brand.getName());
				ps.setString(parameterIndex++, brand.getLogoImag());
			}
		});
	}


	@Override
	public void deleteById(Long id){
		String deleteByIdSql = "delete from `stock_brand` where `id`=?";
		jdbcTemplate.update(deleteByIdSql,id);
	}


	@Override
	public List<Brand> queryAll(){
		String queryAllSql = "SELECT name,logoImag,id FROM stock_brand";
		return jdbcTemplate.query(queryAllSql, new BeanPropertyRowMapper<>(Brand.class));
	}
}
