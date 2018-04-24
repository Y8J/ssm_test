package im.ky.fy.service.serviceimpl;

import im.ky.fy.mapper.TestMapper;
import im.ky.fy.pojo.TestMapperBean;
import im.ky.fy.service.TestMapperService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestMapperServiceImpl implements TestMapperService {

	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<TestMapperBean> queryTestPojo() {
		List<TestMapperBean> testList = testMapper.select(null);
		
		TestMapperBean t = new TestMapperBean();
		t.setId(1);
		
		List<TestMapperBean> tlist = testMapper.select(t);
		
		TestMapperBean tt = testMapper.selectByPrimaryKey(t);
		
		return testList;
	}

}
