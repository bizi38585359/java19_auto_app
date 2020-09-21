package com.lemon.testcases;

import com.lemon.businessflow.LoginFlow;
import com.lemon.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
//    @BeforeTest
//    public void setUp() throws MalformedURLException{
//        openApp();
//    }

    @Test(dataProvider = "getLoginFailureDatas",priority = 1)
    public void testLoginFailure01(String mobile,String password,String toastText) throws InterruptedException {
        LoginFlow loginFlow = new LoginFlow();
        String actualValue = loginFlow.loginFailure(mobile,password,toastText);
        Assert.assertEquals(actualValue,toastText);
    }

    @Test(priority = 2)
    public void testLoginSuccess() throws InterruptedException {
//        waitElementClickable(MobileBy.id("com.lemon.lemonban:id/navigation_tiku")).click();
//        waitElementClickable(MobileBy.id("com.lemon.lemonban:id/button_go_login")).click();
//        waitElementVisible(MobileBy.id("com.lemon.lemonban:id/et_mobile")).sendKeys("13590408096");
//        waitElementVisible(MobileBy.id("com.lemon.lemonban:id/et_password")).sendKeys("123456");
//        waitElementClickable(MobileBy.id("com.lemon.lemonban:id/btn_login")).click();
//        WebElement webElement = waitElementPresence(MobileBy.xpath("//*[contains(@text,'账号信息')]"));
//        System.out.println(webElement.getText());
//        Assert.assertEquals(webElement.getText(),"错误的账号信息");

        LoginFlow loginFlow = new LoginFlow();
        Boolean isSuccess = loginFlow.loginSuccess("13590408096","408096");
        Assert.assertTrue(isSuccess);
    }

//    @Test
//    public void testLoginFailure03() throws InterruptedException {
//        LoginFlow loginFlow = new LoginFlow();
//        String actualValue = loginFlow.login("13590408096","12","密码格式不正确");
//        Assert.assertEquals(actualValue,"密码格式不正确");
//    }

    @DataProvider
    public Object[][] getLoginFailureDatas(){
        Object[][] datas = {{"13323234545","123456","错误的账号信息"},
                {"1332323454","123456","手机号码格式不正确"},
                {"13323234545","","手机号码或密码不能为空"},
                {"13323234545","12","密码格式不正确"}};
        return datas;
    }

//    @AfterTest
//    public void tearDown(){
//        quitApp();
//    }
}
