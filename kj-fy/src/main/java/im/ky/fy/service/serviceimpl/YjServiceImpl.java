package im.ky.fy.service.serviceimpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Value是Spring内部用来读取properties配置文件内容的注解
 * @author yangjingenv
 *
 */
@Service
public class YjServiceImpl {

	@Value("${url}")
	public String url;
	@Value("${userName}")
	public String userName;
	@Value("${password}")
	public String password;
}
