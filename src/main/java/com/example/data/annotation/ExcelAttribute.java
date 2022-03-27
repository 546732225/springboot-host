package com.example.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: seven(546732225seven @ gmail.com)
 * @time: 2021/2/8 5:23 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAttribute {


    /**
     * 对应的列名称
     */
    String title();

    /**
     * 列序号
     */
    String column();

    /**
     * 对应的列类型 默认字符串类型
     */
    FiledFormatType type() default FiledFormatType.STRING;

    /**
     * 字段类型对应的格式
     */
    String format() default "";

    /**
     * 字段类型校验的格式
     */
    String[] validate() default "";

}
