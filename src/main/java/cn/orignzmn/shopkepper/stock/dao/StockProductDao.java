package cn.orignzmn.shopkepper.stock.dao;

import java.util.List;

import cn.orignzmn.shopkepper.api.model.DayReport;
import cn.orignzmn.shopkepper.api.model.StockInfo;
import cn.orignzmn.shopkepper.stock.model.ProductSize;
import cn.orignzmn.shopkepper.stock.model.ProductType;
import cn.orignzmn.shopkepper.stock.model.StockIn;
import cn.orignzmn.shopkepper.stock.model.StockInDetail;
import cn.orignzmn.shopkepper.stock.model.StockOut;
import cn.orignzmn.shopkepper.stock.model.StockOutDetail;
import cn.orignzmn.shopkepper.stock.model.StockProduct;
import cn.orignzmn.shopkepper.stock.model.StockType;
import cn.orignzmn.shopkepper.stock.model.TypeTotal;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;
import cn.orignzmn.shopkepper.stock.model.vo.ProductNoVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductDgVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductTotalVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockReprotVO;

public interface StockProductDao {
	
	public List<TypeTotal> groupByType(PagVo pv);
	
	public List<StockProduct> findAll(StockProductDgVO pv);

	public List<ProductType> findAllType();


	public List<ProductSize> findAllSize();

	public void createStockProduct(StockProduct stockProduct);

	public void deleteStockProduct(Long id);

	public void updateStockProduct(StockProduct stockProduct);

	public StockProduct getByBarCode(String barCode);

	public Integer total(StockProductDgVO pv);

	public List<ProductNoVO> allProductNo();

	public List<StockInfo> getStockInfo(StockReprotVO pv);

	public DayReport dayReport(String date);

	public Long getNoId(String productNo);

	public Long getSizeId(String size);

	Integer getStockInfoTotal(StockReprotVO pv);

	public void stockOut(List<StockProductVO> list);

	public Integer groupByTypeTotal();

	Integer stockInTotal();

	List<StockIn> stockInList(PagVo pv);

	public List<StockInDetail> stockInDetailById(Long id);

	public Long saveStockIn(StockIn sin);

	public void saveStockInDetail(Long id, List<StockProductTotalVO> sptvo);

	Integer stockOutTotal();

	List<StockOut> stockOutList(PagVo pv);

	public List<StockOutDetail> stockOutDetailById(Long id);

	public Long saveStockOut(StockOut sout, Long typeId);

	void saveStockOutDetail(Long id, List<StockProductTotalVO> sptvo);

	List<StockType> allStockType();

	public List<ProductNoVO> allShortProductNo();

	public List<ProductNoVO> noTreeByShotNo(List<String> noList);

	public List<StockInfo> getStockInfoNoId(List<String> noIds);
}
