package com.test.day05;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HybridTest {
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
        //noReset设置为true，不清除APP数据
        desiredCapabilities.setCapability("noReset",true);
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
    public void testBlog() throws InterruptedException {
        //1、进入到社区页面 --文本定位
        androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬社区\")")).click();
        //2、等待一下
        Thread.sleep(5000);
        //3、web页面元素定位、操作  原生模式页面-->web模式页面
        //切换context
        //获取到所有的context
        //System.out.println( androidDriver.getContextHandles());
        //切换context，之后转变成web元素操作
        //可能碰到的问题：web页面是通过chromeDriver驱动的，驱动和webview（Chrome浏览器）版本
        androidDriver.context("WEBVIEW_com.lemon.lemonban");
        //定位web页面的元素，操作
        androidDriver.findElement(MobileBy.xpath("//span[contains(text(),'注册')]")).click();
        Thread.sleep(5000);
        androidDriver.findElement(MobileBy.id("userPhone")).sendKeys("13590408096");
//        androidDriver.findElement(MobileBy.id("userEmail")).sendKeys("1425301899@qq.com");
        androidDriver.findElement(MobileBy.id("captcha")).sendKeys("sada");
        androidDriver.findElement(MobileBy.id("verifyRegister")).click();
        //如果web页面的元素操作完毕，记得切换回来
        androidDriver.context("NATIVE_APP");
    }
}
