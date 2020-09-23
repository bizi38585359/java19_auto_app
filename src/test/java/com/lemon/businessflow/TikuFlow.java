package com.lemon.businessflow;

import com.lemon.common.BaseFlow;
import com.lemon.pageobject.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TikuFlow extends BaseFlow {
    public Boolean tikuAnswer(){
        //1、随机选择领域标题
        List<WebElement> allElements = getAllElements(TikuPage.fieldTitleBy);
        //Random类-->随机类
        Random random = new Random();
        int index = random.nextInt(allElements.size());
//        WebElement selectedWebElement = allElements.get(index);
//        click(selectedWebElement);
        click(allElements.get(index));
        //2、随机选择难度级别
        int indexLevel = random.nextInt(3);
//        WebElement selectedLevel = allElements.get(indexLevel);
        /*if(indexLevel == 0){
            click(DiffcultLevelPage.firstLevelBy);
        }else if(indexLevel == 1){
            click(DiffcultLevelPage.secondLevelBy);
        }else if(indexLevel == 2){
            click(DiffcultLevelPage.thirdLevelBy);
        }*/
        List<By> diffcultElement = new ArrayList<By>();
        diffcultElement.add(DiffcultLevelPage.firstLevelBy);
        diffcultElement.add(DiffcultLevelPage.secondLevelBy);
        diffcultElement.add(DiffcultLevelPage.thirdLevelBy);
        click(diffcultElement.get(indexLevel));
        //3、随机选择套题
        List<WebElement> subjectTitleElements = getAllElements(SuiteSubjectPage.suiteSubjectTitleBy);
        int indextitle = random.nextInt(subjectTitleElements.size());
        click(subjectTitleElements.get(indextitle));
        //4、点击答案的开关
        click(SubjectDetailPage.answerSwitchBy);
        //等待答案内容是否可见
        Boolean isAnswerBodyVisible = waitElementVisible(SubjectDetailPage.answerBodyBy).isDisplayed();
        //返回到首页
        pressBack();
        pressBack();
        pressBack();
        //返回--答案显示与否
        return isAnswerBodyVisible;
    }

    //题库收藏功能
    public List<String> tikuCollection() throws InterruptedException {
        List<String> data = new ArrayList<>();
        //获取到当前收藏数量
        click(IndexPage.mylemonBy);
        String beforeCollectionCount = getElementText(MylemonPage.collectionCountBy);
        click(IndexPage.tikuBy);
        //1、随机选择领域标题
        List<WebElement> allElements = getAllElements(TikuPage.fieldTitleBy);
        //Random类-->随机类
        Random random = new Random();
        int index = random.nextInt(allElements.size());
        click(allElements.get(index));
        //2、随机选择难度级别
        int indexLevel = random.nextInt(3);
        List<By> diffcultElement = new ArrayList<By>();
        diffcultElement.add(DiffcultLevelPage.firstLevelBy);
        diffcultElement.add(DiffcultLevelPage.secondLevelBy);
        diffcultElement.add(DiffcultLevelPage.thirdLevelBy);
        click(diffcultElement.get(indexLevel));
        //3、随机选择套题
        List<WebElement> subjectTitleElements = getAllElements(SuiteSubjectPage.suiteSubjectTitleBy);
        int indextitle = random.nextInt(subjectTitleElements.size());
        click(subjectTitleElements.get(indextitle));
        //4、点击收藏按钮
        click(SubjectDetailPage.collectionBy);
        //5、获取提示信息：收藏成功
        String tips = getToastText("收藏成功");
        //返回到首页
        Thread.sleep(2000);
        pressBack();
        Thread.sleep(2000);
        pressBack();
        Thread.sleep(2000);
        pressBack();
        Thread.sleep(2000);
        //收藏之后我的柠檬页面的收藏数量
        click(IndexPage.mylemonBy);
        String afterCollectionCount = getElementText(MylemonPage.collectionCountBy);
        data.add(tips);
        data.add(beforeCollectionCount);
        data.add(afterCollectionCount);
        return data;
    }

//    public static void main(String[] args){
//        Random random = new Random();
//        System.out.println(random.nextInt(10));
//    }
}
