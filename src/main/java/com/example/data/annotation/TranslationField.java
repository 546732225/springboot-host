package com.example.data.annotation;

import java.lang.annotation.*;

/**
 * 翻译字典值注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface TranslationField {

    FieldParam[] value();
}
