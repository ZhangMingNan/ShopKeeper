package cn.orignzmn.shopkepper.api.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.orignzmn.shopkepper.api.dao.QuarterlyReportsDao;
import cn.orignzmn.shopkepper.api.model.QuarterlyParams;
import cn.orignzmn.shopkepper.api.model.QuarterlyReport;
import cn.orignzmn.shopkepper.api.service.QuarterlyReportsService;
import cn.orignzmn.shopkepper.stock.model.vo.DateVO;
import cn.orignzmn.shopkepper.stock.model.vo.ReportVO;
import cn.orignzmn.shopkepper.stock.service.SalesReportService;
@Service
public class QuarterlyReportsServiceImpl implements QuarterlyReportsService {

	@Autowired
	private QuarterlyReportsDao quarterlyReportsDao;
	@Autowired
	private SalesReportService salesReportService;
	@Override
	public List<QuarterlyReport> getData(QuarterlyParams qp) {
		List<ReportVO> list  = null;	
		DateVO dv = new DateVO();
		if(qp.getType().equals("1")){
			dv.setYear(qp.getTime());
			list = salesReportService.getYearReportData(dv);
		}else if(qp.getType().equals("2")){
			String year = StringUtils.split(qp.getTime(),"-")[0];
			String month = StringUtils.split(qp.getTime(),"-")[1];
			dv.setYear(year);
			dv.setMonth(month);

			list = 	salesReportService.getMonthReportData(dv);
		}else if(qp.getType().equals("3")){
			String[] arr =  StringUtils.split(qp.getTime(),"-");
			String year =arr[0];
			String month = arr[1];
			String day = arr[2];
			dv.setYear(year);
			dv.setMonth(month);
			dv.setDay(day);
			list =  	salesReportService.getDayReportData(dv);
		}
 
		List<QuarterlyReport> qrList = Lists.newArrayList();
		if(list!=null){
			for(ReportVO rv : list){
				QuarterlyReport qr = new QuarterlyReport();
				String[] arr = StringUtils.split(rv.getDate(),"-");
				qr.setTime(arr[arr.length-1]);
				qr.setNumber(Integer.valueOf(rv.getCount()));
				//qr.setProfit(Double.valueOf(rv.getProfit()));
				qr.setSales(Double.valueOf(rv.getTotalSale()));
				qr.setProfit(Double.valueOf(rv.getProfit()));
				qrList.add(qr);
			}
		}
		return qrList;
	}


}
