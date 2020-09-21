package com.test.day05;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class SmallProgramTest {
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
        desiredCapabilities.setCapability("appPackage","com.tencent.mm");
//        desiredCapabilities.setCapability("appPackage","com.xxzb.fenwoo");
        //4、启动测试App
        desiredCapabilities.setCapability("appActivity","com.tencent.mm.ui.LauncherUI");
//        desiredCapabilities.setCapability("appActivity","com.xxzb.fenwoo.activity.addition.WelcomeActivity");
        //noReset设置为true，不清除APP数据
        desiredCapabilities.setCapability("noReset",true);

        // 支持X5内核应用自动化配置
        desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
        // ChromeOptions使用来定制启动选项，因为在appium中切换context识别webview的时候,
        // 由于小程序是在一个单独的进程中，所以需要加上androidProcess:
        //com.tencent.mm:appbrand0
        ChromeOptions options = new ChromeOptions();
        // 第二个参数要改成你查询到的小程序进程名
        options.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // 初始化会默认将chrome浏览器打开，需要将Browser置为空
        desiredCapabilities.setBrowserName("");

        //把配置发送给Appium服务器，需要知道Appium服务器地址127.0.0.1:4723
        //http://127.0.0.1:4723/wd/hub 接口地址
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //创建驱动对象AndroidDriver
        //在创建对象过程中，Appium服务器会进行一些初始化工作，并且和设备端建立通讯连接
        androidDriver = new AndroidDriver(url,desiredCapabilities);
//        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }
    @Test
    public void testWechat() throws InterruptedException {
        //等待主页加载完毕，下滑
        Thread.sleep(5000);
        //下滑。加载小程序列表
        swipeDown(1000);
        //选择“柠檬班软件...”小程序，点击
        androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬班软件…\")")).click();
        //等待小程序加载结束
        Thread.sleep(5000);
        //获取到所有得context
//        System.out.println(androidDriver.getContextHandles());
        //小程序对应得context：WEBVIEW_小程序进程名 WEBVIEW_com.tencent.mm:appbrand0
        //切换context
        //报错：This version of ChromeDriver only supports Chrome(webview) version 74--->v2.40 Chrome
        //解决方案：不是按照常规的ChromeDriver的驱动版本匹配去找，而是按照固定的->对应的ChromeDriver版本v2.40
        androidDriver.context("WEBVIEW_com.tencent.mm:appbrand0");
        //坑--打开小程序之后会同时打开三个窗口-->切换到小程序真正对应的窗口
        //1、获取所有窗口的句柄
        Set<String> handles = androidDriver.getWindowHandles();
        //2、便利所有的句柄，去判断哪一个是真正的小程序页面
        for(String handle:handles){
            //根据标题来判断
            if(androidDriver.getTitle().equals("腾讯课堂柠檬班软件测试")){
                System.out.println(handle);
                break;
            }else{
                //切换句柄
                androidDriver.switchTo().window(handle);
            }
        }
        //定位小程序页面元素->操作
        androidDriver.findElement(MobileBy.xpath("//a[contains(text(),'课程')]")).click();
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
}
