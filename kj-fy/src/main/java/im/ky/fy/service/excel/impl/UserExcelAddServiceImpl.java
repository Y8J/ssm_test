package im.ky.fy.service.excel.impl;

import im.ky.fy.service.excel.UserExcelAddService;
import im.ky.fy.util.poi.ExportUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * excle解析方法
 * @author yangjing
 *
 */
@Service
public class UserExcelAddServiceImpl implements UserExcelAddService {

	/**
	 * 保存Excel
	 *  * @param startSheet
	 *            需要解析的开始页
	 * @param startLine
	 *            需要解析的开始行
	 * @param startColumn
	 *            需要解析的开始列
	 * @param bean
	 *            保存bean
	 * @return
	 * @throws JSONException 
	 */
	public JSONObject excelSave(MultipartFile excel, int startSheet, int startLine, int startColumn, JSONObject ajaxResult){
		JSONArray listj = new JSONArray();
		
		//保存excel零时数据容器
		List<List<String>> data = new ArrayList<List<String>>();
		
		try {
			data = ExportUtil.resolveExcel(excel.getInputStream(), startSheet, startLine, startColumn);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				ajaxResult.put("status", 1);
				ajaxResult.put("msg", "解析Excel异常。。。。。。");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return ajaxResult;
		}
		List<String> curList=new ArrayList<String>();//当前excel门票列表（Excel）
		for (int i = 0; i < data.size(); i++) {
			List<String> row = data.get(i);
			//如果excel为null跳出本次循环
			if (row==null||row.size()==0) {
				break;
			}
			if (StringUtils.isBlank(row.get(0))) {
				break;
			}
			
			try {
				//保存导入的所有对象返回到页面
				JSONObject pojo = new JSONObject();
				//获取excel用户名
				pojo.put("name", row.get(0));
				//获取excel用户密码
				pojo.put("password", row.get(1));
				//获取excel用户状态
				pojo.put("status", row.get(2));
				//获取excel用户邮箱
				pojo.put("emil", row.get(3));
				//获取excel用户手机号
				pojo.put("mobile", row.get(4));
				listj.put(pojo);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			ajaxResult.put("userList", listj);
			return ajaxResult;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ajaxResult.put("status", 0);
			ajaxResult.put("msg", "解析Excel异常。。。。。。");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ajaxResult;
	}
}
