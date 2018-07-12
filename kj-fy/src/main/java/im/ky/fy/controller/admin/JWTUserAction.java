package im.ky.fy.controller.admin;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;
import im.ky.fy.util.jwt.JWTUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("jwtuser")
@Controller
public class JWTUserAction {

	@Autowired
	private UserService userService;
	
	/**
	 * 获取token
	 * @param request
	 * @param response
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("gettoken.do")
	public ResponseEntity<String>  gettoken(HttpServletRequest request, HttpServletResponse response,
									   ModelMap model,
									   String username,
									   String password) throws ParseException{
		UserMapperBean user = new UserMapperBean();
		user.setUserName(username);
		user.setUserPwd(password);
		List<UserMapperBean> queryUserMapperBean = userService.queryUserMapperBean(user);
		//maxAge 时间搓 30L * 24L * 3600L * 1000L

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2018-07-12 09:50:00");
		String token = JWTUtils.sign(queryUserMapperBean.get(0),date);
		
		if (token != null) {
			return ResponseEntity.status(HttpStatus.OK).body(token);
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("用户名或者密码错误");
	}
	
	/**
	 * 检验token
	 * @param request
	 * @param response
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checktoken.do")
	public ResponseEntity<UserMapperBean>  checkToken(HttpServletRequest request, HttpServletResponse response,
									   ModelMap model,String token){
		
		UserMapperBean tokenUser = JWTUtils.unsign(token, UserMapperBean.class);
		if(tokenUser!=null){
			return ResponseEntity.status(HttpStatus.OK).body(tokenUser);
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
