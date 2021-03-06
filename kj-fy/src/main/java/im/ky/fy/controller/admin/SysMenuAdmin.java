package im.ky.fy.controller.admin;

import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.service.SysMenuService;
import im.ky.fy.util.ResponseUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("sysmenu")
public class SysMenuAdmin {

	@Autowired
	private SysMenuService sysMenuService;
	
	//查询所有的菜单
	@ResponseBody
	@RequestMapping("list.do")
	public List<SysMenuBean> listSys(HttpServletRequest request, HttpServletResponse response,ModelMap model,SysMenuBean bean){
		 
		List<SysMenuBean> listPage = sysMenuService.getList(bean);
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
		
		return list;
	}
	
	
	
	@RequestMapping(value = "v_add.do", method = RequestMethod.GET)
    public String v_add(HttpServletRequest request, HttpServletResponse response, ModelMap model, SysMenuBean menu) {
        model.addAttribute("selectIds", "1,2,3,4,5,6");
        model.addAttribute("menu", menu);
        return "sysmenu/add";
    }
	
	@RequestMapping(value = "v_treemenu.do", method = {RequestMethod.POST,RequestMethod.GET})
	public void treemenu(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException{
		JSONObject ajaxResult = new JSONObject();
		List<SysMenuBean> list=sysMenuService.getList(null);
		if (list!=null&&list.size()>0) {
			JSONArray listj = new JSONArray();
			for (SysMenuBean menu : list) {
				JSONObject entity = new JSONObject();
				entity.put("id", menu.getId());
				entity.put("name", menu.getMenuName());
				if (menu!=null) {
					entity.put("pId", menu.getParentId());
				}else{
					entity.put("pId","");
				}
				listj.put(entity);
			}
			ajaxResult.put("treeData", listj);
		}
		ResponseUtils.renderJson(response, ajaxResult.toString());
	}
	
	@RequestMapping(value = "v_list.do")
	public String listgo(SysMenuBean menu,HttpServletRequest request, ModelMap model){
		List<SysMenuBean> list = Lists.newArrayList();
		List<SysMenuBean> sourcelist = sysMenuService.getList(null);
		sortList(list,sourcelist, 1L);
        model.addAttribute("mlist", list);
		return "sysmenu/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "v_json.do")
	public List<SysMenuBean> listgojson(SysMenuBean menu,HttpServletRequest request, ModelMap model){
		List<SysMenuBean> list = Lists.newArrayList();
		List<SysMenuBean> sourcelist = sysMenuService.getList(null);
		sortList(list,sourcelist, 1L);
        model.addAttribute("mlist", list);
		return list;
	}
	

	/**
	 *  List<SysMenu> list = Lists.newArrayList(); 获取容器
	 *  List<SysMenu> listAll = sysMenuMng.getList(); 查询全部
	 * @param list
	 * @param listAll
	 * @param pid=1
	 */
	public static void sortList(List<SysMenuBean> list,List<SysMenuBean> listAll,Long id){
		for (int i=0; i<listAll.size(); i++){
			SysMenuBean e = listAll.get(i);
			if (e.getParentId()!=null && e.getParentId().equals(id)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<listAll.size(); j++){
					SysMenuBean child = listAll.get(j);
					if (child.getParentId()!=null && child.getParentId().equals(e.getId())){
						sortList(list, listAll, e.getId());
						break;
					}
				}
			}
		}
	}
}
