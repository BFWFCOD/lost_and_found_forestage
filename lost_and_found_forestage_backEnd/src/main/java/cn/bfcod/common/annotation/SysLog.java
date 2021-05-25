

package cn.bfcod.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author bfcod
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
