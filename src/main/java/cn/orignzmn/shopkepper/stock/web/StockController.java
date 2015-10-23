package cn.orignzmn.shopkepper.stock.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.api.model.StockInfo;
import cn.orignzmn.shopkepper.common.WebUtils;
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
import cn.orignzmn.shopkepper.stock.model.vo.CombotBoxVO;
import cn.orignzmn.shopkepper.stock.model.vo.DataGridVo;
import cn.orignzmn.shopkepper.stock.model.vo.NewSellingPriceVO;
import cn.orignzmn.shopkepper.stock.model.vo.PagVo;
import cn.orignzmn.shopkepper.stock.model.vo.ProductGroup;
import cn.orignzmn.shopkepper.stock.model.vo.ProductNoVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductDgVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductTotalVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockReprotVO;
import cn.orignzmn.shopkepper.stock.service.PriceAdjustService;
import cn.orignzmn.shopkepper.stock.service.StockService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 库存管理
 * @author 张明楠
 */
@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;
	@Autowired
	private PriceAdjustService priceAdjustService;


	//根据短货号获取货号明细树.
	@RequestMapping(value="noTree",method = RequestMethod.POST)
	@ResponseBody
	public List<Tree>  noTree(@RequestParam(value = "noList[]") List<String> noList) {
		 List<Tree> list = stockService.noTreeByShotNo(noList);
		return list;
	}
	
	
	//根据货号ID获取库存明细.
	@RequestMapping(value="noDetail",method = RequestMethod.POST)
	@ResponseBody
	public List<StockInfo>  noDetail(@RequestParam(value = "noIds[]") List<String> noIds) {
		List<StockInfo> list = 	stockService.getStockInfoNoId(noIds);
		System.out.println(list);
		return list;
	}


	@RequestMapping(value = "stockIn", method = RequestMethod.GET)
	public String stockIn(Model model) {
		return "stock_in";
	}

	@RequestMapping(value="stockInData",method = RequestMethod.POST)
	@ResponseBody
	public DataGridVo<StockIn>  stockInData(PagVo pv) {
		List<StockIn> list = stockService.stockInList(pv);
		Integer totla =  stockService.stockInTotal();
		DataGridVo<StockIn> gridDatas = new DataGridVo<StockIn>(totla,list);
		return gridDatas;
	}


	@RequestMapping(value="stockOutData",method = RequestMethod.POST)
	@ResponseBody
	public DataGridVo<StockOut>  stockOutData(PagVo pv) {
		List<StockOut> list = stockService.stockOutList(pv);
		Integer totla =  stockService.stockOutTotal();
		DataGridVo<StockOut> gridDatas = new DataGridVo<StockOut>(totla,list);
		return gridDatas;
	}

	@RequestMapping(value="stockOutDetailData",method = RequestMethod.POST)
	@ResponseBody
	public List<StockOutDetail>  stockOutDetailData(Long id) {
		List<StockOutDetail> list = stockService.stockOutDetailById(id);
		return list;
	}

	@RequestMapping(value="stockInDetailData",method = RequestMethod.POST)
	@ResponseBody
	public List<StockInDetail>  stockInDetailData(Long id) {
		List<StockInDetail> list = stockService.stockInDetailById(id);
		return list;
	}

	@RequestMapping(value = "stockOut", method = RequestMethod.GET)
	public String stockOut(Model model) {
		return "stock_out";
	}


	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		return "stock_list";
	}

	
	//库存明细
	@RequestMapping(value="reportData",method = RequestMethod.GET)
	public  @ResponseBody  DataGridVo<StockInfo> reportData(StockReprotVO pv){
		List<StockInfo> list = 	stockService.getStockInfo(pv);
		
		DataGridVo<StockInfo> gridDatas = new DataGridVo<StockInfo>(stockService.getStockInfoTotal( pv), list);
		return gridDatas;
	}


	@RequestMapping(value = "stockReport", method = RequestMethod.GET)
	public String stockReport(Model model) {
		return "stock_report";
	}

	@RequestMapping(value="getShortProductNoData",method = RequestMethod.GET)
	@ResponseBody
	public List<CombotBoxVO>getShortProductNoData(StockProductDgVO pv) {
		List<ProductNoVO> productNoList = stockService.allShortProductNo();
		List<CombotBoxVO> productNoVoList = Lists.newArrayList();
		for(ProductNoVO no:productNoList){
			productNoVoList.add(new CombotBoxVO(no.getShortNo(),no.getShortNo(),no.getShortNo()));
		}
		return productNoVoList;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(StockProduct stockProduct) {
		stockService.createStockProduct(stockProduct);
		return WebUtils.successResult;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(StockProduct stockProduct) {
		stockService.updateStockProduct(stockProduct);
		return WebUtils.successResult;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  delete(@PathVariable("id") Long id) {
		stockService.deleteStockProduct(id);
		return WebUtils.successResult;
	}

	@RequestMapping(value="getProductNoData",method = RequestMethod.POST)
	@ResponseBody
	public List<CombotBoxVO>   getProductNoData(StockProductDgVO pv) {
		List<ProductNoVO> productNoList = stockService.allProductNo();
		List<CombotBoxVO> productNoVoList = Lists.newArrayList(new CombotBoxVO("","全部", "全部"));
		for(ProductNoVO no:productNoList){
			productNoVoList.add(new CombotBoxVO(no.getId()+"", no.getNo(),no.getNo()));
		}
		return productNoVoList;
	}
	
	@RequestMapping(value="getProductNoComboboxData",method = RequestMethod.GET)
	@ResponseBody
	public List<CombotBoxVO>   getProductNoComboboxData(StockProductDgVO pv) {
		List<ProductNoVO> productNoList = stockService.allProductNo();
		List<CombotBoxVO> productNoVoList = Lists.newArrayList();
		for(ProductNoVO pnvo:productNoList){
			productNoVoList.add(new CombotBoxVO(pnvo.getId()+"",pnvo.getNo(),pnvo.getNo()));
		}
		return productNoVoList;
	}

	@RequestMapping(value="getStockOutTypeComboboxData",method = RequestMethod.GET)
	@ResponseBody
	public List<CombotBoxVO>   getStockOutTypeComboboxData() {
		List<StockType> list = stockService.allStockOutType();
		List<CombotBoxVO> boxVoList = Lists.newArrayList();
		for(StockType st:list){
			CombotBoxVO sbvo = new CombotBoxVO(st.getId()+"",st.getType(),st.getType());
			boxVoList.add(sbvo);
		}
		if (boxVoList!=null&&boxVoList.size()>0){
			boxVoList.get(0).setSelected(true);
		}
		return boxVoList;
	}


	@RequestMapping(value = "/updateSellingPrice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSellingPrice( NewSellingPriceVO  newSellingPriceVO) {
		priceAdjustService.updatePriceByProductNo(newSellingPriceVO);
		return WebUtils.successResult;
	}
	//批量入商品。
	@RequestMapping(value = "/saveProductList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveProductList( ProductGroup group) {
		String[] codeList  = StringUtils.split(StringUtils.trimToEmpty(group.getCodeList()),"\r\n");
		List<StockProductVO> list  = WebUtils.cleanBarCodeString(codeList);

		List<StockProductTotalVO> sptvo = WebUtils.mergeBarCode(list);

		stockService.bathSaveProduct(sptvo,list,group.getTypeId());
		return WebUtils.successResult;
	}


	//根据条形码拆分出货号和大小
	@RequestMapping(value="saveStockOut",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  saveStockOut(@RequestParam(defaultValue="") String barCode,Long typeId) {
		//List<StockProductTotalVO> totalList = Lists.newArrayList();
		//传入条码列表
		String[] codeList  = StringUtils.split(StringUtils.trimToEmpty(barCode),"\r\n");
		List<StockProductVO> list  = WebUtils.cleanBarCodeString(codeList);
		stockService.stockOut(typeId,list);
		return WebUtils.successResult;
	}

	//根据条形码拆分出货号和大小
	@RequestMapping(value="splitBarCode",method = RequestMethod.POST)
	@ResponseBody
	public DataGridVo<StockProductTotalVO>  splitBarCode(Long type,@RequestParam(defaultValue="") String barCode,
			@RequestParam(defaultValue="false")  boolean isStockIn) {
		DataGridVo<StockProductTotalVO> gridDatas = barCodeDetail(type,barCode,isStockIn);
		return gridDatas;
	}

	@SuppressWarnings("unchecked")
	private DataGridVo<StockProductTotalVO> barCodeDetail(Long type,String barCode, boolean isStockIn) {

		//传入条码列表
		String[] codeList  = StringUtils.split(StringUtils.trimToEmpty(barCode),"\r\n");
		List<StockProductVO> list  = WebUtils.cleanBarCodeString(codeList);
		if(isStockIn){
			for(StockProductVO spvo:list){
				//检查是否已存在货号
				stockService.saveProductNoIfNull(type,StringUtils.upperCase(spvo.getProductNo()));
			}
		}
		List<StockProductTotalVO> totalList = WebUtils.mergeBarCode(list);
		DataGridVo<StockProductTotalVO> gridDatas = new DataGridVo<>(totalList.size(), totalList);
		Map<String,String> footerMap = Maps.newHashMap();
		footerMap.put("sale", "总量:"+list.size());
		gridDatas.setFooter(Lists.newArrayList(footerMap));
//		System.out.println(list.size());
//		for(StockProductTotalVO sptv:totalList){
//			System.out.println(sptv.getProductNo()+"##"+sptv.getTotal());
//		}
		return gridDatas;
	}


	@RequestMapping(value="getByBarCode",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getByBarCode(@RequestParam(defaultValue="") String barCode) {
		StockProduct product = null;
		Map<String,Object> result = Maps.newHashMap();
		if(StringUtils.isNotEmpty(barCode)){
			product = stockService.getByBarCode(barCode);
		}
		if(product==null){
			result.put("error",true);
			return result;
		}

		StockProductVO spvo = new StockProductVO(product);
		result.put("vo", spvo);
		return result;
	}
	/**
	 * @param isContainsAll ， 是否包含全部选项。
	 * @return 返回商品类型信息
	 */
	@RequestMapping(value="getTypeData",method = RequestMethod.GET)
	@ResponseBody
	public List<CombotBoxVO>  getTypeData(@RequestParam(defaultValue="true") boolean isContainsAll) {
		List<ProductType> typeList = stockService.findAllType();
		List<CombotBoxVO> voList = Lists.newArrayList();
		//判断是否需要
		if(isContainsAll){
			voList.add(new CombotBoxVO("","全部", "全部"));
		}
		for(ProductType type:typeList){
			voList.add(new CombotBoxVO(String.valueOf(type.getId()), type.getType(), type.getType()));
		}
		return voList;
	}


	@RequestMapping(value="getProductSizeData",method = RequestMethod.GET)
	@ResponseBody
	public List<CombotBoxVO>  getProductSizeData() {
		List<ProductSize> sizeList = stockService.findAllSize();
		List<CombotBoxVO> voList = Lists.newArrayList();
		for(ProductSize size:sizeList){
			voList.add(new CombotBoxVO(String.valueOf(size.getId()), size.getSize(), size.getSize()));
		}
		return voList;
	}

	@RequestMapping(value="getData",method = RequestMethod.POST)
	@ResponseBody
	public DataGridVo<StockProductVO>  getData(StockProductDgVO pv) {
		List<StockProduct> list = stockService.findAll(pv);
		List<StockProductVO> voList = Lists.newArrayList();
		for(StockProduct pro:list){
			voList.add(new StockProductVO(pro));
		}
		Integer total = stockService.total(pv);
		return new DataGridVo<StockProductVO>(total,voList);
	}



	@RequestMapping(value = "typeList", method = RequestMethod.GET)
	public String typeList(Model model) {
		return "type_total";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="typeTotal",method = RequestMethod.POST)
	@ResponseBody
	public DataGridVo<TypeTotal>  typeTotal(PagVo pv) {
		List<TypeTotal> list = stockService.groupByType(pv);
		Integer totla =  stockService.groupByTypeTotal();

		DataGridVo<TypeTotal> gridDatas = new DataGridVo<TypeTotal>(totla,list);
		Map<String,String> footerMap = Maps.newHashMap();
		int t = 0;
		for(TypeTotal tt:list){
			t+=tt.getTotal();
		}
		footerMap.put("total", "总库存量:"+t);
		gridDatas.setFooter(Lists.newArrayList(footerMap));
		return gridDatas;
	}

}
