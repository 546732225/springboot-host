package com.example.data.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DictParam {

    /**
     * 字典CODE
     */
    String dictCode() default "";

    /**
     * 需要翻译的字段名
     */
    String dictValueFiled() default "";

    /**
     * 被翻译的字段名
     */
    String dictNameFiled() default "";
}
