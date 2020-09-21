package com.test.day04;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class MultiTouchTest {
    public AndroidDriver androidDriver;
    @BeforeTest
    public void setUp() throws MalformedURLException, InterruptedException {
        //所需功能（类），把下面的四个配置保存到其中
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //1、确认测试平台是Android or IOS --platformName配置名
        desiredCapabilities.setCapability("platformName","Android");
        //2、找到测试设备 --deviceName配置名
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //3、找到要测试的App  --appPackage配置名
        desiredCapabilities.setCapability("appPackage","com.baidu.BaiduMap");
        //4、启动测试App
        desiredCapabilities.setCapability("appActivity","com.baidu.baidumaps.WelcomeScreen");
        //noReset设置为true，不清除APP数据
        desiredCapabilities.setCapability("noReset",true);
        //把配置发送给Appium服务器，需要知道Appium服务器地址127.0.0.1:4723
        //http://127.0.0.1:4723/wd/hub 接口地址
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //创建驱动对象AndroidDriver
        //在创建对象过程中，Appium服务器会进行一些初始化工作，并且和设备端建立通讯连接
        androidDriver = new AndroidDriver(url,desiredCapabilities);
//        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(5000);
    }

    @Test
    public void testZoom() throws InterruptedException {
        Thread.sleep(20000);
        zoom();
        for(int i=0;i<5;i++){
            pinch();
            Thread.sleep(2000);
        }
        for(int i=0;i<5;i++){
            zoom();
            Thread.sleep(2000);
        }
    }

    //自定义放大手势动作
    public void zoom() throws InterruptedException {
        int x = androidDriver.manage().window().getSize().getWidth();
        int y = androidDriver.manage().window().getSize().getHeight();
        /*Duration duration = Duration.ofMillis(500);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);*/
        //放大的手势动作实现
        //第一根手指 ，从B->A  2*x/5,2*y/5--》1*x/5,1*y/5
        PointOption pointOptionA = PointOption.point(x / 5, y / 5);
        PointOption pointOptionB = PointOption.point(2 * x / 5, 2 * y / 5);
        TouchAction touchAction1 = new TouchAction(androidDriver);
        touchAction1.press(pointOptionB).moveTo(pointOptionA).release();
        //第二根手指 ，从C->d  3*x/5,3*y/5--》4*x/5,4*y/5
        PointOption pointOptionC = PointOption.point(3 * x / 5, 3 * y / 5);
        PointOption pointOptionD = PointOption.point(4 * x / 5, 4 * y / 5);
        TouchAction touchAction2 = new TouchAction(androidDriver);
        touchAction2.press(pointOptionC).moveTo(pointOptionD).release();
        //必须要保证两根手指同时执行，不能先后执行，所以不能再touchAction调用perform方法
        //多点触摸对象MultiTouchAction
        MultiTouchAction multiTouchAction = new MultiTouchAction(androidDriver);
        multiTouchAction.add(touchAction1);
        multiTouchAction.add(touchAction2);
        multiTouchAction.perform();
    }

    //自定义缩小手势动作
    public void pinch() throws InterruptedException {
        int x = androidDriver.manage().window().getSize().getWidth();
        int y = androidDriver.manage().window().getSize().getHeight();
        /*Duration duration = Duration.ofMillis(500);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);*/
        //放大的手势动作实现
        //第一根手指 ，从A->B  1*x/5,1*y/5--》2*x/5,2*y/5
        PointOption pointOptionA = PointOption.point(x / 5, y / 5);
        PointOption pointOptionB = PointOption.point(2 * x / 5, 2 * y / 5);
        TouchAction touchAction1 = new TouchAction(androidDriver);
        touchAction1.press(pointOptionA).moveTo(pointOptionB).release();
        //第二根手指 ，从D->C  4*x/5,4*y/5--》3*x/5,3*y/5
        PointOption pointOptionC = PointOption.point(3 * x / 5, 3 * y / 5);
        PointOption pointOptionD = PointOption.point(4 * x / 5, 4 * y / 5);
        TouchAction touchAction2 = new TouchAction(androidDriver);
        touchAction2.press(pointOptionD).moveTo(pointOptionC).release();
        //必须要保证两根手指同时执行，不能先后执行，所以不能再touchAction调用perform方法
        //多点触摸对象MultiTouchAction
        MultiTouchAction multiTouchAction = new MultiTouchAction(androidDriver);
        multiTouchAction.add(touchAction1);
        multiTouchAction.add(touchAction2);
        multiTouchAction.perform();
    }
}
