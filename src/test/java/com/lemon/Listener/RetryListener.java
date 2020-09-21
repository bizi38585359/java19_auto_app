package com.lemon.Listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {
    @Override
    public void  transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod){
        //1、得到@Test注解对应参数对象-->retryAnalyzer参数对象
        //annotation-->注解
        IRetryAnalyzer iRetryAnalyzer = annotation.getRetryAnalyzer();
        //2、判断@Test注解里面有没有加上retryAnalyzer
        if(iRetryAnalyzer == null){
            //if判断加黑名单，testMethod.getName()
            //3、给retryAnalyzer设置值：重试机制监听类
            annotation.setRetryAnalyzer(TestNGRetry.class);
        }
    }
}
