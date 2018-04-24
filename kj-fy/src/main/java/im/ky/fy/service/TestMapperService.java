package im.ky.fy.service;

import im.ky.fy.pojo.TestMapperBean;

import java.util.List;

public interface TestMapperService {
	/**
	  * 返回实体数据List
	  * @return
	  */
	 List<TestMapperBean> queryTestPojo();
}
