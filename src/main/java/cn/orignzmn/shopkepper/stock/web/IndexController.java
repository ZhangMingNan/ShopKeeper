package cn.orignzmn.shopkepper.stock.web;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.common.Constants;
import cn.orignzmn.shopkepper.stock.model.Resource;
import cn.orignzmn.shopkepper.stock.model.Tree;
import cn.orignzmn.shopkepper.stock.model.TreeAttributes;
import cn.orignzmn.shopkepper.stock.model.vo.MenusVO;
import cn.orignzmn.shopkepper.stock.service.ResourceService;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

@Controller
public class IndexController {

	@Autowired
	ResourceService  resourceService;


	@RequestMapping("/menuTree")
	@ResponseBody
	public List<Tree> menuTree(){

		List<Tree> groupList  = Lists.newArrayList();
		List<Resource> menus = resourceService.findMenus();
		Multimap<Long, Resource> groupMap = ArrayListMultimap.create();
		for(Resource res:menus){
			groupMap.put(res.getParentId(), res);
		}
		Iterator<Long> it = groupMap.keySet().iterator();
		while(it.hasNext()){
			Long id = it.next();
			Resource res  = resourceService.findOne(id);
			if(!res.getParentId().equals(Constants.rootResourceParentId)){
				Collection<Resource> subResource = 	groupMap.get(res.getId());
				Tree groupTree = new Tree(res.getId(), res.getName(), "open", true);
				groupTree.setIconCls(res.getIcon());
				for(Resource r : subResource){
					Tree t = new Tree(r.getId(), r.getName(), "close", false);
					t.setAttributes(new TreeAttributes(r.getUrl()));
					t.setIconCls(r.getIcon());
					groupTree.getChildren().add(t);
				}
		
				groupList.add(groupTree);
			}
		}
		return groupList;
	}

	@RequestMapping("/")
	public String index(Model model) {    	
		List<MenusVO> list = Lists.newArrayList();
		List<Resource> menus = resourceService.findMenus();
		Multimap<Long, Resource> groupMap = ArrayListMultimap.create();
		for(Resource res:menus){
			groupMap.put(res.getParentId(), res);
		}
		Iterator<Long> it = groupMap.keySet().iterator();
		while(it.hasNext()){
			Long id = it.next();
			Resource res  = resourceService.findOne(id);
			if(!res.getParentId().equals(Constants.rootResourceParentId)){
				list.add(new MenusVO(res.getName(), groupMap.get(res.getId())));
			}
		}
		model.addAttribute("list", list);
		return "index";
	}
}
