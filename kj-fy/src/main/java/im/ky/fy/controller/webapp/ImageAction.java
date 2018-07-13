package im.ky.fy.controller.webapp;

import im.ky.fy.util.PropertyUtil;
import im.ky.fy.util.ResponseUtils;
import im.ky.fy.util.fileUtils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 图片上传模拟演示
 * @author yangjing
 *
 */
@RequestMapping("image")
@Controller
public class ImageAction {

	//图片上传名字
	public String fileName =null;
	
	@RequestMapping("uploadIndex.do")
	public String uploadIndex(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws JSONException{
	
		return "upload";
	}
		
	@RequestMapping("upload.do")
	public void upload(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws JSONException{
		JSONObject ajaxResult = new JSONObject();
		
		MultipartFile file = null;
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			file = multipartRequest.getFile("uploadFile");
		}
		
		String picPath = "";
		try {
			picPath = FileUtil.saveStationsFile("001", file, request);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.put("status", -1);
			ajaxResult.put("msg", "保存图片出错!!!");
			ResponseUtils.renderJson(response, ajaxResult.toString());
		}
		
		fileName = picPath;
		ajaxResult.put("msg", "保存图片成功！！！");
		ResponseUtils.renderJson(response, ajaxResult.toString());
	}
	
	/**
	 * 图片查看
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws JSONException
	 */
	@ResponseBody
	@RequestMapping("viewimage.do")
	public String viewImage(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws JSONException{
		JSONObject ajaxResult = new JSONObject();
		
		String sr = "http://10.2.20.210:8088";
		
		String sr1 = PropertyUtil.getProperty("upload.path");
		
		
		
		ajaxResult.put("image", sr+sr1+fileName);
		
		return ajaxResult.toString();
	}
}
