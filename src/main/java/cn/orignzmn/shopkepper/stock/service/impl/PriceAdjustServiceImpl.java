package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.orignzmn.shopkepper.stock.dao.PriceAdjustDao;
import cn.orignzmn.shopkepper.stock.model.PriceAdjustGroupVO;
import cn.orignzmn.shopkepper.stock.model.vo.NewSellingPriceVO;
import cn.orignzmn.shopkepper.stock.model.vo.PriceAdjustVO;
import cn.orignzmn.shopkepper.stock.service.PriceAdjustService;

@Service
public class PriceAdjustServiceImpl implements PriceAdjustService {
	@Autowired
	private PriceAdjustDao priceAdjustDao;

	@Override
	public List<PriceAdjustVO> findAll(PriceAdjustVO pv) {
		// TODO Auto-generated method stub
		return priceAdjustDao.findAll(pv);
	}

	@Override
	public Integer total(PriceAdjustVO pv) {
		// TODO Auto-generated method stub
		return priceAdjustDao.total(pv);
	}

	@Override
	public void updatePriceByProductId(PriceAdjustGroupVO pv) {
		// TODO Auto-generated method stub
		priceAdjustDao.updatePriceByProductId(pv);
	}

	@Override
	public void save(PriceAdjustVO pvo) {
		// TODO Auto-generated method stub
		priceAdjustDao.save(pvo);
	}



	@Override
	public void updatePriceByProductNo(NewSellingPriceVO newSellingPriceVO) {
		// TODO Auto-generated method stub
		priceAdjustDao.updatePriceByProductNo(newSellingPriceVO);
	}
	
}
