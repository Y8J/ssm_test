package im.ky.fy.util.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class JWTUtils {

	private static final Logger log = LoggerFactory.getLogger(JWTUtils.class);
	
	//密钥
	private static final String SECRET = "xX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
	
	//经验值
	private static final String EXP = "exp";
	
	//有效载荷
	private static final String PAYLOAD = "payload";
 
	/**
	 * 获取jwt对象字符串
	 * @param object 实体pojo对象
	 * @param maxAge  时间搓 毫秒值
	 * @return 返回一个token
	 */
	public static <T> String sign(T object, Date date) {
		try {
			final JWTSigner signer = new JWTSigner(SECRET);
			final Map<String, Object> claims = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(object);
			claims.put(PAYLOAD, jsonString);
			long currentTimeMillis = System.currentTimeMillis();
			//获取时间搓
			Long dateLong = date.getTime();
			claims.put(EXP, dateLong);
			return signer.sign(claims);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获取未过期的jwt对象
	 * @param jwt
	 * @return 返回实体类对象
	 */
	public static<T> T unsign(String token, Class<T> classT) {
		final JWTVerifier verifier = new JWTVerifier(SECRET);
	    try {
			final Map<String,Object> claims= verifier.verify(token);
			if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
				Long exp = (Long)claims.get(EXP);
				//System.currentTimeMillis()返回的毫秒
				long currentTimeMillis = System.currentTimeMillis();
				if (exp > currentTimeMillis) {
					String json = (String)claims.get(PAYLOAD);
					ObjectMapper objectMapper = new ObjectMapper();
					return objectMapper.readValue(json, classT);
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
