package com.example.data.aop;

import com.example.data.annotation.FieldParam;
import com.example.data.annotation.TranslationField;
import com.example.data.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;


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
        // 第一步、获取返回值类型
        Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        //首先，取出要翻译字段的字典值
        String returnJsonResult = GsonUtils.gsonString(result);
        FieldParam[] fieldParams = translationField.value();
        if (result instanceof Collection) {
            List<Map<String, Object>> maps = GsonUtils.gsonToListMaps(returnJsonResult);
            maps.forEach(map -> {
                for (FieldParam fieldParam : fieldParams) {
                    if (map.containsKey(fieldParam.dictValueFiled())) {
                        map.put(fieldParam.dictValueFiled() + "Text", "男");
                    }
                }
            });
            return maps;
        }
        return result;
    }


}
