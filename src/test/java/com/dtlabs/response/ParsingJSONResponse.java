package com.dtlabs.response;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParsingJSONResponse {

    @Test
    public void verifyJsonResponse(){

        Response res = given().
                when().get();
        JsonPath jo = new JsonPath(res.asString());
    }
}
