package im.ky.fy.service.serviceimpl;

import im.ky.fy.mapper.UserMapper;
import im.ky.fy.pojo.TestMapperBean;
import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public Integer save(UserMapperBean bean) {
		int insert = userMapper.insert(bean);
		return insert;
	}

	@Override
	public List<UserMapperBean> queryUserMapperBean(UserMapperBean bean) {
		List<UserMapperBean> select = userMapper.select(bean);
		return select;
	}
	
	
	@Override
	public List<UserMapperBean> pagingUserPojo(UserMapperBean bean,Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<UserMapperBean> tlist = userMapper.select(bean);
		return tlist;
	}
	
	
}
