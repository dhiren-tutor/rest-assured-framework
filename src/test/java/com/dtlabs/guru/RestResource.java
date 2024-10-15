package com.dtlabs.guru;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class RestResource {

    final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

    @Test
    public static void getResponseBody(){

        given().
                queryParam("CUSTOMER_ID","68195").
                queryParam("PASSWORD","1234!").
                queryParam("Account_No","1").
        when().
                get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
                .body();
    }

    @Test
    public static void getResponseStatus(){

        given().
                queryParam("CUSTOMER_ID","68195").
                queryParam("PASSWORD","1234!").
                queryParam("Account_No","1").
                when().
                get("http://demo.guru99.com/V4/sinkministatement.php").
                then().assertThat().statusCode(200);
    }

    @Test
    public void getResponseTime(){
        System.out.println("Time taken to fetch response :"+
                get(url).timeIn(TimeUnit.MILLISECONDS)+"milliseconds");
    }

}
