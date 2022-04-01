package com.example.data.annotation;

import java.lang.annotation.*;

/**
 * @author seven
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {


    String key() default  "";

    /**
     *过期时间，使用本地缓存可以忽略，如果使用redis做缓存就需要
     */
    int expire() default 5;
}
