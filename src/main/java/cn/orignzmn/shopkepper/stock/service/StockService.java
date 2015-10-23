package cn.orignzmn.shopkepper.stock.service;

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
import cn.orignzmn.shopkepper.stock.model.Tree;
import cn.orignzmn.shopkepper.stock.model.TypeTotal;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;
import cn.orignzmn.shopkepper.stock.model.vo.ProductNoVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductDgVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductTotalVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockReprotVO;

public interface StockService {
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
	public Integer getStockInfoTotal(StockReprotVO pv);
	public DayReport dayReport(String date);
	public void bathSaveProduct(List<StockProductTotalVO> sptvo, List<StockProductVO> list, Long typeId);
	public void stockOut(Long typeId, List<StockProductVO> list);
	public Integer groupByTypeTotal();
	
	public Integer stockInTotal();
	public List<StockIn> stockInList(PagVo pv);
	public Integer stockOutTotal();
	public List<StockOut> stockOutList(PagVo pv);
	public List<StockInDetail> stockInDetailById(Long id);
	public List<StockOutDetail> stockOutDetailById(Long id);
	List<StockType> allStockOutType();
	public String saveProductNoIfNull(Long type, String productNo);
	public void saveProductType(ProductType productType);
	public List<ProductNoVO> allShortProductNo();
	public List<Tree> noTreeByShotNo(List<String> noList);
	public List<StockInfo> getStockInfoNoId(List<String> noIds);
}
