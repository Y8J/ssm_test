package im.ky.fy.controller.intercept;

import im.ky.fy.auth.AuthToken;
import im.ky.fy.util.jwt.JWTUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * token权限验证拦截器 AuthToken接口
 * 
 * @author yangjing
 *  fastjson jar的神奇功能  对象转json  json转对象
 *    <dependency>
 *		<groupId>com.alibaba</groupId>
 *	    <artifactId>fastjson</artifactId>
 *		<version>1.1.37</version>
 *    </dependency>
 * //json转化成为javaList对象
 *		List<User> findObject =  (List<User>) JSON.parseArray(jsonzhuan, User.class);
 * //list转化成为json
 *		String json = JSON.toJSON(findUserOrRedisByUserId).toString()
 *
 */
public class AuthTokenHandlerInterceptor implements HandlerInterceptor {

	//preHandle：在执行action里面的处理逻辑之前执行，它返回的是boolean，这里如果我们返回true在接着执行postHandle和afterCompletion，如果我们返回false则中断执行。
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		    //查看是否存在注解 @AuthToken存在就解析token校验
	        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
	        	AuthToken authToken = ((HandlerMethod) handler).getMethodAnnotation(AuthToken.class);
	        	
                if(authToken != null && authToken.validate() != false){
                	String param = getPostParameter(request);
              	    System.out.println(param);
              	    //阿里fastjson json转化
              	    JSONObject obj = JSONObject.parseObject(param);
            		//从json中获取token参数
            	     String token = (String) obj.get("token")==null?"123456677": (String) obj.get("token"); 
                	//进行token JWT校验
                	Boolean bo = JWTUtils.verifyTokenTrueFlse(token);
            		if(bo==false){
            			//token不正确则返回验证错误提示信息
            			returnjson(response); 
            		}
                    return bo;
                }else{
                    return true;
                }
	        }
        
		return false;
	}


	//postHandle：在执行action里面的逻辑后返回视图之前执行。。
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	


	//afterCompletion：在action返回视图后执行
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private static void returnjson(HttpServletResponse response){
		
		//如果token校验信息错误返回错误json提示信息
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null ;
		try{
		    JSONObject res = new JSONObject();
		    res.put("status","-1");
		    res.put("msg","token校验有误，请勿非法操作！");
		    out = response.getWriter();
		    out.append(res.toString());
		}catch (Exception e){
		    e.printStackTrace();
		    try {
				response.sendError(500);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	/**
	  * 只能用于post请求get请求会报错
	  *       java.lang.NegativeArraySizeException
	  * 根据request获取Post参数
	  * @param request
	  * @return
	  * @throws IOException
	  */
	  private static String getPostParameter(HttpServletRequest request) throws IOException{
		  BufferedInputStream buf = null;
		  int iContentLen = request.getContentLength();
		  byte sContent[] = new byte[iContentLen];
		  String sContent2 = null;
		  try {
			  buf = new BufferedInputStream(request.getInputStream());
			  buf.read(sContent, 0, sContent.length);
			  sContent2 = new String(sContent,0,iContentLen,"UTF-8");
	 
		  } catch (IOException e) {
			  throw new IOException("Parse data error!",e);
		  } finally
		  {
			  try {
				  buf.close();
			  } catch (IOException e) {
	 
			  }
		  }
		  return sContent2;
	  }


}
