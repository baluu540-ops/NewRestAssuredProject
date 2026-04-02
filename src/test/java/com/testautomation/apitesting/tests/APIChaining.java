package com.testautomation.apitesting.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import reports.ExtentManager;

public class APIChaining {

    @Test
    public void APIChaining(){
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject1.put("firstname", "apichainingtest");
        jsonObject1.put("lastname", "testtutorial");
        jsonObject1.put("totalprice", 100);
        jsonObject1.put("depositpaid", true);
        jsonObject1.put("additionalneeds", "super bowl");
        jsonObject1.put("bookingdates", jsonObject2);
        jsonObject2.put( "checkin", "2026-01-01");
        jsonObject2.put("checkout", "2026-01-02");
        Response response=
        RestAssured
                .given()
                    .contentType("application/json")
                    .body(jsonObject1)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                    .post()
                .then()
                .statusCode(200)
                .extract().response();
        //String bookinngidnew=response.jsonPath().getJsonObject("bookingid").toString();
        int bookingidnew=response.path("bookingid");
        String str=response.path("booking.firstname");
        System.out.println("str : "+str);
        System.out.println("bookingidnew:"+bookingidnew);
        RestAssured
                .given()
                    .contentType("application/json")
                    .pathParam("bookingID",bookingidnew)
                    .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                    .get("{bookingID}")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .header("Content-Type","application/json; charset=utf-8")
                    .body("bookingdates.checkin", Matchers.equalTo("2026-01-01"));

    }
}
