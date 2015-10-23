package cn.orignzmn.shopkepper.weixin.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jodd.util.ReflectUtil;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import us.codecraft.xsoup.Xsoup;
import cn.orignzmn.shopkepper.weixin.model.Validation;
import cn.orignzmn.shopkepper.weixin.service.MemberScoreService;

import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/wx")
public class WeiXinController {

	@Autowired
	ApiConfig apiConfig;
	
	//新品橱窗页面
	@RequestMapping(value="showcase")
	public String showcase(){
		return "wx_showcase";
	}
	
	
	@RequestMapping(value="barCode")
	public String barCode(){
		return "wx_barcode";
	}
	
	@Autowired
	private MemberScoreService memberScoreService;
	private static final String TOKEN = "2w3e4r5t";
	@RequestMapping(value="message",method = RequestMethod.GET)
	@ResponseBody
	public Object  valiGet(Validation vali)  {
		List<String> tempArr = Lists.newArrayList(TOKEN,vali.getTimestamp(),vali.getNonce());
		Collections.sort(tempArr);
		String tempStr = Joiner.on("").join(tempArr);
		tempStr = Hex.encodeHexString(DigestUtils.sha1(tempStr));
		if(vali.getSignature().equals(tempStr)){
			return vali.getEchostr();
		}
		return "";
	}

	
	@RequestMapping(value="cc")
	public @ResponseBody String cc(){
		String openid = "oVQ7qjhGEzoqEacwxDBJLHFlBeuo";
		UserAPI userAPI = new UserAPI(apiConfig);
		GetUserInfoResponse userInfo = userAPI.getUserInfo(openid);
		//获取用户信息后保存。
		long id = memberScoreService.saveMember(userInfo);
		return "11";
	}
	
	@RequestMapping(value="message",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public @ResponseBody String  message(Model model) throws Exception {
		HashMap<String,Object> map = Maps.newHashMap(model.asMap());
		String event = map.get("event").toString();
		String openid = map.get("fromusername").toString();
		if("unsubscribe".equals(event)){
			//用户取消关注
		}else if("subscribe".equals(event)){
			//用户关注,获取用户信息，保存到数据库中
			UserAPI userAPI = new UserAPI(apiConfig);
			GetUserInfoResponse userInfo = userAPI.getUserInfo(openid);
			//获取用户信息后保存。
			long id = memberScoreService.saveMember(userInfo);
		}
		
		String result  = "";
		String mname = 	map.get("eventkey").toString();
		if(StringUtils.isNotEmpty(mname)){
		try {
			 result = ReflectUtil.invoke(memberScoreService, mname,map).toString();
			} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		}
		return result;
	}

	@ModelAttribute
	public void header(@RequestBody String mess,Model model){
		Document doc=Jsoup.parse(mess, "", Parser.xmlParser());
		List<Element> proList = Xsoup.compile("/xml/*").evaluate(doc).getElements();
		for(Element ele :proList){
			model.addAttribute(ele.tagName(), ele.text());
		}
	}
	
}
