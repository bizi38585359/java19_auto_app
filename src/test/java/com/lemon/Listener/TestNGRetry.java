package com.lemon.Listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestNGRetry implements IRetryAnalyzer {
    //最大的重试次数
    public int maxRetryCount = 2;
    //当前的重试次数
    public int currentRetryCount = 0;
    //日志
    private Logger logger = Logger.getLogger(TestNGRetry.class);
    @Override
    public boolean retry(ITestResult iTestResult) {
        //如果retry方法返回的为false，表示不会去执行重试机制
        //如果retry方法返回的为true，表示会去执行重试机制
        if(currentRetryCount < maxRetryCount){
            currentRetryCount++;
            logger.info("当前运行第【" + currentRetryCount + "】次重试机制");
            return true;
        }else {
            return false;
        }
    }
}
