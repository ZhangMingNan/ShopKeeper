package cn.orignzmn.shopkepper.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.orignzmn.shopkepper.api.model.Member;
import cn.orignzmn.shopkepper.api.model.MemberQueryParams;
import cn.orignzmn.shopkepper.api.service.MemberService;

/**
 * 移动端用户相关。
 * @author 张明楠
 */
@Controller
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
    @RequestMapping(value="members",method = RequestMethod.GET)
    @ResponseBody
    public List<Member> members(MemberQueryParams params){
    	//根据等级条件查看用户。
    	List<Member> list = memberService.query(params);
    	return list;
    }
	
}
