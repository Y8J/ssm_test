package im.ky.fy.controller.admin;

import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.service.SysMenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("sysmenu")
public class SysMenuAdmin {

	@Autowired
	private SysMenuService sysMenuService;
	
	//查询所有的菜单
	@ResponseBody
	@RequestMapping("list.do")
	public List<SysMenuBean> listSys(HttpServletRequest request, HttpServletResponse response,ModelMap model,SysMenuBean bean,Integer pageNo,Integer pageSize){
		 pageNo = 1;
		 pageSize = 10;
		List<SysMenuBean> listPage = sysMenuService.getListPage(bean, pageNo, pageSize);
		return listPage;
	}
	
	//根据父类id查询菜单
	@ResponseBody
	@RequestMapping("byIdSys.do")
	public List<SysMenuBean> getIntSys(HttpServletRequest request, HttpServletResponse response,ModelMap model,Long parentId){
		//查询的菜单列表
		List<SysMenuBean> listPage = sysMenuService.getListPage(parentId);
		
		//保存的菜单列表
		List<SysMenuBean> list = Lists.newArrayList();
		sortList(list,listPage,1L);
		
		return list;
	}
	
	/**
	 * 
	 * @param list  保存容器
	 * @param listAll 查询出来的数据
	 * @param pid 父类id
	 */
	public void sortList(List<SysMenuBean> list,List<SysMenuBean> listAll,Long pid){
		
		for (int i=0; i<listAll.size(); i++){
			SysMenuBean e = listAll.get(i);
			
			if (e.getParentId().equals(pid)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<listAll.size(); j++){
					SysMenuBean child = listAll.get(j);
					List<SysMenuBean> listPage = sysMenuService.getListPage(child.getParentId());
					for (SysMenuBean sysMenuBean : listPage) {
						sortList(list, listAll, e.getId());
						break;
					}
				}
			}
		}
	}
	
}
