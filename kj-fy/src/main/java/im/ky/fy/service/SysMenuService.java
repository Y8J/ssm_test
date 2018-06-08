package im.ky.fy.service;

import im.ky.fy.pojo.SysMenuBean;

import java.util.List;

public interface SysMenuService {

	//分页查询所有菜单
	public List<SysMenuBean> getList(SysMenuBean bean);
	
	//根据父类Id查询菜单
	public List<SysMenuBean> getListPage(Long parentId);
	
	//条件查询单个菜单
	public SysMenuBean getBean(SysMenuBean bean);
}
