package com.example.data.annotation;


import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheckSqlInjectionValidator implements ConstraintValidator<CheckSqlInjection, String> {

    private static final Set<String> set = new HashSet<>();

    static {
        String inj_str = "'|and|exec|replace|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|--|+|drop|substr|ascii|into|order by";
        set.addAll(Arrays.asList(inj_str.split("\\|")));
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(StringUtils.hasLength(value)){
            String[] values = value.split("\\s+");
            return Arrays.stream(values).noneMatch(item -> set.contains(item.toLowerCase()));
        }
        return true;
    }
}
