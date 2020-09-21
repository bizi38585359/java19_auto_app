package com.test.day03;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ElementLocate {
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
        desiredCapabilities.setCapability("appPackage","com.lemon.lemonban");
        //4、启动测试App
        desiredCapabilities.setCapability("appActivity","com.lemon.lemonban.activity.WelcomeActivity");
        //把配置发送给Appium服务器，需要知道Appium服务器地址127.0.0.1:4723
        //http://127.0.0.1:4723/wd/hub 接口地址
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //创建驱动对象AndroidDriver
        //在创建对象过程中，Appium服务器会进行一些初始化工作，并且和设备端建立通讯连接
        androidDriver = new AndroidDriver(url,desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(5000);
    }

    @Test
    public void testLocate() throws InterruptedException {
        //1、id定位--根据resource-id属性定位
//        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
        //2、text定位-text属性S
//        androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"全程班\")")).click();
        //3、className定位--class属性用得极少，一般不用tagName
        //默认点击第一个
//        androidDriver.findElement(MobileBy.className("android.widget.TextView")).click();
        //4、Xpath
//        androidDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='就业信息']")).click();
//        androidDriver.findElement(MobileBy.
//                xpath("//android.widget.TextView[@resource-id='com.lemon.lemonban:id/category_title']")).click();
//        androidDriver.findElement(MobileBy.
//                xpath("//android.widget.FrameLayout[@resource-id='com.lemon.lemonban:id/navigation_tiku']" +
//                        "/android.widget.ImageView")).click();
        //5、AccessbilitiyID定位--根据元素content-desc属性
//        androidDriver.findElement(MobileBy.AccessibilityId("题库")).click();
        //6、坐标定位-只能点击
        //316.5、1236
        //（1）创建TouchAction对象--触摸动作
//        TouchAction touchAction = new TouchAction(androidDriver);
        //（2）把要操作得坐标转换成PointOption类型对象
//        PointOption pointOption = new PointOption().point(316,1236);
//        (3)通过touchAction动作去点击press:点击，release手指释放动作
//        touchAction.press(pointOption).release().perform();

        //toast特殊元素获取
        //点击登录
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
//        Thread.sleep(2000);
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
//        Thread.sleep(2000);
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("13590408096");
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("123456");
        androidDriver.findElement(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
        //通过Xpath获取toast
        //不知道toast提示什么时候出来
        Thread.sleep(2000);
        WebElement webElement = androidDriver.findElement(MobileBy.xpath("//*[contains(@text,'账号信息')]"));
        System.out.println(webElement.getText());
        //显示等待
//        WebDriverWait webDriverWait = new WebDriverWait(androidDriver,10);
//        WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.
//                xpath("//*[contains(@text,'账号信息')]")));
//        System.out.println(webElement.getText());
    }
}
