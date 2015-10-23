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
import cn.orignzmn.shopkepper.stock.model.PriceAdjustGroupVO;
import cn.orignzmn.shopkepper.stock.model.vo.DataGridVo;
import cn.orignzmn.shopkepper.stock.model.vo.PriceAdjustVO;
import cn.orignzmn.shopkepper.stock.service.PriceAdjustService;

@Controller
@RequestMapping("/priceAdjust")
public class PriceAdjustController {
	
	@Autowired
	private PriceAdjustService priceAdjustService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "price_adjust_list";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(PriceAdjustGroupVO pv) {
    	priceAdjustService.updatePriceByProductId(pv);
        return WebUtils.successResult;
    }
    
    @RequestMapping(value="getData",method = RequestMethod.POST)
    @ResponseBody
    public DataGridVo<PriceAdjustVO>  getData(PriceAdjustVO pv) {

    	List<PriceAdjustVO> list = priceAdjustService.findAll(pv);
    	Integer total = priceAdjustService.total(pv);
    	return new DataGridVo<PriceAdjustVO>(total,list);
    }
}
