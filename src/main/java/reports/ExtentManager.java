package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    static ExtentReports extentReports;
    public static String datefolder;
    public static ExtentReports getextentReports(){
        if(extentReports==null){
            /*Date d = new Date();
            String datee = d.toString().replace(":","-");*/
            datefolder=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            String reportspath = System.getProperty("user.dir")+"/extentreports/"+datefolder+"/";
            String screenshotspath = System.getProperty("user.dir")+"/extentreports/"+datefolder+"/screenshots/";
            File f = new File(screenshotspath);
            f.mkdirs(); //create the directories if it doesn't exist

            extentReports = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportspath);
            sparkReporter.config().setReportName("API Automation Report");
            sparkReporter.config().setDocumentTitle("Extent API Testing");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setEncoding("UTF-8");
            extentReports.attachReporter(sparkReporter);
            return extentReports;
        }
        return extentReports;
    }

}
