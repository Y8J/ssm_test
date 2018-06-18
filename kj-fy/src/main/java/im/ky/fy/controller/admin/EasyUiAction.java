package im.ky.fy.controller.admin;

import java.util.List;

import im.ky.fy.pojo.PageBean;
import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;
import im.ky.fy.util.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value="easyui")
@Controller
public class EasyUiAction {

	@Autowired
	private UserService userService;
	
	//跳转到注册页面
	@RequestMapping("tablejson.html")
	public String goAddUserHtml(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		
		return "easy/tablejson";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param page  页面
	 * @param rows  数量
	 * @return
	 * @throws JSONException 
	 */
	@ResponseBody
	@RequestMapping("userjson.do")
	public PageBean<UserMapperBean> userJson(HttpServletRequest request, HttpServletResponse response,ModelMap model,Integer page,Integer rows) throws JSONException{
		UserMapperBean bean = new UserMapperBean();
		
		JSONObject ajaxResult = new JSONObject();
		List<UserMapperBean> userList = userService.pagingUserPojo(bean, page, rows);
		
		List<UserMapperBean> userListNumber = userService.queryUserMapperBean(null);
		
		/*JSONArray listj = new JSONArray();
		
		JSONObject entity = new JSONObject();
		//获取总记录条数
		entity.put("total",userListNumber.size());
		//获得数据体
		entity.put("rows", userList);
			
		listj.put(entity);
		
	    ajaxResult.put("treeData", listj);*/
	    
	    PageBean<UserMapperBean> page1 = new PageBean<UserMapperBean>();
	    page1.setTotal(userListNumber.size());
	    page1.setRows(userList);
        
	    return page1;
		//ResponseUtils.renderJson(response, entity.toString());
		
	}
		
		
}
