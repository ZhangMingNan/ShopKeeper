package cn.orignzmn.shopkepper.stock.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import cn.orignzmn.shopkepper.stock.dao.EmployeeDao;
import cn.orignzmn.shopkepper.stock.model.Employee;
@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Employee> getData(Employee ev) {
		// TODO Auto-generated method stub
		String sql = "select id,name,phone from stock_employee";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}
	@Override
	public void save(final Employee emp) {
		// TODO Auto-generated method stub
		String sql = "insert stock_employee(name,phone,avataImg) values(?,?,?)";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				int parameterIndex = 1;
				ps.setString(parameterIndex++, emp.getName());
				ps.setString(parameterIndex++, emp.getPhone());
				ps.setString(parameterIndex++, emp.getAvataImg());
			}
		});
	}
	@Override
	public void delete(Integer empId) {
		// TODO Auto-generated method stub
		String sql = "delete from stock_employee where id = ?";
		jdbcTemplate.update(sql,empId);
	}
}
