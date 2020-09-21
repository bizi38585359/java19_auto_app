package com.lemon.pageobject;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class SubjectDetailPage {
    //题目的详细页面中的答案开关
    public static By answerSwitchBy = MobileBy.id("com.lemon.lemonban:id/switch_button");
    //答案的显示区域
    public static By answerBodyBy = MobileBy.id("com.lemon.lemonban:id/tvBody");
    //收藏按钮--根据content-desc进行定位
    public static By collectionBy = MobileBy.AccessibilityId("收藏");
}
