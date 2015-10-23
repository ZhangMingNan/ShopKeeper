package cn.orignzmn.shopkepper.stock.service;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordFromVo;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;

public interface TradingRecordService {
	List<TradingRecord> findAll(TradingRecordVO trv);
	void createTradingRecord(TradingRecordFromVo fromVo);
	void deleteTradingRecord(Long id, Long sspId);
	int total(TradingRecordVO trv);
	void updatePost(TradingRecord tr);
	public String getMixDate();
}
