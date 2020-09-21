package com.lemon.pageobject;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class MylemonPage {
    //本周课程
    public static By weeklyCourseBy = MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_expend_title");
    //收藏数量
    public static By collectionCountBy = MobileBy.id("com.lemon.lemonban:id/fragment_my_lemon_collection_count");
}
