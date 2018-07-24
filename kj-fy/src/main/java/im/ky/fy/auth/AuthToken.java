package im.ky.fy.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 token权限校验
 * 放在方法前面，每次请求接口的时候都需要验证token是否合法
 * @author yangjing
 * 
 */

@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {

	boolean validate() default true;
}
