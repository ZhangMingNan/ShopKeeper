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
import cn.orignzmn.shopkepper.stock.model.StockMember;
import cn.orignzmn.shopkepper.stock.model.vo.DataGridVo;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;
import cn.orignzmn.shopkepper.stock.service.StockMemberService;

@Controller
@RequestMapping("/member")
public class StockMemberController {
	@Autowired
	StockMemberService  stockMemberService;
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String stockIn(Model model) {
		return "member_list";
	}
    @RequestMapping(value="getData",method = RequestMethod.POST)
    @ResponseBody
    public DataGridVo<StockMember>  getData(PagVo pv ) {
    	List<StockMember> list = stockMemberService.getData(pv);
    	Integer total =  stockMemberService.total();
    	return new DataGridVo<StockMember>(total, list);
    }
    
    @RequestMapping(value="save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(StockMember member){
    	stockMemberService.save(member);
    	return WebUtils.successResult;
    }
    @RequestMapping(value="delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(Long id){
    	stockMemberService.delete(id);
    	return WebUtils.successResult;
    }
}
