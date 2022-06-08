package com.example.data.aspect;

import com.example.data.annotation.FieldParam;
import com.example.data.annotation.TranslationField;
import com.example.data.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Order(-1)
@Aspect
@Component
public class TransformDictionaryAspect {
    
    
    @Pointcut("@annotation(translationField)")
    public void pointcut(TranslationField translationField) {
    }
    
    @Around(value = "pointcut(translationField)", argNames = "joinPoint,translationField")
    public Object after(ProceedingJoinPoint joinPoint, TranslationField translationField) throws Throwable {
        Object result = joinPoint.proceed();
        // 获取返回值类型
        Class<?> clazz = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        //首先，取出要翻译字段的字典值
        List<FieldParam> fieldParams = new ArrayList<>();
        Collections.addAll(fieldParams, translationField.value());
        if (fieldParams.isEmpty()) {
            fieldParams.addAll(Arrays.stream(clazz.getDeclaredFields())
                    .filter(item -> item.getAnnotation(FieldParam.class) != null)
                    .map(item -> item.getAnnotation(FieldParam.class))
                    .collect(Collectors.toList()));
        }
        
        //给增加的属性赋值
        if (result instanceof Collection) {
            List<Map<String, Object>> maps = GsonUtils.gsonToListMaps(GsonUtils.gsonString(result));
            maps.forEach(map -> extracted(map, fieldParams));
            return maps;
        } else {
            BeanWrapper beanWrapper = new BeanWrapperImpl(result);
            fieldParams.forEach(item -> beanWrapper.setPropertyValue(item.targetFiled(), "女"));
        }
        return result;
    }
    
    private void extracted(Map<String, Object> map, List<FieldParam> fieldParams) {
        for (FieldParam fieldParam : fieldParams) {
            if (map.containsKey(fieldParam.sourceFiled())) {
                map.put(fieldParam.targetFiled(), "男");
            }
        }
    }
    
    
}
