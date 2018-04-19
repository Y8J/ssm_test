package im.ky.fy.controller.test;

import im.ky.fy.pojo.Test;
import im.ky.fy.service.TestService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="test")
public class TestController {
  @Autowired
  TestService testService;
	
  @RequestMapping(value="queryTestMap")
  @ResponseBody
  public List<Map<Object,Object>> queryTestMap(){
	  return testService.queryTestMap();
  }
  
  @RequestMapping("queryTestPojo")
  @ResponseBody
  public List<Test> queryTestPojo(){
	  return testService.queryTestPojo();
  }
}
