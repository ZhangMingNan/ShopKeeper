package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.EmployeeDao;
import cn.orignzmn.shopkepper.stock.dao.TradingRecordDao;
import cn.orignzmn.shopkepper.stock.model.Employee;
import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private TradingRecordDao tradingRecordDao;
	@Override
	public List<Employee> getData(Employee ev) {
		// TODO Auto-generated method stub
		return employeeDao.getData(ev);
	}
	@Override
	public List<TradingRecord> getRecordByEmpId(Long employeeId,String month) {
		// TODO Auto-generated method stub
		return tradingRecordDao.getRecordByEmpId(employeeId,month);
	}
	@Override
	public void save(Employee emp) {
		// TODO Auto-generated method stub
		employeeDao.save(emp);
	}
	@Override
	public void delete(Integer empId) {
		// TODO Auto-generated method stub
		employeeDao.delete(empId);
	}

}
