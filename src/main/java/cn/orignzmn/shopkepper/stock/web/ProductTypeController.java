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
import cn.orignzmn.shopkepper.stock.model.ProductType;
import cn.orignzmn.shopkepper.stock.service.StockService;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {

	@Autowired
	private StockService stockService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "product_type_list";
    }
    
	@RequestMapping(value = "/allType", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductType> typeList(){
		List<ProductType> typeList = stockService.findAllType();
		return typeList;
	}
	
    @RequestMapping(value="save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveType(ProductType productType){
    	stockService.saveProductType(productType);
    	return WebUtils.successResult;
    }
}
