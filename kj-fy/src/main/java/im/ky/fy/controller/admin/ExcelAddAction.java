package im.ky.fy.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class ExcelAddAction {

	
	/**
	 * 批量导入
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "o_excel_add.do", method = RequestMethod.POST)
	public String excelAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		AjaxResult<AutoShowTicket> ajaxResult = new AjaxResult<AutoShowTicket>();
		MultipartHttpServletRequest mhsRequest= (MultipartHttpServletRequest)request;
		MultipartFile excel=mhsRequest.getFile("excel");
		if (excel.isEmpty()) {
			ajaxResult.setMsg("请选择要导入的Excel文件！");
			model.addAttribute("ajaxResult", ajaxResult);
			return list(1, new AutoShowTicket(),request, response, model);
		}
		try {
			ajaxResult=autoShowTicketMng.excelSave(excel, 1, 2, 1, ajaxResult);
		} catch (Exception e) {
			log.error(user.getUserName()+"导入车展门票券码发生未知异常：",e);
			ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
			ajaxResult.setMsg("导入处理发生未知异常，请稍后再试！");
			model.addAttribute("ajaxResult", ajaxResult);
			return list(1, new AutoShowTicket(), request, response, model);
		}
		model.addAttribute("ajaxResult", ajaxResult);
		return list(1, new AutoShowTicket(), request, response, model);
	}
}
