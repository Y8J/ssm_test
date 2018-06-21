package im.ky.fy.controller.admin;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="userrestful")
public class UserRestfulAction {

	@Autowired
	private UserService userService;
	
	//查看用户分页列表显示
	@ResponseBody
	@RequestMapping(value="{userId}",method=RequestMethod.GET)
	public ResponseEntity<UserMapperBean> list(HttpServletRequest request, HttpServletResponse response,ModelMap model,@PathVariable("userId")Long userId){
		UserMapperBean bean = new UserMapperBean();
		bean.setId(userId);
		List<UserMapperBean> pagingUserPojo = userService.queryUserMapperBean(bean);
		
		try { int i=1/0;
			//
			return ResponseEntity.status(HttpStatus.OK).body(pagingUserPojo.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
