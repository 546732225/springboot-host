package com.example.data.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author seven
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = CheckSqlInjectionValidator.class)
public @interface CheckSqlInjection {

    String message() default "参数存在SQL注入风险";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};





}
