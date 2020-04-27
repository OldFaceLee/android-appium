package com.ai.appium.support.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author: lixuejun
 * @date: Create in 2019/10/30 下午5:18
 * @description:
 */
public class TestNGListenerRetry implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer retry = iTestAnnotation.getRetryAnalyzer();
        if (retry == null) {
            iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}
