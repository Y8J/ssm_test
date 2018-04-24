package im.ky.fy.controller.test;

import im.ky.fy.pojo.TestMapperBean;
import im.ky.fy.service.TestMapperService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("tmc")
@Controller
public class TestMapperController {

	@Autowired
	private TestMapperService testMapperService;
	
	 @RequestMapping("queryTestPojo")
	  @ResponseBody
	  public List<TestMapperBean> queryTestPojo(){
		  return testMapperService.queryTestPojo();
	  }
	
}
