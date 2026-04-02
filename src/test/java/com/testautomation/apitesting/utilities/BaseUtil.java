package com.testautomation.apitesting.utilities;


import com.testautomation.apitesting.tests.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static reports.ExtentManager.datefolder;

public class BaseUtil extends BaseClass {
  public String screenshot;
  public static String getScreenShot(WebDriver driver,String screenshotname) throws IOException {
    //String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    TakesScreenshot ts = (TakesScreenshot)driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    String destination = System.getProperty("user.dir")+"/extentreports/"+datefolder+"/"+"screenshots"+"/"+screenshotname+".png";
    File finaldestination = new File(destination);
    FileUtils.copyFile(source, finaldestination);
    return destination;
  }

}
