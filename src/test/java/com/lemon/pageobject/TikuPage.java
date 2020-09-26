package com.lemon.pageobject;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class TikuPage{
    //登录
    public static By goLoginBy = MobileBy.id("com.lemon.lemonban:id/button_go_login");
    public static By testBaseBy = MobileBy.AndroidUIAutomator("new UiSelector().text(\"软件测试基础\")");
    public static By fieldTitleBy = MobileBy.id("com.lemon.lemonban:id/fragment_category_type");
    public static By noContentBy = MobileBy.AndroidUIAutomator("new UiSelector().text(\"暂时木有内容呀~~\")");
}
