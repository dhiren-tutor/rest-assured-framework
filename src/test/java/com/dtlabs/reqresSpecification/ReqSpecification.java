package com.dtlabs.reqresSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import static io.restassured.RestAssured.requestSpecification;

public class ReqSpecification {

    public void setUp(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("");
        requestSpecBuilder.addHeader("", "");

        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.
                query(requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
    }
}
