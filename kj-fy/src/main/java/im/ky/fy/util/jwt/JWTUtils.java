package im.ky.fy.util.jwt;

import im.ky.fy.pojo.UserMapperBean;
import im.ky.fy.util.Md5Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * <!-- JWT权限验证token -->
 * <dependency>
 *   <groupId>com.auth0</groupId>
 *   <artifactId>java-jwt</artifactId>
 *   <version>3.3.0</version>
 * </dependency>
 */
		
public class JWTUtils {

	private static final Logger log = LoggerFactory.getLogger(JWTUtils.class);
	
	/** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */
	public static final String SECRET = "JKKLJOoasdlfj";
	//对象公用名
	public static final String EXP = "EXP"; 
	//字符串公用名
	public static final String EXPStr = "EXPStr"; 
	
	/** token 过期时间: 10天/分钟 */
	public static final int calendarField = Calendar.MINUTE;  //Calendar.MINUTE 分钟数,Calendar.DATE 天数
	public static final int calendarInterval = 3;  //MINUTE:表示10分钟  ,DATE:表示10天
 
	
	/**
	 * 创建token
	 * 第一部分我们称它为头部（header)
	 * 第二部分我们称其为载荷（payload)
	 * 第三部分是签证（signature)
	 * @param object  传入的pojo对象
	 * @param date  传入的token创建时间
	 * @return
	 * @throws Exception
	 */
	public static String createTokenString(String userName, Date date) throws Exception {
		
		// token过期时间
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(calendarField, calendarInterval);
		Date expiresDate = nowTime.getTime();
		
		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
 
		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withClaim("iss", "Service") // payload
				.withClaim("aud", "APP").withClaim(EXPStr,userName)
				.withIssuedAt(date) // sign time 设置签发时间
				.withExpiresAt(expiresDate) // expire time 设置过期时间 过期时间要大于签发时间
				.sign(Algorithm.HMAC256(SECRET)); //加密算法
		return token;
	}
	
	/**
	 * 创建token 加入user对象
	 * 第一部分我们称它为头部（header)
	 * 第二部分我们称其为载荷（payload)
	 * 第三部分是签证（signature)
	 * @param object  传入的pojo对象
	 * @param date  传入的token创建时间
	 * @return
	 * @throws Exception
	 */
	public static<T> String createToken(T object, Date date) throws Exception {
		
		// token过期时间
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(calendarField, calendarInterval);
		Date expiresDate = nowTime.getTime();
 
		//解析object对象
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(object);
		
		
		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
 
		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withClaim("iss", "Service") // payload
				.withClaim("aud", "APP").withClaim(EXP,jsonString)
				.withIssuedAt(date) // sign time 设置签发时间
				.withExpiresAt(expiresDate) // expire time 设置过期时间 过期时间要大于签发时间
				.sign(Algorithm.HMAC256(SECRET)); //加密算法
		return token;
	}
 
	
	/**
	 * 解密验证令牌安全情况
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Boolean verifyTokenTrueFlse(String token) {
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}
	
	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) {
		Boolean flg = verifyTokenTrueFlse(token);
		if(!flg){
			return null;
		}
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			// e.printStackTrace();
			// token 校验失败, 抛出Token验证非法异常
			
		}
		return jwt.getClaims();
	}
 
	/**
	 * 根据Token获取user
	 * 
	 * @param token
	 * @return user_id
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static<T> T getAppUser(String token, Class<T> classT) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Claim> claims = verifyToken(token);
		Claim userStr = claims.get(EXP);
		if (null != userStr || StringUtils.isEmpty(userStr.asString())) {
				ObjectMapper objectMapper = new ObjectMapper();
				
				String asString = userStr.asString();
				return objectMapper.readValue(asString, classT);
		}
		return null;
	}


	public static void main(String[] args) throws Exception {
		//创建token
		UserMapperBean user = new UserMapperBean();
		user.setUserName("admin");
		user.setUserPwd("admin");
		//maxAge 时间搓 30L * 24L * 3600L * 1000L
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2018-07-12 09:50:00");
		String token = JWTUtils.createToken(user,date);
		
		log.info("创建token："+token);
		
		
		//解密token
		 Map<String, Claim> map = JWTUtils.verifyToken(token);
		 log.info(map.toString());
		//根据Token获取user_id
		UserMapperBean user1 = JWTUtils.getAppUser(token,UserMapperBean.class);
		log.info("根据Token获取user_id："+user);
	}
}
