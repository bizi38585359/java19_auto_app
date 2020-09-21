package com.lemon.businessflow;

import com.lemon.common.BaseFlow;
import com.lemon.pageobject.IndexPage;
import com.lemon.pageobject.LoginPage;
import com.lemon.pageobject.TikuPage;

public class LoginFlow extends BaseFlow {

    public Boolean loginSuccess(String correctMobilePhone,String correctPassword) throws InterruptedException {
        //得到driver驱动对象
//        IndexPage indexPage = new IndexPage();
//        indexPage.waitElementClickable(indexPage.tikuBy).click();
//        TikuPage tikuPage = new TikuPage();
//        tikuPage.waitElementClickable(tikuPage.goLoginBy).click();
//        LoginPage loginPage = new LoginPage();
//        loginPage.waitElementVisible(loginPage.mobileBy).sendKeys(mobilePhone);
//        loginPage.waitElementVisible(loginPage.mobileBy).sendKeys(password);
//        loginPage.waitElementClickable(loginPage.loginBy).click();
//        waitElementClickable(IndexPage.tikuBy).click();
//        waitElementClickable(TikuPage.goLoginBy).click();
//        waitElementVisible(LoginPage.mobileBy).sendKeys(correctMobilePhone);
//        waitElementVisible(LoginPage.passwordBy).sendKeys(correctPassword);
//        waitElementClickable(LoginPage.loginBy).click();
        click(IndexPage.tikuBy);
        click(TikuPage.goLoginBy);
        input(LoginPage.mobileBy,correctMobilePhone);
        input(LoginPage.passwordBy,correctPassword);
        click(LoginPage.loginBy);
        Boolean isTestBaseVisible = waitElementVisible(TikuPage.testBaseBy).isDisplayed();
        return isTestBaseVisible;
    }

    public String loginFailure(String mobilePhone,String password,String toastText) throws InterruptedException {
        //得到driver驱动对象
//        IndexPage indexPage = new IndexPage();
//        indexPage.waitElementClickable(indexPage.tikuBy).click();
//        TikuPage tikuPage = new TikuPage();
//        tikuPage.waitElementClickable(tikuPage.goLoginBy).click();
//        LoginPage loginPage = new LoginPage();
//        loginPage.waitElementVisible(loginPage.mobileBy).sendKeys(mobilePhone);
//        loginPage.waitElementVisible(loginPage.mobileBy).sendKeys(password);
//        loginPage.waitElementClickable(loginPage.loginBy).click();
//        waitElementClickable(IndexPage.tikuBy).click();
//        waitElementClickable(TikuPage.goLoginBy).click();
//        waitElementVisible(LoginPage.mobileBy).sendKeys(mobilePhone);
//        waitElementVisible(LoginPage.passwordBy).sendKeys(password);
//        waitElementClickable(LoginPage.loginBy).click();
        click(IndexPage.tikuBy);
        click(TikuPage.goLoginBy);
        input(LoginPage.mobileBy,mobilePhone);
        input(LoginPage.passwordBy,password);
        click(LoginPage.loginBy);
        String toastTextTips = getToastText(toastText);
        Thread.sleep(3000);
        //回到起点页面-->点击返回键
        pressBack();
        pressBack();
        Thread.sleep(5000);
        //获取提示信息，等待元素可见
        return toastTextTips;
    }
}
