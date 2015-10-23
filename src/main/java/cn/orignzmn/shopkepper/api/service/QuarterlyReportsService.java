package cn.orignzmn.shopkepper.api.service;

import java.util.List;

import cn.orignzmn.shopkepper.api.model.QuarterlyParams;
import cn.orignzmn.shopkepper.api.model.QuarterlyReport;

public interface QuarterlyReportsService {
	List<QuarterlyReport> getData(QuarterlyParams qp);
}
