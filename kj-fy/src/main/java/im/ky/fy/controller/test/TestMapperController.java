package im.ky.fy.controller.test;

import im.ky.fy.pojo.TestMapperBean;
import im.ky.fy.service.TestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("tmc")
@Controller
public class TestMapperController {

	@Autowired
	private TestService testService;
	
    @RequestMapping("queryTestPojo")
    @ResponseBody
    public List<TestMapperBean> queryTestPojo(){
	    return testService.mapperQueryTestPojo();
    }
	
}
