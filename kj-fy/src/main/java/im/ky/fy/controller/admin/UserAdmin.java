package im.ky.fy.controller.admin;

import im.ky.fy.auth.AuthToken;
import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user/*")
@Controller
public class UserAdmin {
	
	@Autowired
	private UserService userService;

	//跳转到注册页面
	@RequestMapping("add.html")
	public String goAddUserHtml(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		
		return "user/registered";
	}
	
	//保存注册数据跳转到登录页面
	@AuthToken //token校验注解 与拦截器配置属于
	@RequestMapping("userSave.do")
	public String saveUser(HttpServletRequest request, HttpServletResponse response,ModelMap model,UserMapperBean bean){
		
		userService.save(bean);
		model.addAttribute("msg", "恭喜你注册成功！");
		return "user/success";
	}
	
	
	//跳转到登录页面
	@RequestMapping("login.html")
	public String loginHtml(HttpServletRequest request, HttpServletResponse response,ModelMap model,String userName,String password){
		
		return "user/login";
	}
	
	//登录数据保存
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, HttpServletResponse response,ModelMap model,String userName,String password){
		UserMapperBean bean = new UserMapperBean();
		bean.setUserName(userName);
		bean.setUserPwd(password);
		
		List<UserMapperBean> queryUserMapperBean = userService.queryUserMapperBean(bean);
		if(queryUserMapperBean.isEmpty()){
			model.addAttribute("msg", "找不到此用户请检查用户名密码");
			return "user/error";
		}
		//将登录数据保存在session中
		HttpSession session = request.getSession();
		session.setAttribute("usersession", queryUserMapperBean.get(0));
		model.addAttribute("msg", "恭喜你登录成功！");
		return "user/success";
	}
	
	
	//查看用户分页列表显示
	@ResponseBody
	@RequestMapping("list.do")
	public List<UserMapperBean> list(HttpServletRequest request, HttpServletResponse response,ModelMap model,UserMapperBean bean,Integer pageNo,Integer pageSize){
		List<UserMapperBean> pagingUserPojo = userService.pagingUserPojo(bean, pageNo, pageSize);
		return pagingUserPojo;
	}
	
	
}
