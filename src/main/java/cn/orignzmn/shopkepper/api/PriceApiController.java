package cn.orignzmn.shopkepper.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.stock.model.vo.PriceAdjustVO;
import cn.orignzmn.shopkepper.stock.service.PriceAdjustService;


/**
 * 展示各个货号价格的列表信息。
 * @author zhangmingnan
 */
@Controller
@RequestMapping("/api/price")
public class PriceApiController {
	@Autowired
	private PriceAdjustService priceAdjustService;
    @RequestMapping(value="getData",method = RequestMethod.GET)
    @ResponseBody
    public List<PriceAdjustVO>  getData(PriceAdjustVO pv) {
       	List<PriceAdjustVO> list = priceAdjustService.findAll(pv);
    	return list;
    }
}
