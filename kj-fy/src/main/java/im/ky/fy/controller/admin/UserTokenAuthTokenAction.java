package im.ky.fy.controller.admin;

import im.ky.fy.auth.AuthToken;
import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * token注解拦截器测试实验
 * @AuthToken //token校验注解 与拦截器配置属于
 * @author yangjing
 *
 */
@RequestMapping("utata")
@Controller
public class UserTokenAuthTokenAction {

	@Autowired
	private UserService userService;
	
	//保存注册数据跳转到登录页面
	@AuthToken //token校验注解 与拦截器配置属于
	@ResponseBody
	@RequestMapping("userSave.do")
	public UserMapperBean saveUser(HttpServletRequest request, HttpServletResponse response,ModelMap model,UserMapperBean bean){
		
		return bean;
	}
		
	//保存注册数据跳转到登录页面
	@AuthToken //token校验注解 与拦截器配置属于
	@ResponseBody
	@RequestMapping("userSave.json")
	public UserMapperBean saveUserJson(HttpServletRequest request, HttpServletResponse response,ModelMap model,UserMapperBean bean){
		
		return bean;
	}
	
	//查看用户分页列表显示
	@AuthToken
	@ResponseBody
	@RequestMapping("list.do")
	public List<UserMapperBean> list(HttpServletRequest request, HttpServletResponse response,ModelMap model) throws IOException{
		String param = getPostParameter(request);
		//阿里fastjson json转化
  	    JSONObject obj = JSONObject.parseObject(param);
		//从json中获取token参数
        String token = (String) obj.get("token");
        Integer pageNo = (Integer) obj.get("pageNo");
        Integer pageSize = (Integer) obj.get("pageSize");
        //获取j'son中传递的user对象
        UserMapperBean bean = (UserMapperBean) obj.get("bean");
	     
	     
		List<UserMapperBean> pagingUserPojo = userService.pagingUserPojo(bean, pageNo, pageSize);
		return pagingUserPojo;
	}
	
	
	/**
	  * 只能用于post请求get请求会报错
	  *       java.lang.NegativeArraySizeException
	  * 根据request获取Post提交的json参数
	  * @param request
	  * @return
	  * @throws IOException
	  */
	  private static String getPostParameter(HttpServletRequest request) throws IOException{
		  BufferedInputStream buf = null;
		  int iContentLen = request.getContentLength();
		  byte sContent[] = new byte[iContentLen];
		  String sContent2 = null;
		  try {
			  buf = new BufferedInputStream(request.getInputStream());
			  buf.read(sContent, 0, sContent.length);
			  sContent2 = new String(sContent,0,iContentLen,"UTF-8");
	 
		  } catch (IOException e) {
			  throw new IOException("Parse data error!",e);
		  } finally
		  {
			  try {
				  buf.close();
			  } catch (IOException e) {
	 
			  }
		  }
		  return sContent2;
	  }
}
