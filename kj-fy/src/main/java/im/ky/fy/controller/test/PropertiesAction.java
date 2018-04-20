package im.ky.fy.controller.test;

import im.ky.fy.util.PropertyUtil;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实验使用propertyUtil读取配置文件工具类
 * @author yj
 *
 */
@RestController
@RequestMapping("proper")
public class PropertiesAction {

	@RequestMapping("sunline.do")
	public String properSunline(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Properties ps = PropertyUtil.getPropertes("sunline", "sunline");
		String s = ps.getProperty("sunline.a");
		return s;
	}
	
	@RequestMapping("web.do")
	public String properWeb(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Properties ps = PropertyUtil.getPropertes("web", "web");
		String s = ps.getProperty("web.p");
		return s;
	}
}
