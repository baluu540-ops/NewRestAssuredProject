package com.testautomation.apitesting.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testautomation.apitesting.utilities.BaseUtil;
import io.restassured.RestAssured;
import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
import org.testng.annotations.*;
import reports.ExtentManager;


public class PostAPIRequest extends BaseUtil {
ExtentReports extentreport;
ExtentTest testreport;
    @BeforeTest
    public void setup(){
        extentreport = ExtentManager.getextentReports();
        testreport = extentreport.createTest("POST API Automation Report");
        testreport.log(Status.INFO, "POST API Test Case Started");
    }
    @AfterTest
    public void quit(){
        extentreport.flush();
    }
    @Test
    public void postapirequest(){
        //RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("firstname", "apitesting");
        jsonObject1.put("lastname", "tutorial");
        jsonObject1.put("totalprice", 1000);
        jsonObject1.put("depositpaid", true);
        jsonObject1.put("additionalneeds", "super bowls");
        jsonObject1.put("bookingdates", jsonObject2);
        jsonObject2.put( "checkin", "2018-01-01");
        jsonObject2.put("checkout", "2019-01-01");
        testreport.log(Status.PASS,"Request body is : "+jsonObject1.toString());
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject1.toString())
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                    //.log().all()
                .when()
                    .post()
                .then()
                    .assertThat()
                    .statusCode(200)
                    .body("booking.firstname", Matchers.equalTo("apitesting"))
                    .body("booking.bookingdates.checkin", Matchers.equalTo("2018-01-01"))
                    .header("Content-Type","application/json; charset=utf-8");
        testreport.log(Status.INFO,"POST API Test Case Completed");
                    //.log().all();
                //.log().ifValidationFails();
    }
}
