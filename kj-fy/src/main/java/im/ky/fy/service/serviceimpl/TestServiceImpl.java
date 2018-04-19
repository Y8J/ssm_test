package im.ky.fy.service.serviceimpl;

import im.ky.fy.mapper.TestDao;
import im.ky.fy.pojo.Test;
import im.ky.fy.service.TestService;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;
    private static Logger logger = Logger.getLogger(TestServiceImpl.class);
	@Override
	public List<Map<Object, Object>> queryTestMap() {
		return testDao.queryTestMap();
	}

	@Override
	public List<Test> queryTestPojo() {
		List<Test> list = testDao.queryTestPojo();
		logger.info("实体内映射所查询到的数据是:"+list);
		return list;
	}

}
