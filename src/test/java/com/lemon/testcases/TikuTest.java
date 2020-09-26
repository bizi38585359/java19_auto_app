package com.lemon.testcases;

import com.lemon.businessflow.LoginFlow;
import com.lemon.businessflow.TikuFlow;
import com.lemon.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class TikuTest extends BaseTest {
    @BeforeTest
    public void setUp() throws MalformedURLException, InterruptedException {
        //1、打开测试App
//        openApp();
        //2、登录App
        LoginFlow loginFlow = new LoginFlow();
        loginFlow.loginSuccess("13590408096","408096");
    }
    @Test(priority = 3)
    public void testAnswer() throws InterruptedException {
        TikuFlow tikuFlow = new TikuFlow();
        Boolean isSuccess = tikuFlow.tikuAnswer();
        Assert.assertTrue(isSuccess);
    }

    @Test(priority = 4)
    public void testCollection() throws InterruptedException {
        TikuFlow tikuFlow = new TikuFlow();
        List<String> datas = tikuFlow.tikuCollection();
        //提示信息
        String actualValue = datas.get(0);
        //收藏之前的数量
        String beforeCollectionCount = datas.get(1);
        //收藏之前的数量
        String afterCollectionCount = datas.get(2);
        int actualCollectionCount = Integer.parseInt(afterCollectionCount) - Integer.parseInt(beforeCollectionCount);
        Assert.assertEquals(actualValue,"收藏成功");
        Assert.assertEquals(actualCollectionCount,1);
    }

//    @AfterTest
//    public void testDown() throws InterruptedException {
//        Thread.sleep(5000);
//        quitApp();
//    }
}
