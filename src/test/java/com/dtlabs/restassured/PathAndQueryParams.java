package com.dtlabs.restassured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParams {


    @Test
    public void testPathAndQueryParams(){
        //https://reqres.in/api/users?page=2&id=5
        given()
                .pathParams("mypath1", "users")
                .queryParam("page",2)
                .queryParam("id",5).
        when().
                get("https://reqres.in/api/{mypath1}").
        then().
               statusCode(200)
                .log().all();
    }
}
