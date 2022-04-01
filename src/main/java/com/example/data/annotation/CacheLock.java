package com.example.data.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author seven
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {

    /**
     * redis锁前缀
     */

    String prefix();

    /**
     * redis锁过期时间
     */
    int expire() default 5;

    /**
     * redis锁过期时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * key分隔符
     */
    String delimiter() default ":";
}
