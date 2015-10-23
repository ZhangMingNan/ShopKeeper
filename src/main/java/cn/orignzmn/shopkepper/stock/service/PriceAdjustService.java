package cn.orignzmn.shopkepper.stock.service;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.PriceAdjustGroupVO;
import cn.orignzmn.shopkepper.stock.model.vo.NewSellingPriceVO;
import cn.orignzmn.shopkepper.stock.model.vo.PriceAdjustVO;

public interface PriceAdjustService {

	List<PriceAdjustVO> findAll(PriceAdjustVO pv);
	Integer total(PriceAdjustVO pv);
	void updatePriceByProductId(PriceAdjustGroupVO pv);
	void save(PriceAdjustVO pvo);
	void updatePriceByProductNo(NewSellingPriceVO newSellingPriceVO);

}
