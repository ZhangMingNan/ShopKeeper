package cn.orignzmn.shopkepper.stock.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import cn.orignzmn.shopkepper.api.model.DayReport;
import cn.orignzmn.shopkepper.api.model.StockInfo;
import cn.orignzmn.shopkepper.common.WebUtils;
import cn.orignzmn.shopkepper.stock.dao.PriceAdjustDao;
import cn.orignzmn.shopkepper.stock.dao.ProductTypeDao;
import cn.orignzmn.shopkepper.stock.dao.StockProductDao;
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
import cn.orignzmn.shopkepper.stock.service.StockService;
@Service
public class StockServiceImpl implements StockService{
	
	@Autowired
	private StockProductDao stockProductDao;

	@Autowired
	private PriceAdjustDao priceAdjustDao;
	
	@Autowired
	private ProductTypeDao  productTypeDao;
	
	@Override
	public List<StockProduct> findAll(StockProductDgVO pv) {
		// TODO Auto-generated method stub
		return stockProductDao.findAll(pv);
	}

	@Override
	public List<ProductType> findAllType() {
		// TODO Auto-generated method stub
		return stockProductDao.findAllType();
	}


	@Override
	public List<ProductSize> findAllSize() {
		// TODO Auto-generated method stub
		return stockProductDao.findAllSize();
	}

	@Override
	public void createStockProduct(StockProduct stockProduct) {
		// TODO Auto-generated method stub
		stockProductDao.createStockProduct(stockProduct);
	}

	@Override
	public void deleteStockProduct(Long id) {
		// TODO Auto-generated method stub
		stockProductDao.deleteStockProduct(id);
	}

	@Override
	public void updateStockProduct(StockProduct stockProduct) {
		// TODO Auto-generated method stub
		stockProductDao.updateStockProduct(stockProduct);
	}

	@Override
	public StockProduct getByBarCode(String barCode) {
		// TODO Auto-generated method stub
		return stockProductDao.getByBarCode(barCode);
	}

	@Override
	public Integer total(StockProductDgVO pv) {
		// TODO Auto-generated method stub
		return stockProductDao.total(pv);
	}

	@Override
	public List<ProductNoVO> allProductNo() {
		// TODO Auto-generated method stub
		return stockProductDao.allProductNo();
	}

	@Override
	public List<StockInfo> getStockInfo(StockReprotVO pv) {
		
		// TODO Auto-generated method stub
		return stockProductDao.getStockInfo(pv);
	}
	@Override
	public Integer getStockInfoTotal(StockReprotVO pv){
		return stockProductDao.getStockInfoTotal( pv);
	}

	@Override
	public DayReport dayReport(String date) {
		// TODO Auto-generated method stub
		return stockProductDao.dayReport(date);
	}

	@Override
	public void bathSaveProduct(List<StockProductTotalVO> sptvo,List<StockProductVO> list,Long typeId) {
		// TODO Auto-generated method stub
		//生成入库记录
		StockIn sin = new StockIn();
		sin.setTotal(list.size());
		sin.setTypeId(typeId);
		Long id = stockProductDao.saveStockIn(sin);
		//生成入库明细记录
		stockProductDao.saveStockInDetail(id,sptvo);
    	for(StockProductVO spvo:list){
    		String barCode = spvo.getBarCode();
    		//根据货号获取货号id
    		Long noId = stockProductDao.getNoId(spvo.getProductNo());
    		Long sizeId = stockProductDao.getSizeId(spvo.getSize());
    		StockProduct sp = new StockProduct();
    		sp.setBarCode(barCode);
  
    		ProductNoVO pnvo = new ProductNoVO();
    		pnvo.setId(noId);
    		sp.setProductNo(pnvo);
    		
    		ProductSize ps = new ProductSize();
    		ps.setId(sizeId);
    		sp.setProductSize(ps);
    		
    		ProductType pt = new ProductType();
    		pt.setId(typeId);
    		sp.setProductType(pt);
    		stockProductDao.createStockProduct(sp);
    	}
	}
	
	//根据传入的货号判断这个货号是否存在 如果不存在则插入一条新的货号记录， 进价，售价，利润均是0
	//如果出入的货号存在则直接返回售价short_no
	@Override
	public String saveProductNoIfNull(Long type,String key) {
		// TODO Auto-generated method stub
		//根据货号查询是否存在。
		String sale = priceAdjustDao.saleByNo(key);
		if(sale ==null){
			//不存在
			priceAdjustDao.saveNewNo(type,key);
			return "";
		}
		return sale;
	}
	@Override
	public void stockOut(Long typeId,List<StockProductVO> list) {
		// TODO Auto-generated method stub
		//创建出库记录。
		StockOut sout = new StockOut();
		sout.setTotal(list.size());
		List<StockProductTotalVO> sptvo = WebUtils.mergeBarCode(list);
		//创建出库明细记录。
		Long id = stockProductDao.saveStockOut(sout,typeId);
		stockProductDao.saveStockOutDetail(id, sptvo);
		stockProductDao.stockOut(list);
	}
	
	@Override
	public List<StockType> allStockOutType(){
		return stockProductDao.allStockType();
	}

	@Override
	public List<TypeTotal> groupByType(PagVo pv) {
		// TODO Auto-generated method stub
		return stockProductDao.groupByType(pv);
	}

	@Override
	public Integer groupByTypeTotal() {
		// TODO Auto-generated method stub
		return stockProductDao.groupByTypeTotal();
	}

	@Override
	public Integer stockInTotal() {
		// TODO Auto-generated method stub
		return stockProductDao.stockInTotal();
	}

	@Override
	public List<StockIn> stockInList(PagVo pv) {
		// TODO Auto-generated method stub
		return stockProductDao.stockInList(pv);
	}

	@Override
	public List<StockInDetail> stockInDetailById(Long id) {
		// TODO Auto-generated method stub
		return stockProductDao.stockInDetailById(id);
	}

	@Override
	public Integer stockOutTotal() {
		// TODO Auto-generated method stub
		return stockProductDao.stockOutTotal();
	}

	@Override
	public List<StockOut> stockOutList(PagVo pv) {
		// TODO Auto-generated method stub
		return stockProductDao.stockOutList(pv);
	}

	@Override
	public List<StockOutDetail> stockOutDetailById(Long id) {
		// TODO Auto-generated method stub
		return stockProductDao.stockOutDetailById(id);
	}

	@Override
	public void saveProductType(ProductType productType) {
		// TODO Auto-generated method stub
		productTypeDao.saveProductType(productType);
	}

	@Override
	public List<ProductNoVO> allShortProductNo() {
		// TODO Auto-generated method stub
		return stockProductDao.allShortProductNo();
	}

	@Override
	public List<Tree> noTreeByShotNo(List<String> noList) {
		// TODO Auto-generated method stub
		List<Tree> list = Lists.newArrayList();
		List<ProductNoVO> plist = stockProductDao.noTreeByShotNo(noList);
		Multimap<String, ProductNoVO> noMultiMap = ArrayListMultimap.create();  
		for(ProductNoVO pv:plist){
			noMultiMap.put(pv.getShortNo(),pv);
		}
		Iterator<String> mapIt = 	noMultiMap.keySet().iterator();
		while(mapIt.hasNext()){
			String key = mapIt.next();
			Tree tree = new Tree(0L, key, "open", false);
			for(ProductNoVO pn: noMultiMap.get(key)){
				Tree childrenTree =  new Tree(pn.getId(), pn.getNo(), "open", false);
				tree.getChildren().add(childrenTree);
			}
			list.add(tree);
		}
		return list;
	}

	@Override
	public List<StockInfo> getStockInfoNoId(List<String> noIds) {
		// TODO Auto-generated method stub
		return stockProductDao.getStockInfoNoId(noIds);
	}
}
