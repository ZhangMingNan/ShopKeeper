package cn.orignzmn.shopkepper.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.api.model.Brand;
import cn.orignzmn.shopkepper.api.service.BrandService;
/**
 * 品牌管理
 * @author 张明楠
 */
@Controller
@RequestMapping("/api/brand")
public class BrandApiController {

@Autowired
private BrandService brandService;

    @RequestMapping(value="getData",method = RequestMethod.GET)
    @ResponseBody
    public List<Brand>  getData(Brand brand) {
    	List<Brand> list = brandService.queryAll();
    	return list;
    }
	@RequestMapping(value="save",method = RequestMethod.POST)
	public  void save(Brand brand){
		brandService.save(brand);
	}
	@RequestMapping(value="deleteById",method = RequestMethod.POST)
	public  void deleteById(Long id){
		brandService.deleteById(id);
	}
}
