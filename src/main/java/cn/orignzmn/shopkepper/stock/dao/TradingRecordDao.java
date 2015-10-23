package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;

public interface TradingRecordDao {

	List<TradingRecord> findAll(TradingRecordVO trv);

	void createTradingRecord(TradingRecord record);

	void deleteTradingRecord(Long id);

	int total(TradingRecordVO trv);

	void updatePost(TradingRecord tr);

	List<TradingRecord> getRecordByEmpId(Long employeeId,String month);

	public String getMixDate();

	void updateProductStatus(Long id,int status);

}
