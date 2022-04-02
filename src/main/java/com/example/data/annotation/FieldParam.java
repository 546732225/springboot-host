package com.example.data.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FieldParam {


    String dictCode() default "";


    String dictValueFiled() default "";


    String dictNameFiled() default "";

    int type() default 0;
}
