package com.test.day04;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SwipeTest02 {
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
        desiredCapabilities.setCapability("appPackage","com.xxzb.fenwoo");
        //4、启动测试App
        desiredCapabilities.setCapability("appActivity","com.xxzb.fenwoo.activity.addition.WelcomeActivity");
        //noReset设置为true，不清除APP数据
        desiredCapabilities.setCapability("noReset",true);
        //把配置发送给Appium服务器，需要知道Appium服务器地址127.0.0.1:4723
        //http://127.0.0.1:4723/wd/hub 接口地址
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //创建驱动对象AndroidDriver
        //在创建对象过程中，Appium服务器会进行一些初始化工作，并且和设备端建立通讯连接
        androidDriver = new AndroidDriver(url,desiredCapabilities);
//        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }

    @Test(enabled = false)
    public void testSwipe01() throws InterruptedException {
        PointOption pointOption1 = PointOption.point(147, 427);
        PointOption pointOption2 = PointOption.point(359, 430);
        PointOption pointOption3 = PointOption.point(567, 428);
        PointOption pointOption4 = PointOption.point(353, 644);
        PointOption pointOption5 = PointOption.point(152, 851);
        PointOption pointOption6 = PointOption.point(368, 847);
        PointOption pointOption7 = PointOption.point(569, 855);
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.press(pointOption1).moveTo(pointOption2).moveTo(pointOption3).moveTo(pointOption4).moveTo(pointOption5)
                .moveTo(pointOption6).moveTo(pointOption7).release().perform();
    }

    @Test(enabled = true)
    public void testSwipe02() throws InterruptedException {
        //兼容不同分辨率手机进行九宫格解锁
        //获取到控件的起始点坐标，宽度和高度
        WebElement webElement = androidDriver.findElement(MobileBy.id("com.xxzb.fenwoo:id/lpv_password"));
        int x = webElement.getLocation().getX();
        int y = webElement.getLocation().getY();
        int w = webElement.getSize().getWidth();
        int h = webElement.getSize().getHeight();
        //第一个圆圈的中心点坐标公式：x+1/6*w y+1/6*h
        //第二个圆圈的中心点坐标公式：x+3/6*w y+1/6*h
        //第三个圆圈的中心点坐标公式：x+5/6*w y+1/6*h
        //第四个圆圈的中心点坐标公式：x+3/6*w y+3/6*h
        //第五个圆圈的中心点坐标公式：x+1/6*w y+5/6*h
        //第六个圆圈的中心点坐标公式：x+3/6*w y+5/6*h
        //第七个圆圈的中心点坐标公式：x+5/6*w y+5/6*h
        PointOption pointOption1 = PointOption.point(x + w / 6, y + h / 6);
        PointOption pointOption2 = PointOption.point(x + 3 * w / 6, y + h / 6);
        PointOption pointOption3 = PointOption.point(x + 5 * w / 6, y + h / 6);
        PointOption pointOption4 = PointOption.point(x + 3 * w / 6, y + 3 * h / 6);
        PointOption pointOption5 = PointOption.point(x + w / 6, y + 5 * h / 6);
        PointOption pointOption6 = PointOption.point(x + 3 * w / 6, y + 5 * h / 6);
        PointOption pointOption7 = PointOption.point(x + 5 * w / 6, y + 5 * h / 6);
        //连续得多次滑动
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.press(pointOption1).moveTo(pointOption2).moveTo(pointOption3).moveTo(pointOption4).moveTo(pointOption5)
                .moveTo(pointOption6).moveTo(pointOption7).release().perform();
    }

    //向下滑动
    public void swipeDown(int swipeTime){
        //得到屏幕得宽度和高度
        int width = androidDriver.manage().window().getSize().getWidth();
        int height = androidDriver.manage().window().getSize().getHeight();
        //把原始坐标值转换为PointOption类型的
        //设置起始点坐标：width/2 height/4
        //设置终止点坐标：width/2 3*height/4
        //把原始坐标值转换为PointOption类型得
        PointOption pointOption1 = PointOption.point(width/2,height/4);
        PointOption pointOption2 = PointOption.point(width/2,3*height/4);
        TouchAction touchAction = new TouchAction(androidDriver);
        //创建WaitOptions类型的对象，里面保存滑动时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        //press:点击某个点   moveTo：移动到某个位置上去  waitAction:设置滑动的时间
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();
    }

    //向上滑动
    public void swipeUp(int swipeTime){
        //得到屏幕得宽度和高度
        int width = androidDriver.manage().window().getSize().getWidth();
        int height = androidDriver.manage().window().getSize().getHeight();
        //把原始坐标值转换为PointOption类型的
        //设置起始点坐标：width/2 3*height/4
        //设置终止点坐标：width/2 height/4
        //把原始坐标值转换为PointOption类型得
        PointOption pointOption1 = PointOption.point(width/2,3*height/4);
        PointOption pointOption2 = PointOption.point(width/2,height/4);
        TouchAction touchAction = new TouchAction(androidDriver);
        //创建WaitOptions类型的对象，里面保存滑动时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        //press:点击某个点   moveTo：移动到某个位置上去  waitAction:设置滑动的时间
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();
    }

    //向左滑动
    public void swipeLeft(int swipeTime){
        //得到屏幕得宽度和高度
        int width = androidDriver.manage().window().getSize().getWidth();
        int height = androidDriver.manage().window().getSize().getHeight();
        //把原始坐标值转换为PointOption类型的
        //设置起始点坐标：3*width/5 height/2
        //设置终止点坐标：width/5 height/2
        //把原始坐标值转换为PointOption类型得
        PointOption pointOption1 = PointOption.point(4*width/5,height/2);
        PointOption pointOption2 = PointOption.point(width/5,height/2);
        TouchAction touchAction = new TouchAction(androidDriver);
        //创建WaitOptions类型的对象，里面保存滑动时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        //press:点击某个点   moveTo：移动到某个位置上去  waitAction:设置滑动的时间
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();
    }

    //向右滑动
    public void swipeLeftRight(int swipeTime){
        //得到屏幕得宽度和高度
        int width = androidDriver.manage().window().getSize().getWidth();
        int height = androidDriver.manage().window().getSize().getHeight();
        //把原始坐标值转换为PointOption类型的
        //设置起始点坐标：width/4 height/2
        //设置终止点坐标：3*width/4 height/2
        //把原始坐标值转换为PointOption类型得
        PointOption pointOption1 = PointOption.point(width/4,height/2);
        PointOption pointOption2 = PointOption.point(3*width/4,height/2);
        TouchAction touchAction = new TouchAction(androidDriver);
        //创建WaitOptions类型的对象，里面保存滑动时间
        Duration duration = Duration.ofMillis(swipeTime);
        WaitOptions waitOptions = WaitOptions.waitOptions(duration);
        //press:点击某个点   moveTo：移动到某个位置上去  waitAction:设置滑动的时间
        touchAction.press(pointOption1).waitAction(waitOptions).moveTo(pointOption2).release().perform();
    }
}
