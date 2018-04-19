package im.ky.fy.service;

import im.ky.fy.pojo.Test;

import java.util.List;
import java.util.Map;

public interface TestService {
	/**
	  * 返回HashMap数据
	  * @return
	  */
	 List<Map<Object,Object>> queryTestMap();
	 /**
	  * 返回实体数据
	  * @return
	  */
	 List<Test> queryTestPojo();
}
