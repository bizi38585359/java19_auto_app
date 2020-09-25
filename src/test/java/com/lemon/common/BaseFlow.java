package com.lemon.common;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFlow {
    private Logger logger = Logger.getLogger(BaseFlow.class);
    public WebElement waitElementVisible(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(BaseTest.getAndroidDriver(),20);
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitElementClickable(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(BaseTest.getAndroidDriver(),20);
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitElementPresence(By locator){
        WebDriverWait webDriverWait = new WebDriverWait(BaseTest.getAndroidDriver(),20);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    //获取相同定位的所有元素
    public List<WebElement> getAllElements(By locator){
        waitElementVisible(locator);
        List<WebElement> allElements = BaseTest.getAndroidDriver().findElements(locator);
        return allElements;
    }

    //可以让业务流更加清晰，可读性更好，统一的日志处理（操作）
    public void input(By locator,String data){
        logger.info("给元素【" + locator + "】输入数据【" + data + "】");
        waitElementVisible(locator).sendKeys(data);
    }

    public void click(By locator){
        logger.info("点击了元素【" + locator + "】");
        waitElementClickable(locator).click();
    }

    public void click(WebElement webElement){
        logger.info("点击了元素【" + webElement + "】");
        webElement.click();
    }

    //封装的公共获取Toast文本的方法
    public String getToastText(String partialToastText){
        String toasttext = waitElementPresence(MobileBy.xpath("//*[contains(@text,'" +partialToastText+ "')]")).getText();
        logger.info("获取toast信息文本【" + toasttext + "】");
        return toasttext;
    }

    //封装的公共获取元素文本的方法
    public String getElementText(By locator){
        String text = waitElementVisible(locator).getText();
        logger.info("获取元素【" + locator + "】文本值【" + text + "】");
        return text;
    }

    //封装的公共获取控件文本的方法
    public String getPresenceElementText(By locator){
        String text = waitElementPresence(locator).getText();
        logger.info("获取元素【" + locator + "】文本值【" + text + "】");
        return text;
    }

    //点击返回键再点击题库进入登录页面
    public void pressBack(){
        //实例化按键对象
        KeyEvent keyEvent = new KeyEvent(AndroidKey.BACK);
        logger.info("返回至上一级页面");
        BaseTest.getAndroidDriver().pressKey(keyEvent);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
