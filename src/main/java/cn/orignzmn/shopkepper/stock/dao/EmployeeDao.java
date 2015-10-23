package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.Employee;

public interface EmployeeDao {

	List<Employee> getData(Employee ev);

	void save(Employee emp);

	void delete(Integer empId);

}
