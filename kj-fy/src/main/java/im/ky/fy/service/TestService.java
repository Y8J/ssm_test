package im.ky.fy.service;

import im.ky.fy.pojo.Test;
import im.ky.fy.pojo.TestMapperBean;

import java.util.List;
import java.util.Map;

public interface TestService {
	/**
	  * xml返回HashMap数据
	  * @return
	  */
	 List<Map<Object,Object>> queryTestMap();
	 /**
	  * XML返回实体数据
	  * @return
	  */
	 List<Test> queryTestPojo();
	 
	 
	 /**
	  * Mapper返回实体数据List
	  * @return
	  */
	 List<TestMapperBean> mapperQueryTestPojo();
	 
	 
	 /**
	  *  通用mapper 与 分页助手 结合组成分页查询功能
	  */
	 List<TestMapperBean> pagingTestPojo(TestMapperBean bean,Integer pageNo,Integer pageSize);
	 
}
