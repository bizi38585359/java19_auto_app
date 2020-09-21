package com.lemon.common;

import com.lemon.constants.GlobalConstants;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    //创建ThreadLocal线程保护区域变量（静态类型）
    private static ThreadLocal<AndroidDriver>  threadLocal = new ThreadLocal<AndroidDriver>();
    public AndroidDriver androidDriver;
    private Logger logger = Logger.getLogger(BaseTest.class);
    public void openApp(String systemPort,String udid,String appiumPort) throws MalformedURLException{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", GlobalConstants.PLATFORM_NAME);
        desiredCapabilities.setCapability("deviceName",GlobalConstants.DEVICE_NAME);
        desiredCapabilities.setCapability("udid",udid);
        desiredCapabilities.setCapability("appPackage",GlobalConstants.APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity",GlobalConstants.APP_ACTIVITY);
        desiredCapabilities.setCapability("systemPort",systemPort);
        URL url = new URL("http://127.0.0.1:" + appiumPort + "/wd/hub");
        androidDriver = new AndroidDriver(url,desiredCapabilities);
        //把AndroidDriver保存到线程本地变量中去--存只需要存储一次
        threadLocal.set(androidDriver);
//        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("===========打开测试App===========");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //从线程本地变量区域中取得AndroidDriver
    public static AndroidDriver getAndroidDriver(){
        return threadLocal.get();
    }

    @Parameters({"systemPort","udid","appiumPort"})
    @BeforeTest
    public void setUpSuite(String systemPort,String udid,String appiumPort) throws MalformedURLException {
        //打开测试App
        openApp(systemPort,udid,appiumPort);
    }

    @AfterTest
    public void tearDownSuite(){
        //打开测试App
        quitApp();
    }

    public void quitApp(){
        threadLocal.get().quit();
//        androidDriver.quit();
        logger.info("===========关闭测试App===========");
    }
}
