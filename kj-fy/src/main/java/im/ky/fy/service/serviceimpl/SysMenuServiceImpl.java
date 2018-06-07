package im.ky.fy.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import im.ky.fy.mapper.SysMenuMapper;
import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Override
	public List<SysMenuBean> getListPage(SysMenuBean bean, Integer pageNo,Integer pageSize) {
		//转载分页插件
		PageHelper.startPage(pageNo, pageSize);
		List<SysMenuBean> select = sysMenuMapper.select(bean);
		return select;
	}

	@Override
	public List<SysMenuBean> getListPage(Long parentId) {
		SysMenuBean bean = new SysMenuBean();
		bean.setParentId(parentId);
		List<SysMenuBean> select = sysMenuMapper.select(bean);
		return select;
	}

	@Override
	public SysMenuBean getListPage(SysMenuBean bean) {
		SysMenuBean selectOne = sysMenuMapper.selectOne(bean);
		return selectOne;
	}

}
