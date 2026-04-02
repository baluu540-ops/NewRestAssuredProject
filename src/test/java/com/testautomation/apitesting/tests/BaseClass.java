package com.testautomation.apitesting.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    public WebDriver driver;
    public BaseClass() {

    }
    public void launchBrowser() {
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://www.google.com");
    }
}
