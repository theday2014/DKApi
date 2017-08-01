package common.domain.context;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wanghao on 2017/7/31.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Convertible {
    /**
     * @return the method name that convert to another type
     */
    String valueMethod() default "value";

    /**
     * @return the method name that convert from another type
     */
    String ofMethod() default "of";
}
