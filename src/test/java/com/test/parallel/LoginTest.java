package com.test.parallel;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    public AndroidDriver androidDriver;
    @Parameters({"systemPort","udid","appiumPort"})
    @BeforeTest
    public void setUp(String systemPort,String udid,String appiumPort) throws MalformedURLException, InterruptedException {
        //所需功能（类），把下面的四个配置保存到其中
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //1、确认测试平台是Android or IOS --platformName配置名
        desiredCapabilities.setCapability("platformName","Android");
        //坑：Appium的，deviceName值随意写，结果都会选择第一台去执行
        //2、找到测试设备 --deviceName配置名
        desiredCapabilities.setCapability("deviceName","lemon");
        //udid--相当于设备标识符--唯一
        desiredCapabilities.setCapability("udid",udid);
        //systemPort -- Appoum和设备端交互的端口，默认为8200
        //所以多设备并发执行的时候
        //指定第一个线程对应Appium端口为4723，Appium和设备端交互的端口为8200
        //指定第一个线程对应Appium端口为4725，Appium和设备端交互的端口为8202
        //指定第一个线程对应Appium端口为4727，Appium和设备端交互的端口为8204
        //...
        desiredCapabilities.setCapability("systemPort",systemPort);
        //3、找到要测试的App  --appPackage配置名
        desiredCapabilities.setCapability("appPackage","com.lemon.lemonban");
        //4、启动测试App
        desiredCapabilities.setCapability("appActivity","com.lemon.lemonban.activity.WelcomeActivity");
//        //noReset设置为true，不清除APP数据
//        desiredCapabilities.setCapability("noReset",true);
        //把配置发送给Appium服务器，需要知道Appium服务器地址127.0.0.1:4723
        //http://127.0.0.1:4723/wd/hub 接口地址
        URL url = new URL("http://127.0.0.1:" + appiumPort + "/wd/hub");
        //创建驱动对象AndroidDriver
        //在创建对象过程中，Appium服务器会进行一些初始化工作，并且和设备端建立通讯连接
        androidDriver = new AndroidDriver(url,desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        Thread.sleep(5000);
    }

    @Test
    public void test_login_failure(){
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("13590408096");
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("123456");
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
    }
}
