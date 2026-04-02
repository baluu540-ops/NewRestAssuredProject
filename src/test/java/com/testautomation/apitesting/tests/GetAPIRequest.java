package com.testautomation.apitesting.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.*;
import reports.ExtentManager;

public class GetAPIRequest {
    ExtentReports extentreport;
    ExtentTest testreport;
    @BeforeTest
    public void setup(){
        extentreport = ExtentManager.getextentReports();
        testreport = extentreport.createTest("GET API Automation Report");
        testreport.log(Status.INFO, "GET API Test Case Started");
    }
    @AfterTest
    public void quit(){
        extentreport.flush();
    }
    @Test
    public void GetAllBookings(){
        Response response =
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                    .get("")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .header("Content-Type","application/json; charset=utf-8")
                    .header("Server","Heroku")
                    .extract()
                    .response();
        testreport.log(Status.INFO, response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains("bookingid"));
        testreport.log(Status.PASS, "GET API Test Case Completed");
    }

}
