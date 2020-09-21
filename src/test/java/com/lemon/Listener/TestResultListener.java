package com.lemon.Listener;

import com.lemon.common.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class TestResultListener implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        //保证能够正常的执行测试方法
        iHookCallBack.runTestMethod(iTestResult);
        if(iTestResult.getThrowable() != null){
            //调用截图
            byte[] screenshot = BaseTest.getAndroidDriver().getScreenshotAs(OutputType.BYTES);
            //把截图嵌入到Allure报表中去
            saveScreenShot(screenshot);
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenShot(byte[] screenShot){
        return screenShot;
    }
}
