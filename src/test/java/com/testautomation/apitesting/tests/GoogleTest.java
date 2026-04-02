package com.testautomation.apitesting.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.Log;
import com.testautomation.apitesting.utilities.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reports.ExtentManager;

import java.io.IOException;

public class GoogleTest extends BaseClass{
    ExtentReports extentreport;
    ExtentTest testreport;
    @BeforeTest
    public void setup(){
       //extentreport = ExtentManager.getextentReports();
      // testreport = extentreport.createTest("Google Test Automation Report");
       //testreport.log(Status.INFO, "Google Test Case Started");
    }
    @AfterTest
    public void quit(){
        //extentreport.flush();

    }
    @BeforeMethod
    public void setup1() {
        extentreport = ExtentManager.getextentReports();
         testreport = extentreport.createTest("Google Test Automation Report");
        testreport.log(Status.INFO, "Google Test Case Started");
        Log.info("Google Test Case Started");
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE){
            String screenshot = BaseUtil.getScreenShot(driver,result.getName());
            testreport.log(Status.FAIL, "Test Case Failed is "+result.getName());
            testreport.log(Status.FAIL, testreport.addScreenCaptureFromPath(screenshot,"result.getName()").toString());
            Log.error("Test Case Failed is "+result.getName());
            //testreport.addScreenCaptureFromPath(failedscreenshot);
        }
         else if(result.getStatus()==ITestResult.SUCCESS){
            String screenshot = BaseUtil.getScreenShot(driver,result.getName());

            testreport.log(Status.PASS, "Test Case Passed is "+result.getName());
            testreport.log(Status.PASS, testreport.addScreenCaptureFromPath(screenshot,result.getName()).toString());
            Log.info("Test Case Passed is "+result.getName());
        }
         else if(result.getStatus()==ITestResult.SKIP){
            testreport.log(Status.SKIP, "Test Case Skipped is "+result.getName());
            Log.warn("Test Case Skipped is "+result.getName());
         }
        //extentreport.endTest(testreport);
        extentreport.flush();
         driver.quit();
    }
    @Test
    public void googleTest1(){
        Log.info("Launching Chrome1 Browser");
        launchBrowser();
        driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("Selenium");
        Log.info("Entered Selenium in Google Search Box");
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ENTER).perform();
        System.out.println(driver.getTitle());
        testreport.log(Status.INFO, "Google Test Case 1 Completed");
        Log.info("Google Test Case 1 Completed");
    }
    @Test
    public void googleTest2(){
        Log.info("Launching Chrome2 Browser");
        launchBrowser();
        driver.findElement(By.xpath("//*[@id='APjFqb']")).sendKeys("Selenium");
        Log.info("2 Entered Selenium in Google Search Box");
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ENTER).perform();
        System.out.println(driver.getTitle());
        testreport.log(Status.INFO, "Google Test Case 2 Completed");
        Log.info("Google Test Case 1 Completed");
    }
}
