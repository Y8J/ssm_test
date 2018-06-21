package im.ky.fy.service.serviceimpl;

import im.ky.fy.mapper.TestDao;
import im.ky.fy.mapper.TestMapper;
import im.ky.fy.pojo.Test;
import im.ky.fy.pojo.TestMapperBean;
import im.ky.fy.service.TestService;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;


@Service
public class TestServiceImpl implements TestService {
	
	/****************************使用通用Mybatis自身XML进行查询***********************/
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

	
	/****************************使用通用Mapper进行查询***********************/
	@Autowired
	private TestMapper testMapper;
	@Override
	public List<TestMapperBean> mapperQueryTestPojo() {
       List<TestMapperBean> testList = testMapper.select(null);
		
		//条件查询
		TestMapperBean t = new TestMapperBean();
		t.setId(1);
		t.setName("aaa");
		List<TestMapperBean> tlist = testMapper.select(t);
		
		TestMapperBean tt = testMapper.selectByPrimaryKey(t);
		
		return testList;
	}

	@Override
	public List<TestMapperBean> pagingTestPojo(TestMapperBean bean,Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<TestMapperBean> tlist = testMapper.select(bean);
		return tlist;
	}

}
