package cn.orignzmn.shopkepper.stock.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.common.WebUtils;
import cn.orignzmn.shopkepper.stock.model.Employee;
import cn.orignzmn.shopkepper.stock.model.vo.CombotBoxVO;
import cn.orignzmn.shopkepper.stock.model.vo.DataGridVo;
import cn.orignzmn.shopkepper.stock.service.EmployeeService;

import com.google.common.collect.Lists;
/**
 * 店员管理
 * @author 张明楠
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

@Autowired
private EmployeeService employeeService;
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "employee_list";
    }

    @RequestMapping(value="getEmployeeCombotBoxData",method = RequestMethod.GET)
    @ResponseBody
    public List<CombotBoxVO>  getProductSizeData() {
    	List<Employee> list = employeeService.getData(null);
    	List<CombotBoxVO> voList = Lists.newArrayList();
    	for(Employee e:list){
    		voList.add(new CombotBoxVO(String.valueOf(e.getId()),e.getName(),e.getPhone()));
    	}
    	return voList;
    }
    
    @RequestMapping(value="save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(Employee emp){
    	System.out.println(emp.toString());
    	employeeService.save(emp);
    	return WebUtils.successResult;
    }
    
    @RequestMapping(value="delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(Integer empId){
    	employeeService.delete(empId);
    	return WebUtils.successResult;
    }
    
    
    @RequestMapping(value="getData",method = RequestMethod.POST)
    @ResponseBody
    public DataGridVo<Employee>  getData(Employee ev) {
    	List<Employee> list = employeeService.getData(ev);
    	return new DataGridVo<Employee>(list.size(),list);
    }
}
