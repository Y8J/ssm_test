package im.ky.fy.controller.admin;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.util.Md5Util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("usa")
@Controller
public class UserShiroAct {

	 private static Logger logger = LoggerFactory.getLogger(UserShiroAct.class);
	 
	//跳转到登录页面
	@RequestMapping("login.html")
	public String loginHtml(HttpServletRequest request, HttpServletResponse response,ModelMap model,String userName,String password){
		
		return "shiroUser/login";
	}
	
	@RequestMapping("login.do")
    public String login(UserMapperBean user,Model model) throws Exception{
		user.setUserPwd(Md5Util.md5(user.getUserPwd()));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),user.getUserPwd());
        try {
            subject.login(usernamePasswordToken);
            logger.info("======登陆成功=======");
            return "shiroUser/success";
        } catch (Exception e) {
            logger.error("======登陆异常=======");
            //model.addAttribute("msg", "用户名或者密码错误,登陆失败");
            e.printStackTrace();
            return "shiroUser/login";
        }
    }
	
	/**
	* 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> logout(){
	    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	    try {
	        //退出
	        SecurityUtils.getSubject().logout();
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	    return resultMap;
	}
	
	//用户权限不足跳转页面
	@RequestMapping("nopermissions.html")
	public String nopermissions(HttpServletRequest request, HttpServletResponse response,ModelMap model,String userName,String password){
		model.addAttribute("msg", "对不起，您没有权限访问！");
		return "shiroUser/nopermissions";
	}
	
}
