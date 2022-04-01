package com.example.data.core;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CacheKeyGenerator {


    String getLockKey(ProceedingJoinPoint joinPoint);
}
