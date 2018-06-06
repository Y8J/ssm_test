package im.ky.fy.controller.webapp;

import im.ky.fy.service.serviceimpl.YjServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Value是Spring内部用来读取properties配置文件内容的注解
 * @author yangjing
 *
 */
@RequestMapping("sgp")
@Controller
public class SpringGetProAct {

	@Autowired
	YjServiceImpl yjService;

	@RequestMapping("getpro.do")
	@ResponseBody
	public String getPro(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		String X = yjService.url+"  "+yjService.userName+"  "+yjService.password;
		
		return X;
		
	}
	
	
	/**********************************以下方式不能吧properties文件的spring配置信息注入到代码里面******************/
	@Value("${url}")
	public String url;
	@Value("${userName}")
	public String userName;
	@Value("${password}")
	public String password;
	
	@RequestMapping("getpro1.do")
	@ResponseBody
	public String getPro1(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		String X = url+"  "+userName+"  "+password;
		
		return X;
		
	}
}
