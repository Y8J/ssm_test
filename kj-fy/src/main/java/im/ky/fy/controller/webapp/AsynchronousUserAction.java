package im.ky.fy.controller.webapp;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异步用户接口
 * @author yangjing
 *
 */
@RequestMapping("aua")
@Controller
public class AsynchronousUserAction {

	@Autowired
	private UserService userService;
	
	 
	  
	//查看用户分页列表显示
		@ResponseBody
		@RequestMapping("list.do")
		public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, final UserMapperBean bean, final Integer pageNo, final Integer pageSize){
			
			
			//在新线程中调用耗时操作 返回后在执行此段异步代码
			  new Thread(){  
			   public void run(){  
				   List<UserMapperBean> pagingUserPojo = userService.pagingUserPojo(bean, pageNo, pageSize);
				   System.out.println("java异步接口处理:"+pagingUserPojo.toString());
			   }  
			  }.start(); 
			  
			  //先执行返回
			  return "success!!!";
		}
}
