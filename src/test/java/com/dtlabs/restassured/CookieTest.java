package com.dtlabs.restassured;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @Test
    public void testCookie(){

        given().

        when().
                get("https://www.google.com").
        then().
                cookie("AEC","").
                log().all();
    }

    @Test
    public void getCookie(){

        Response res = given().

                when().
                get("https://www.google.com");

        // Get Single Cookie
        String cookie_value = res.getCookie("AEC");

        // Get all cookie
        Map<String, String> cookies_value = res.getCookies();


    }

}
