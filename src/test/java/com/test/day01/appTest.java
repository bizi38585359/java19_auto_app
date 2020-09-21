package com.test.day01;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class appTest {

    private AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:62001");
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        //隐式等待设置一下
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() {
        MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("题库");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/button_go_login");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/et_mobile");
        el3.sendKeys("13590408096");
        MobileElement el4 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/et_password");
        el4.sendKeys("123456");
        MobileElement el5 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/btn_login");
        el5.click();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}