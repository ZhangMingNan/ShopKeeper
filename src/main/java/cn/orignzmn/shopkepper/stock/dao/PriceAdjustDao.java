package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.stock.model.PriceAdjustGroupVO;
import cn.orignzmn.shopkepper.stock.model.vo.NewSellingPriceVO;
import cn.orignzmn.shopkepper.stock.model.vo.PriceAdjustVO;

public interface PriceAdjustDao {

	List<PriceAdjustVO> findAll(PriceAdjustVO pv);

	Integer total(PriceAdjustVO pv);

	void updatePriceByProductId(PriceAdjustGroupVO pv);



	void save(PriceAdjustVO pvo);

	int countByNo(String key);

	String saleByNo(String key);

	void saveNewNo(Long type, String key);

	void updatePriceByProductNo(NewSellingPriceVO newSellingPriceVO);

}
