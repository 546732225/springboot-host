package com.example.data.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FieldParam {
    
    
    /**
     * 字典表的值
     */
    String code() default "";
    
    /**
     * 实体类中字典属性
     */
    String sourceFiled() default "";
    
    /**
     * 实体类中给字典赋值属性
     */
    String targetFiled() default "";
   
}
