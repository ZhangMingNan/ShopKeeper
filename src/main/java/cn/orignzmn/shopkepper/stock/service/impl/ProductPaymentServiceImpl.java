package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.ProductPaymentDao;
import cn.orignzmn.shopkepper.stock.model.StockProductPayment;
import cn.orignzmn.shopkepper.stock.service.ProductPaymentService;
@Service
public class ProductPaymentServiceImpl implements ProductPaymentService{
	@Autowired
	private ProductPaymentDao productPaymentDao;
	@Override
	public List<StockProductPayment> getData(StockProductPayment payment) {
		// TODO Auto-generated method stub
		return productPaymentDao.getData(payment);
	}

}
