package cn.orignzmn.shopkepper.stock.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.stock.model.StockProductPayment;
import cn.orignzmn.shopkepper.stock.model.vo.CombotBoxVO;
import cn.orignzmn.shopkepper.stock.service.ProductPaymentService;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/productPayment")
public class ProductPaymentController {
	@Autowired
	private ProductPaymentService productPaymentService;
	
    @RequestMapping(value="getProductPaymentCombotBoxData",method = RequestMethod.GET)
    @ResponseBody
    public List<CombotBoxVO>  getProductSizeData() {
    	List<StockProductPayment> list = productPaymentService.getData(null);
    	List<CombotBoxVO> voList = Lists.newArrayList();
    	for(StockProductPayment p:list){
    		CombotBoxVO sbvo = new CombotBoxVO(String.valueOf(p.getId()),p.getPayment(),null);
    		voList.add(sbvo );
    	}
    	if(voList!=null&&voList.size()>0){
    		voList.get(0).setSelected(true);
    	}
    	return voList;
    }
}
