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
import cn.orignzmn.shopkepper.stock.dao.TradingRecordDao;
import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.DataGridVo;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordFromVo;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;
import cn.orignzmn.shopkepper.stock.service.TradingRecordService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/tradingRecord")
public class TradingRecordController {				
	@Autowired
	private TradingRecordService tradingRecordService;
	
	
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  delete(Long id,Long sspId) {
    	tradingRecordService.deleteTradingRecord(id,sspId);
   
        return WebUtils.successResult;
    }
    

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(TradingRecordFromVo fromVo) {
    	tradingRecordService.createTradingRecord(fromVo);
        return WebUtils.successResult;
    }
	
    @Autowired
    TradingRecordDao tradingRecordDao;
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return "trading_record_list";
    }
    
    @RequestMapping(value="getData",method = RequestMethod.GET)
    @ResponseBody
    public DataGridVo<TradingRecordVO>  getData(TradingRecordVO trv) {
    List<TradingRecord> trList = 	tradingRecordService.findAll(trv);
    List<TradingRecordVO>trvList = Lists.newArrayList();
    float totalSale = 0f;
    float carSale = 0f;
    float cashSale = 0f;
    for(TradingRecord tr: trList){
    	trvList.add(new TradingRecordVO(tr));
    	totalSale += tr.getSale();
    	if(tr.getProductPayment().getId() == 1){ //现金
    		cashSale+=tr.getSale();
    	}else if (tr.getProductPayment().getId() == 2){//刷卡
    		carSale+=tr.getSale();
    	}
    }
    int total = tradingRecordService.total(trv);
    DataGridVo<TradingRecordVO> dgv =  new DataGridVo<TradingRecordVO>(total,trvList);
    

        List<Map<String,String>> footerlist = Lists.newArrayList();
        Map<String,String> map = Maps.newHashMap();
        map.put("date","总额:"+String.valueOf(totalSale));
        Map<String,String> map1 = Maps.newHashMap();
        map1.put("date","刷卡:"+String.valueOf(carSale));
        Map<String,String> map2 = Maps.newHashMap();
        map2.put("date","现金:"+String.valueOf(cashSale));
        footerlist.add(map);
        footerlist.add(map1);
        footerlist.add(map2);
        dgv.setFooter(footerlist);
    
	return dgv;
    }
}
