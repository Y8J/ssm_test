package im.ky.fy.mapper;

import im.ky.fy.pojo.Role;
import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.pojo.UserMapperBean;

import java.util.List;

public interface UserXMLDao {

	/**
	 * 根据用户查询用户所有权限
	 */
	public List<SysMenuBean> findPermissionByUserId(UserMapperBean user);
	
	/**
	 * 根据用户查询用户所有角色
	 */
	public List<Role> findRoleByUserId(UserMapperBean user);
}
