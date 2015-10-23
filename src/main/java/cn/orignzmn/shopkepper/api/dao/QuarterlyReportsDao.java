package cn.orignzmn.shopkepper.api.dao;

import java.util.List;

import cn.orignzmn.shopkepper.api.model.QuarterlyReport;

public interface QuarterlyReportsDao {

	public List<QuarterlyReport> getData(int flag);
}
