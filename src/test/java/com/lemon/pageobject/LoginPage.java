package com.lemon.pageobject;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class LoginPage{
    //手机号码输入元素信息
    public static By mobileBy = MobileBy.id("com.lemon.lemonban:id/et_mobile");
    //密码输入元素信息
    public static By passwordBy = MobileBy.id("com.lemon.lemonban:id/et_password");
    //登录按钮
    public static By loginBy = MobileBy.id("com.lemon.lemonban:id/btn_login");
}
