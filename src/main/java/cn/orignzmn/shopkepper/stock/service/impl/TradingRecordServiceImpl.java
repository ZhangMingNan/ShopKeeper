package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.TradingRecordDao;
import cn.orignzmn.shopkepper.stock.model.Employee;
import cn.orignzmn.shopkepper.stock.model.StockProduct;
import cn.orignzmn.shopkepper.stock.model.StockProductPayment;
import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordFromVo;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;
import cn.orignzmn.shopkepper.stock.service.TradingRecordService;
@Service
public class TradingRecordServiceImpl implements TradingRecordService {
	@Autowired
	private TradingRecordDao tradingRecordDao;
	
	@Override
	public List<TradingRecord> findAll(TradingRecordVO trv) {
		// TODO Auto-generated method stub
		return tradingRecordDao.findAll(trv);
	}

	@Override
	public void createTradingRecord(TradingRecordFromVo fromVo) {
			// TODO Auto-generated method stub
			StockProduct stockProduct = fromVo.getStockProduct();
			TradingRecord record = new TradingRecord();
			Employee employee = new Employee();
			employee.setId(fromVo.getEmployeeId());
			record.setEmployee(employee);
			StockProductPayment productPayment = new StockProductPayment();
			productPayment.setId(fromVo.getPayment());
			record.setProductPayment(productPayment);
			record.setSalesVolume(1);
			record.setStockProduct(stockProduct);
			record.setSale(stockProduct.getSellingPrice());
			record.setMemberBarCode(fromVo.getMemberBarCode());
			//根据商品id更新商品状态,0,待销售,1已销售。
			tradingRecordDao.updateProductStatus(record.getStockProduct().getId(),1);
			//创建销售记录
			tradingRecordDao.createTradingRecord(record);
		

	}

	@Override
	public void deleteTradingRecord(Long id,Long sspId) {
		// TODO Auto-generated method stub
		tradingRecordDao.deleteTradingRecord(id);
		//将库存记录中已经销售状态修改为未销售状态。
		tradingRecordDao.updateProductStatus(sspId,0);
	}

	@Override
	public int total(TradingRecordVO trv) {
		// TODO Auto-generated method stub
		return tradingRecordDao.total(trv);
	}

	@Override
	public void updatePost(TradingRecord tr) {
		// TODO Auto-generated method stub
		tradingRecordDao.updatePost(tr);
	}

	@Override
	public String getMixDate() {
		// TODO Auto-generated method stub
		return tradingRecordDao.getMixDate();
	}
}
