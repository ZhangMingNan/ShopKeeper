package cn.orignzmn.shopkepper.api;

import java.util.List;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.api.model.StockInfo;
import cn.orignzmn.shopkepper.stock.model.vo.StockReprotVO;
import cn.orignzmn.shopkepper.stock.service.StockService;

@Controller
@RequestMapping("/api/stock")
public class StockApiController {

	@Autowired
	StockService stockService;
	
	@RequestMapping(value="getData",method = RequestMethod.GET)
	public  @ResponseBody List<StockInfo> getData(StockReprotVO pv){
	    List<StockInfo> list = 	stockService.getStockInfo(pv);
	    

		return list;
	}
	
}
