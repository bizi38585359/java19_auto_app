package com.test.day01;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstAppAuto {
    public static void main(String[] args) throws MalformedURLException {
        //所需功能（类），把下面的四个配置保存到其中
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //1、确认测试平台是Android or IOS --platformName配置名
        desiredCapabilities.setCapability("platformName","Android");
        //2、找到测试设备 --deviceName配置名
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //3、找到要测试的App  --appPackage配置名
        desiredCapabilities.setCapability("appPackage","com.lemon.lemonban");
        //4、启动测试App
        desiredCapabilities.setCapability("appActivity","com.lemon.lemonban.activity.WelcomeActivity");
        //把配置发送给Appium服务器，需要知道Appium服务器地址127.0.0.1:4723
        //http://127.0.0.1:4723/wd/hub 接口地址
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //创建驱动对象AndroidDriver
        //在创建对象过程中，Appium服务器会进行一些初始化工作，并且和设备端建立通讯连接
        AndroidDriver androidDriver = new AndroidDriver(url,desiredCapabilities);
    }
}
