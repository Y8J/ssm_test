package im.ky.fy.controller.test;

import im.ky.fy.pojo.TestMapperBean;
import im.ky.fy.service.TestService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("tphc")
@Controller
public class TestPageHelperController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("paging")
	@ResponseBody
	public List<TestMapperBean> paging(HttpServletRequest request, HttpServletResponse response,String name){
		TestMapperBean bean = new TestMapperBean();
		bean.setName(name);
		List<TestMapperBean> testList = testService.pagingTestPojo(bean);
		
		return testList;
	}
}
