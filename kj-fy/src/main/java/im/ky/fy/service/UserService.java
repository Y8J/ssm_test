package im.ky.fy.service;

import im.ky.fy.pojo.UserMapperBean;

import java.util.List;

public interface UserService {
	
	//保存用户信息
	public Integer save(UserMapperBean bean);
	
	//查询用户信息
	public List<UserMapperBean> queryUserMapperBean(UserMapperBean bean);
	
	//查看用户数据分页列表显示
	public List<UserMapperBean> pagingUserPojo(UserMapperBean bean,Integer pageNo,Integer pageSize);
	
}
