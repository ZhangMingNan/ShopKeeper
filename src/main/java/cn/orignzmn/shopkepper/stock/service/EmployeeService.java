package cn.orignzmn.shopkepper.stock.service;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.Employee;
import cn.orignzmn.shopkepper.stock.model.TradingRecord;

public interface EmployeeService {

	List<Employee> getData(Employee ev);

	List<TradingRecord> getRecordByEmpId(Long employeeId,String month);

	void save(Employee emp);

	void delete(Integer empId);

}
