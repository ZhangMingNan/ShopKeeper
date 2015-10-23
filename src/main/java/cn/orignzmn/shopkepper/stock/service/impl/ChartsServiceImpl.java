package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.ChartsDao;
import cn.orignzmn.shopkepper.stock.model.StaffSalesReports;
import cn.orignzmn.shopkepper.stock.model.StaffSalesReportsQuery;
import cn.orignzmn.shopkepper.stock.service.ChartsService;


@Service
public class ChartsServiceImpl implements ChartsService {

	@Autowired
	ChartsDao chartsDao;
	
	@Override
	public List<StaffSalesReports> allStaffSalesReports(StaffSalesReportsQuery query) {
		// TODO Auto-generated method stub
		return chartsDao.allStaffSalesReports(query);
	}



}
