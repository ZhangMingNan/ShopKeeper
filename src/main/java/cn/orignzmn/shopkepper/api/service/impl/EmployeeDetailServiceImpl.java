package cn.orignzmn.shopkepper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.api.dao.EmployeeDetailDao;
import cn.orignzmn.shopkepper.api.model.EmployeeDetail;
import cn.orignzmn.shopkepper.api.service.EmployeeDetailService;
@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService {
	@Autowired
	EmployeeDetailDao employeeDetailDao;


	/* (non-Javadoc)
	 * @see cn.orignzmn.shopkepper.api.service.impl.EmployeeDetailServiec#getEmpDetailById(java.lang.Integer)
	 */
	@Override
	public EmployeeDetail getEmpDetailById(Integer id){
		return employeeDetailDao.getEmpDetailById(id);
	}
}
