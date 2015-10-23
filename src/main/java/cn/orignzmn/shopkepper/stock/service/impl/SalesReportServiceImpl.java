package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.SalesReportDao;
import cn.orignzmn.shopkepper.stock.model.vo.DateVO;
import cn.orignzmn.shopkepper.stock.model.vo.ReportVO;
import cn.orignzmn.shopkepper.stock.service.SalesReportService;

@Service
public class SalesReportServiceImpl implements SalesReportService {
	@Autowired
	private SalesReportDao salesReportDao;

	@Override
	public List<String> allYearItem() {
		// TODO Auto-generated method stub
		return salesReportDao.allYearItem();
	}

	@Override
	public List<String> findMonthItemByYear(String year) {
		// TODO Auto-generated method stub
		return salesReportDao.findMonthItemByYear(year);
	}

	@Override
	public List<ReportVO> getMonthReportData(DateVO dv) {
		// TODO Auto-generated method stub
		return salesReportDao.getMonthReportData(dv);
	}

	@Override
	public List<ReportVO> getYearReportData(DateVO dv) {
		// TODO Auto-generated method stub
		return salesReportDao.getYearReportData(dv);
	}

	@Override
	public List<ReportVO> getDayReportData(DateVO dv) {
		// TODO Auto-generated method stub
		return salesReportDao.getDayReportData(dv);
	}
}
