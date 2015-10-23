package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.vo.DateVO;
import cn.orignzmn.shopkepper.stock.model.vo.ReportVO;

public interface SalesReportDao {

	List<String> allYearItem();

	List<String> findMonthItemByYear(String year);

	List<ReportVO> getMonthReportData(DateVO dv);

	List<ReportVO> getYearReportData(DateVO dv);

	List<ReportVO> getDayReportData(DateVO dv);

}
