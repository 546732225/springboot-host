package com.example.data.cache;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CacheKeyGenerator {


    String getLockKey(ProceedingJoinPoint joinPoint);
}
