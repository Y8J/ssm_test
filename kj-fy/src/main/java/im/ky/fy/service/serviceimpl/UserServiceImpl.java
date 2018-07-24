package im.ky.fy.service.serviceimpl;

import im.ky.fy.mapper.UserMapper;
import im.ky.fy.mapper.UserXMLDao;
import im.ky.fy.pojo.Role;
import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserXMLDao userXMLDao;
	
	@Override
	public  List<UserMapperBean> queryUserByName(UserMapperBean bean) {
		
		return userMapper.select(bean);
	}
	
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

	/**
	 * 根据用户查询用户所有权限
	 */
	@Override
	public List<SysMenuBean> findPermissionByUserId(UserMapperBean user){
		return userXMLDao.findPermissionByUserId(user);
	}
	
	/**
	 * 根据用户查询用户所有角色
	 */
	@Override
	public List<Role> findRoleByUserId(UserMapperBean user){
		return userXMLDao.findRoleByUserId(user);
	}
	
	
}
