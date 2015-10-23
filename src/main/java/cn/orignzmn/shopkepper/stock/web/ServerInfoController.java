package cn.orignzmn.shopkepper.stock.web;

import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.common.BaseController;
import cn.orignzmn.shopkepper.common.SystemInfo;

@Controller
@RequestMapping("serverInfo")
public class ServerInfoController extends BaseController {

	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		return "server_info";
	}
	
	@RequestMapping("getStatusData")
	@ResponseBody
	public SystemInfo getStatusInfo(){
	//	SystemInfo sb = new SystemInfo();
		//Sigar sigar = new Sigar();
		//sb.loadData(sigar, getSystemPhysicalPath());
		return null;
	}

}
