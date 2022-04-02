package com.example.data.aop;

import com.example.data.annotation.DictParam;
import com.example.data.annotation.TranslationDict;
import com.example.data.util.GsonUtil;
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


@Order(-1)
@Aspect
@Component
public class TransformDictionaryAspect {


    @Pointcut("@annotation(translationDict)")
    public void pointcut(TranslationDict translationDict) {
    }

    @Around(value = "pointcut(translationDict)", argNames = "joinPoint,translationDict")
    public Object after(ProceedingJoinPoint joinPoint, TranslationDict translationDict) throws Throwable {
        Object result = joinPoint.proceed();

        System.out.println(result);
        // 第一步、获取返回值类型
        Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        //首先，取出要翻译字段的字典值
        String returnJsonResult = GsonUtil.gsonString(result);
        DictParam[] dictParams = translationDict.value();
        if (result instanceof Collection) {
            List<Map<String, Object>> maps = GsonUtil.gsonToListMaps(returnJsonResult);
            maps.forEach(map -> {
                for (DictParam dictParam : dictParams) {
                    if (map.containsKey(dictParam.dictValueFiled())) {
                        map.put(dictParam.dictValueFiled() + "Text", "男");
                    }
                }
            });
            return maps;
        }


        Map<String, Object> map = GsonUtil.gsonToMaps(returnJsonResult);
        for (DictParam dictParam : dictParams) {
            if (map.containsKey(dictParam.dictValueFiled())) {
                map.put(dictParam.dictValueFiled() + "Text", "男");
            }
        }
        return map;


    }


}
