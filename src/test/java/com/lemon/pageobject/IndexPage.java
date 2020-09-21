package com.lemon.pageobject;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class IndexPage{
    //题库导航栏
    public static By tikuBy = MobileBy.id("com.lemon.lemonban:id/navigation_tiku");
    //我的柠檬导航栏
    public static By mylemonBy = MobileBy.id("com.lemon.lemonban:id/navigation_my");
}
