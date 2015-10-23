package cn.orignzmn.shopkepper.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.api.model.QuarterlyParams;
import cn.orignzmn.shopkepper.api.model.QuarterlyReport;
import cn.orignzmn.shopkepper.api.service.QuarterlyReportsService;

@Controller
@RequestMapping("/api/quarterlyReports")
public class QuarterlyReportsController {
	
	@Autowired
	QuarterlyReportsService   quarterlyReportsService;
	
	@RequestMapping(value="getData",method = RequestMethod.GET)
	public  @ResponseBody List<QuarterlyReport> getData(QuarterlyParams qp){
		List<QuarterlyReport> list = quarterlyReportsService.getData(qp);
		return list;
	}
}
