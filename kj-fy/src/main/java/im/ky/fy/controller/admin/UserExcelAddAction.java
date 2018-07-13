package im.ky.fy.controller.admin;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.excel.UserExcelAddService;
import im.ky.fy.util.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UserExcelAddAction {

	@Autowired
	UserExcelAddService userExcelAddService;
	
	
	/**
	 * 批量导入
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userExcel.html")
	public String excelAddHtml(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		return "user/upload";
	}
	
	/**
	 * 批量导入
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws JSONException 
	 * 注意 返回json中文乱码可以使用如下代码解决
	 *  @RequestMapping(value = "testPersonalValidtor.do",produces = "application/json;charset=utf-8")
	 */
	@ResponseBody
	@RequestMapping(value = "o_excel_user_add.do",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String excelAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject ajaxResult = new JSONObject();
		MultipartHttpServletRequest mhsRequest= (MultipartHttpServletRequest)request;
		MultipartFile excel=mhsRequest.getFile("excel");
		if (excel.isEmpty()) {
			ajaxResult.put("msg","请选择要导入的Excel文件！");
			model.addAttribute("ajaxResult", ajaxResult);
			return ajaxResult.toString();
		}
		try {
			ajaxResult=userExcelAddService.excelSave(excel, 1, 2, 1, ajaxResult);
			String json = ajaxResult.toString();
			return json;
		} catch (Exception e) {
			ajaxResult.put("msg","导入处理发生未知异常，请稍后再试！");
			return ajaxResult.toString();
		}
	}
}
