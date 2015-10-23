package cn.orignzmn.shopkepper.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public HttpSession getSession() {
		return this.getRequest().getSession(true);
	}


	/**
	 * 获取工程根目录的绝对路径
	 * @return
	 */
	public String getSystemPhysicalPath(){
		return this.getSession().getServletContext().getRealPath("/");
	}


	@ModelAttribute
	public void setResponseRequest(HttpServletRequest request,HttpServletResponse response){		
		this.request = request;
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "BaseController [request=" + request + "]";
	}
}
