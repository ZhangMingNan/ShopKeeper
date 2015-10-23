package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.StockProductPayment;

public interface ProductPaymentDao {

	List<StockProductPayment> getData(StockProductPayment payment);

}
