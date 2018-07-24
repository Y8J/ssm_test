package im.ky.fy.shiro;

import im.ky.fy.pojo.Role;
import im.ky.fy.pojo.SysMenuBean;
import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.service.UserService;
import im.ky.fy.util.Md5Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro 自定义配置
 * @author yangjing
 *
 */
public class ShiroUserRealm extends AuthorizingRealm {

	 private static Logger logger = LoggerFactory.getLogger(ShiroUserRealm.class);  
     private static final String ALGORITHM = "MD5";  
      
     @Autowired  
     private UserService userService;  
  
     public ShiroUserRealm() {  
        super();  
     }  
     
     
     /** 
      * 验证登陆 
      */ 
 	@Override
 	protected AuthenticationInfo doGetAuthenticationInfo(
 			AuthenticationToken authcToken) throws AuthenticationException {
 		   UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
 		   logger.info("获得的用名为:"+token.getUsername());
 		   
 		   //获取UsernamePasswordToken中的用户名
 		   String username = token.getPrincipal().toString();
 		//获取UsernamePasswordToken中的用户密码
 		   String password = new String((char[]) token.getCredentials());

 		   
 		   //用户名密码查询
 	       UserMapperBean suo = new UserMapperBean();
 	       suo.setUserName(username);
 	       suo.setUserPwd(password);
 	       List<UserMapperBean> userlist = userService.queryUserByName(suo);
 	       logger.info("user:"+userlist);
 	       
 	        if (userlist != null && (!userlist.isEmpty())) {
 	        	try {
					return new SimpleAuthenticationInfo(userlist.get(0).getUserName(), userlist.get(0).getUserPwd(), getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
 	        }else{  
 	            throw new AuthenticationException();  
 	        }  
 		return null;
 	}
 	
	
 	/** 
     * 登陆成功之后，进行角色和权限验证 
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		
		//获取用户
		UserMapperBean user = (UserMapperBean)SecurityUtils.getSubject().getPrincipal();
		
		//获取用户所有的角色
		List<Role> roleList = userService.findRoleByUserId(user);
		//把角色值给set容器
		Set<String> roleSet = new HashSet<String>();
		for(Role role : roleList){
			//角色类型
			roleSet.add(role.getRoleType());
		}
		//角色添加
		info.setRoles(roleSet);
		
		//获取用户所有的权限
		List<SysMenuBean> permissionList = userService.findPermissionByUserId(user);
		//把权限值给set容器
		Set<String> sysMenuBeanSet = new HashSet<String>();
		for(SysMenuBean sysMenuBean : permissionList){
			sysMenuBeanSet.add(sysMenuBean.getMenuUrl());
		}
		//权限添加
		info.setStringPermissions(sysMenuBeanSet);
		return info;
	}

     /* 
      * 清除所有用户授权信息缓存. 
	  */  
    public void clearCachedAuthorizationInfo(String principal) {  
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());  
        clearCachedAuthorizationInfo(principals);  
    }  
	  
	  
    /** 
     * 清除所有用户授权信息缓存. 
     */  
    public void clearAllCachedAuthorizationInfo() {  
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();  
        if (cache != null) {  
            for (Object key : cache.keys()) {  
                cache.remove(key);  
            }  
        }  
    }  
}
