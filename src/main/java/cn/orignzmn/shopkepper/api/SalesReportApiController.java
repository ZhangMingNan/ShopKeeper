package cn.orignzmn.shopkepper.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.orignzmn.shopkepper.api.model.DayReport;
import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;
import cn.orignzmn.shopkepper.stock.service.SalesReportService;
import cn.orignzmn.shopkepper.stock.service.StockService;
import cn.orignzmn.shopkepper.stock.service.TradingRecordService;

/**
 * 销售信息接口
 * @author 张明楠
 */
@Controller
@RequestMapping("/api/salesReport")
public class SalesReportApiController {
	@Autowired
	private SalesReportService salesReportService;
	@Autowired
	StockService stockService;
	@Autowired
	private TradingRecordService tradingRecordService;

	@RequestMapping(value="getData",method = RequestMethod.GET)
	@ResponseBody
	public List<TradingRecordVO> getData(TradingRecordVO trv){
		List<TradingRecord> list  = 	tradingRecordService.findAll(trv);
		List<TradingRecordVO>trvList = Lists.newArrayList();
		for(TradingRecord tr: list){
			trvList.add(new TradingRecordVO(tr));
		}
		return trvList;
	}
	/**
	 * 得到用户是从那一天记录第一笔交易的。
	 */
	@RequestMapping(value="mixDate",method = RequestMethod.GET)
	public  @ResponseBody Map<String,String> mixDate(){
		Map<String,String> map = Maps.newHashMap();
		map.put("mixDate", tradingRecordService.getMixDate());
		return map;
	}
	
	
	
	/**
	 * 获取指定日期的销售统计数据
	 * 包含三个字段  一天销售数量， 一天销售额，一天利润
	 * @return
	 */
	@RequestMapping(value="dayReport",method = RequestMethod.GET)
	public  @ResponseBody DayReport dayReport(String date){
		DayReport dayReport = 	stockService.dayReport(date);
		return dayReport;
	}

	
}
