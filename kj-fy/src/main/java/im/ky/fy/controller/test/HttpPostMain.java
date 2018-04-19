package im.ky.fy.controller.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping(value="httpPostMain")
@Controller
public class HttpPostMain {
	
 public static String cookieVal="";  

 /**
  * 模拟浏览器get请求
  * @param url_get   请求url连接 www.baidu.com
  * @param str_param_url  请求参数 a=20171101&b=02&c=20171101
  * @param charset  请求字符集 如 utf-8,gbk等
  * @param cookie  浏览器端可以复制cookies
  * @throws IOException
  */
 public static void Get(String url_get,String str_param_url,String charset,String cookie) throws IOException  {  
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码  
        //    String getURL = GET_URL + "?username="  + URLEncoder.encode("fat man", "utf-8");  
        String getURL = url_get + "?" + str_param_url; 
        URL getUrl = new URL(getURL);  
        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，  
        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection  
        HttpURLConnection connection = (HttpURLConnection) getUrl  
                .openConnection();  
  
        if (cookie != null) {  
            //发送cookie信息上去，以表明自己的身份，否则会被认为没有权限  
        	System.out.println("set cookieVal = [" + cookie + "]");  
            connection.setRequestProperty("Cookie", cookie);  
        }  
  
        // 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到  
        // 服务器  
        connection.connect();  
        // 取得输入流，并使用Reader读取  
        BufferedReader reader = new BufferedReader(new InputStreamReader(  
                connection.getInputStream(),charset));  
        System.out.println("Contents of get request:");  
        String lines;  
        while ((lines = reader.readLine()) != null)  {  
             System.out.println(lines);  
        }  
        System.out.println(" ");
        reader.close();  
        // 断开连接  
        connection.disconnect();  
    }  
	
 /**
  * 模拟浏览器post请求
  * @param url_get   请求url连接 www.baidu.com
  * @param str_param_url  请求参数 a=20171101&b=02&c=20171101
  * @param charset  请求字符集 如 utf-8,gbk等
  * @param cookie  浏览器端可以复制cookies
  * @throws IOException
  */
 public static String Post(String url_post,String str_param_body,String charset,boolean b_flag,String cookies) throws IOException  {  
        // Post请求的url，与get不同的是不需要带参数  
        URL postUrl = new URL(url_post);  
        // 打开连接  
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();  
        // Output to the connection. Default is  
        // false, set to true because post  
        // method must write something to the  
        // connection  
        // 设置是否向connection输出，因为这个是post请求，参数要放在  
        // http正文内，因此需要设为true  
        if("" != cookies){  
            connection.setRequestProperty("Cookie", cookies);  
        }  
        
        connection.setDoOutput(true);  
        // Read from the connection. Default is true.  
        connection.setDoInput(true);  
        // Set the post method. Default is GET  
        connection.setRequestMethod("POST");  
        // Post cannot use caches  
        // Post 请求不能使用缓存  
        connection.setUseCaches(false);  
        // This method takes effects to  
        // every instances of this class.  
        // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。  
        // connection.setFollowRedirects(true);  
  
        // This methods only  
        // takes effacts to this  
        // instance.  
        // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数  
        connection.setInstanceFollowRedirects(b_flag);  
        // Set the content type to urlencoded,  
        // because we will write  
        // some URL-encoded content to the  
        // connection. Settings above must be set before connect!  
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的  
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode  
        // 进行编码  
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，  
        // 要注意的是connection.getOutputStream会隐含的进行connect。  
        connection.connect();  
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());  
        // The URL-encoded contend  
        // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致  
        //    String content = "userName=" + URLEncoder.encode("console", "utf-8");  
        //    content = content + "&password=" + URLEncoder.encode("12345678", "utf-8");  
  
        System.out.println("http param body = [" + str_param_body + "]"); 
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
        out.writeBytes(str_param_body);  
  
        out.flush();  
  
        // 取得cookie，相当于记录了身份，供下次访问时使用  
        //    cookieVal = connection.getHeaderField("Set-Cookie").split(";")[0]  
        cookieVal = connection.getHeaderField("Set-Cookie");
        System.out.println("get cookieVal = [" + cookieVal  + "]");  
  
        out.close(); // flush and close  
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),charset));  
        String line;  
        System.out.println("Contents of post request:");  
        while ((line = reader.readLine()) != null)  {  
            System.out.println(line);  
        }  
        System.out.println(" ");  
  
        reader.close();  
        connection.disconnect();  
  
        return cookieVal;  
    }  
	 
     /**
      * 
      * @param request
      * @param response
      * @param model
      * @param c 表示第几场 01 上午
      * @return
      * @throws IOException
      */
     @ResponseBody
     @RequestMapping(value="index1.do")
	 public String go(HttpServletRequest request, HttpServletResponse response, ModelMap model,String c) throws IOException {
		 String url1 = "http://member.gzzbpizza.com/WxFood/WxService.asmx/QuickSeckill";
		 String cs1 = "drawNo=20171101&periodNo="+c+"&ticketNo=20171101";
		 
		 
		 String url2 = "http://member.gzzbpizza.com/WxFood/WxService.asmx/QuickSeckill";
		 String cs2 = "drawNo=20171101&periodNo="+c+"&ticketNo=20171101";
		 
		 
         String cookie_login1 = "ASP.NET_SessionId=1e24moqadtfjtpqieiuuaunq; Phone=13129373424; GroupId=54; LGK=DB05C50AF733FDEA045FBA4D935B72AC";
		 
		 String cookie_login2 = "ASP.NET_SessionId=aj0iqfwqy3lwcwywhr4wnycb; Phone=13660342517; GroupId=54; LGK=F9FA69B4A276883313FB442F2ECB07DB";
		 
		 for(int i=0;i<1000;i++){
			 Post(url1,cs1,"utf-8",false,cookie_login1);
		 
			 Post(url2,cs2,"utf-8",false,cookie_login2);
		 } 
		 
		 String s1 = "18485807074"+Post(url1,cs1,"utf-8",false,cookie_login1)+"end";
		 String s2 = "13141957723"+Post(url2,cs2,"utf-8",false,cookie_login2)+"end";
		 
		 return s1+s2;
	 }
}
