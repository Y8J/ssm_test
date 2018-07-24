package im.ky.fy.service;

import im.ky.fy.pojo.Role;
import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.pojo.UserMapperBean;

import java.util.List;

public interface UserService {
	
	//根据用户名查询用户信息
	public List<UserMapperBean> queryUserByName(UserMapperBean bean);
	
	//保存用户信息
	public Integer save(UserMapperBean bean);
	
	//查询用户信息
	public List<UserMapperBean> queryUserMapperBean(UserMapperBean bean);
	
	//查看用户数据分页列表显示
	public List<UserMapperBean> pagingUserPojo(UserMapperBean bean,Integer pageNo,Integer pageSize);
	
	
	/**
	 * 根据用户查询用户所有权限
	 */
	public List<SysMenuBean> findPermissionByUserId(UserMapperBean user);
	
	/**
	 * 根据用户查询用户所有角色
	 */
	public List<Role> findRoleByUserId(UserMapperBean user);
}
