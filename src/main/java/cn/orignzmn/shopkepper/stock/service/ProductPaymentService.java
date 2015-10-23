package cn.orignzmn.shopkepper.stock.service;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.StockProductPayment;

public interface ProductPaymentService {

	List<StockProductPayment> getData(StockProductPayment payment);

}
