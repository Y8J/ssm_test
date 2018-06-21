package im.ky.fy.controller.admin;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;
import im.ky.fy.util.poi.ExportUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="exporutils")
@Controller
public class ExportUtilsAction {


	@Autowired
	private UserService userService;

	//跳转到注册页面
	@RequestMapping("daochu.do")
	public void goAddUserHtml(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		List<UserMapperBean> beanList = userService.queryUserMapperBean(null);
		String[] head = new String[] { "编号", "用户名", "密码", "状态", "邮箱", "手机号码"};
		
		 List<List<String>> list2 = new ArrayList<List<String>>();
	        if (beanList != null && beanList.size() > 0) {
	            for (int j = 0; j < beanList.size(); j++) {
	                List<String> arr = new ArrayList<String>();
	                UserMapperBean o = beanList.get(j);
	                arr.add(o.getId()+ ""); // id
	                arr.add(o.getUserName()!=null?o.getUserName():"---"); // 用户名
	                arr.add(o.getUserPwd() == null ? "---" : o.getUserPwd()); // 密码
	                arr.add(o.getStatus()+""); // 状态
	                arr.add(o.getEmail()); // 邮箱
	                arr.add(o.getEmail()); // 手机
	                list2.add(arr);
	            }
	        } else {
	            List<String> arr = new ArrayList<String>();
	            arr.add("找不到相关用户数据！");
	            list2.add(arr);
	        }
	        try {
	                ExportUtil.exportExcelNew("用户数据列表.xls", request, response, head, list2);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
