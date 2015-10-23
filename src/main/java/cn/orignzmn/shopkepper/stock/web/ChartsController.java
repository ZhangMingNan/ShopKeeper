package cn.orignzmn.shopkepper.stock.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.stock.model.StaffSalesReports;
import cn.orignzmn.shopkepper.stock.model.StaffSalesReportsQuery;
import cn.orignzmn.shopkepper.stock.model.vo.CombotBoxVO;
import cn.orignzmn.shopkepper.stock.model.vo.DateVO;
import cn.orignzmn.shopkepper.stock.model.vo.ReportVO;
import cn.orignzmn.shopkepper.stock.service.ChartsService;
import cn.orignzmn.shopkepper.stock.service.SalesReportService;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/charts")
public class ChartsController {
	@Autowired
	ChartsService chartsService;
	@Autowired
	private SalesReportService salesReportService;


	   @RequestMapping(value="salesChart",method = RequestMethod.GET)
	    public String list(Model model) {
	        return "sales_chart";
	    }
	
	    @RequestMapping(value="allStaffSalesReports",method = RequestMethod.GET)
	    public @ResponseBody List<StaffSalesReports>  allStaffSalesReports(StaffSalesReportsQuery query){
	    	return chartsService.allStaffSalesReports(query);
	    }
	

		@RequestMapping(value="monthData",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
		public  @ResponseBody Option gethcd(String type,String time)  {
			Option option = new Option();
			monthChart(option,type,time);
			return option;
		}
		private void monthChart(Option option,String type,String time) {
			Title title = new Title();
			title.setX(X.center);
			title.setY(Y.top);

			title.setText(time+"销售报表");
			option.setTitle(title);
			option.toolbox().show(false);
			option.calculable(false);
			ValueAxis valueAxis = new ValueAxis();
			valueAxis.axisLabel().formatter("{value}双");

			CategoryAxis categoryAxis = new CategoryAxis();
			categoryAxis.axisLine().onZero(true);

			categoryAxis.boundaryGap(true);

			option.xAxis(categoryAxis);
			option.yAxis( valueAxis);

			Bar salesVolumebar = new Bar();
			salesVolumebar.name("销量");
			DateVO dv ;
			List<ReportVO> datas;
			if("month".equals(type)){
				String[] timeArr = time.split(",");
				dv = new DateVO(timeArr[0],timeArr[1]);
				datas = salesReportService.getMonthReportData(dv);
				categoryAxis.axisLabel().formatter("{value}日");
			}else{
				dv = new DateVO(time);
				datas = salesReportService.getYearReportData(dv);
				categoryAxis.axisLabel().formatter("{value}月");
			}

			List<Object> salesVolumebarList = Lists.newArrayList();
			List<Object> categoryAxisList = Lists.newArrayList();
			for(ReportVO vo:datas){
				salesVolumebarList.add(vo.getCount());
				categoryAxisList.add(vo.getDate().substring(vo.getDate().lastIndexOf("-")+1));
			}
			categoryAxis.setData(categoryAxisList);
			salesVolumebar.setData(salesVolumebarList);
			option.series(salesVolumebar);
		}

		//获取报表类型。
		@RequestMapping(value="getReportTypeBoxData",method = RequestMethod.GET)
		@ResponseBody
		public List<CombotBoxVO>  getReportTypeBoxData() {
			List<CombotBoxVO>  voList = Lists.newArrayList();
			voList.add(new CombotBoxVO("Y", "年销售", "年销售"));
			voList.add(new CombotBoxVO("YM", "月销售", "月销售"));
			voList.add(new CombotBoxVO("YMD", "日销售", "日销售"));
			return  voList;
		}
		//获取有销售记录的年份。
		@RequestMapping(value="getYearReportBoxData",method = RequestMethod.GET)
		@ResponseBody
		public List<CombotBoxVO> getYearReportBoxData() {
			List<CombotBoxVO>  voList = Lists.newArrayList();
			//获取所有有销售记录的年份。
			List<String>list = salesReportService.allYearItem();
			for(int i=0;i<list.size();i++){
				CombotBoxVO cbvo = 	new CombotBoxVO(list.get(i), list.get(i)+"年");
				if(i==0){
					cbvo.setSelected(true);
				}
				voList.add(cbvo);
			}
			return  voList;
		}
		//获取有销售记录的年份。
		@RequestMapping(value="getMonthReportBoxData",method = RequestMethod.GET)
		@ResponseBody
		public List<CombotBoxVO> getMonthReportBoxData() {
			List<CombotBoxVO>  voList = Lists.newArrayList();
			//根据年份获取有销售记录的月份列表。
			List<String> yearlist = salesReportService.allYearItem();
			for(String year:yearlist){

				if(StringUtils.isNotEmpty(year)){
					List<String>list   = salesReportService.findMonthItemByYear(year);
					for(int i=0;i<list.size();i++){
						String padStr = year+"-"+StringUtils.leftPad(list.get(i), 2,"0");
						CombotBoxVO cbvo =	new CombotBoxVO(padStr,list.get(i)+"月");
						cbvo.setGroupField(year);
						if(i==0){
							cbvo.setSelected(true);
						}
						voList.add(cbvo);
					}
				}
			}
			return  voList;
		}
	//堆积柱状图
    @RequestMapping(value="stackBarChart",method = RequestMethod.GET)
    public @ResponseBody Option  stackBarChart(Option opt,StaffSalesReportsQuery query){
    	opt.calculable(false);
		opt.tooltip(new Tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)"));
		opt.legend().orient(Orient.vertical);
		opt.legend().x(X.left);
		Pie pie = new Pie("员工销售情况");
		pie.radius("70%");
		pie.center("50%","50%");
		opt.series(pie);
    	List<StaffSalesReports>  dataList = chartsService.allStaffSalesReports(query);	
    	System.out.println(">>>>"+dataList.size());
    	for(StaffSalesReports report:dataList){
    		opt.legend().data().add(report.getEname());
    		Map<String,Object> map = Maps.newHashMap();
    		map.put("value",report.getTotal());
    		map.put("name", report.getEname());	
    		pie.data().add(map);
    	}
    	return opt;
    }
}
