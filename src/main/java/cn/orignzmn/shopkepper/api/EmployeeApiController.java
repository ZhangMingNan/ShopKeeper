package cn.orignzmn.shopkepper.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.orignzmn.shopkepper.api.model.EmployeeDetail;
import cn.orignzmn.shopkepper.api.service.EmployeeDetailService;
import cn.orignzmn.shopkepper.common.FileUtil;
import cn.orignzmn.shopkepper.stock.model.Employee;
import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;
import cn.orignzmn.shopkepper.stock.service.EmployeeService;

import com.google.common.collect.Lists;

/**
 * 店员管理
 * 
 * @author 张明楠
 */
@Controller
@RequestMapping("/api/employee")
public class EmployeeApiController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeDetailService employeeDetailService;

	@RequestMapping(value = "getData", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getData(Employee ev) {
		List<Employee> list = employeeService.getData(ev);
		return list;
	}

	// ,@RequestParam MultipartFile file) throws FileNotFoundException, IOException
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public void save(Employee emp){
//		if (file != null) {
//			String fileName = FileUtil.getUuidFileName(file);
//			FileUtil.updataToQiNiu(file.getInputStream(), fileName);
//			emp.setAvataImg(fileName);
//		}
		employeeService.save(emp);
	}

	// 获取员工的销售统计情况。
	@RequestMapping(value = "getEmpDetail", method = RequestMethod.GET)
	@ResponseBody
	public EmployeeDetail getEmpDetail(Integer id) {
		return employeeDetailService.getEmpDetailById(id);
	}

	// 获取员工销售信息
	@RequestMapping(value = "getEmpTradingRecord", method = RequestMethod.GET)
	@ResponseBody
	public List<TradingRecordVO> getEmpTradingRecord(Long  employeeId,String month) {
		List<TradingRecord> list = employeeService.getRecordByEmpId(employeeId,month);
		List<TradingRecordVO> trvList = Lists.newArrayList();
		for (TradingRecord tr : list) {
			trvList.add(new TradingRecordVO(tr));
		}
		return trvList;
	}
}
