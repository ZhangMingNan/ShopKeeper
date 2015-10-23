package cn.orignzmn.shopkepper.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.orignzmn.shopkepper.common.FileUtil;
import cn.orignzmn.shopkepper.stock.model.vo.PriceAdjustVO;
import cn.orignzmn.shopkepper.stock.service.PriceAdjustService;

@Controller
@RequestMapping("/api/productNo")
public class ProductNoController {

	@Autowired
	PriceAdjustService adjustService;
	//添加新的货号
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public void  save(PriceAdjustVO pvo,@RequestParam  MultipartFile file) throws FileNotFoundException, IOException{
		//上传图片到七牛存储。
		if(file!=null){
			String fileName = FileUtil.getUuidFileName(file);
			FileUtil.updataToQiNiu(file.getInputStream(), fileName);
		    pvo.setProductIconUrl(fileName);
		}
		adjustService.save(pvo);
	}
}