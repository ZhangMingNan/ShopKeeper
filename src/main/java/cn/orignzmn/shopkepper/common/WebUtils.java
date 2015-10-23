package cn.orignzmn.shopkepper.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cn.orignzmn.shopkepper.stock.model.vo.StockProductTotalVO;
import cn.orignzmn.shopkepper.stock.model.vo.StockProductVO;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

public class WebUtils {
	public  static final Map<String, Object>  successResult;
	public  static final Map<String, Object>  exceptionResult;
	static {
		successResult = Maps.newHashMap();
		successResult.put("success",true);
		successResult.put("message","添加成功！");
		exceptionResult = Maps.newHashMap();
		exceptionResult.put("success",false);
		exceptionResult.put("message","操作异常！");
	}
	
	
	public static Integer getStart(int page,int rows){
		if(page==0) return 0;
		return (page-1)*rows;
	}

	public static  List<StockProductTotalVO>  mergeBarCode(List<StockProductVO> list) {
		List<StockProductTotalVO> totalList = Lists.newArrayList();
		//对数据进行合并
		Multimap<String, String> mmap = ArrayListMultimap.create();
		for(StockProductVO spvo:list){
			mmap.put(spvo.getProductNo(), spvo.getSize());
		}
		for(String key:mmap.keySet()){
			Collection<String> values = mmap.get(key);
			Multiset<String> mms = TreeMultiset.create();
			for(String value : values){
				mms.add(value);
			}
			Iterator<String> it = mms.elementSet().iterator();
			List<String> subList = Lists.newArrayList();
			while(it.hasNext()){
				String v = it.next();
				subList.add(v+"<sup>"+mms.count(v)+"</sup>&nbsp;&nbsp;");
			}
			StockProductTotalVO spt = new StockProductTotalVO();
			spt.setProductNo(key);
			spt.setTotal(values.size());
			spt.setSizeDetail(Joiner.on(",").join(subList));
			totalList.add(spt);
		}
		return totalList;
	}
	public static List<StockProductVO> cleanBarCodeString( String[] codeList) {
		List<StockProductVO> list = Lists.newArrayList();
		for(String code : codeList){
			code = StringUtils.upperCase(StringUtils.trim(StringUtils.chomp(code))); 
			if(StringUtils.isNotBlank(code)){
				StockProductVO spvo = new StockProductVO();
				spvo.setBarCode(code);
				spvo.setSize(StringUtils.substring(code, code.length()-2));
				spvo.setProductNo(StringUtils.substring(code, 0,code.length()-2));
				list.add(spvo);
			}
		}
		return 	list;
	}

	
	public static String shortNo(String no){
		return StringUtils.substring(no, indexOfFirst(no)+1, StringUtils.indexOf(no, "-"));
	}
	
	
	//用于分切条形码中的货号。
	public static int indexOfFirst(String str){ 
		Pattern p = Pattern.compile("[A-Z]");
		char[] chars = str.toCharArray();
		for(int i=0;i<chars.length;i++){
			if(p.matcher(String.valueOf(chars[i])).matches()){
				return i;
			}
		}
		return -1;
	}
}
