package im.ky.fy.service.excel;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.util.AjaxResult;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;


public interface UserExcelAddService {

	/**
	 * 保存Excel
	 * 
	 * @param bean
	 *            保存bean
	 * @return
	 */
	public JSONObject excelSave(MultipartFile excel, int startSheet, int startLine, int startColumn,JSONObject ajaxResult);
}
