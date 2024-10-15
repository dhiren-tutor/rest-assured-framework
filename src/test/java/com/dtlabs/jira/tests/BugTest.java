package com.dtlabs.jira.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BugTest {
    String issueId;
    @BeforeTest
    public void setUp(){
        RestAssured.baseURI = "https://dhirent.atlassian.net/"; //https://dhirent.atlassian.net/rest/api/3/issue/10000/attachments
    }
    @Test
    public void createBug(){

        String payload = "{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \"SCRUM\"\n" +
                "       },\n" +
                "       \"summary\": \"[Automation]-Links are not working\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "       }\n" +
                "   }\n" +
                "}\n";
        String issueResponse = given().
                header("Content-Type", "application/json").
                header("Authorization", "Basic ZGhpcmVudC4yMDIwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBzU2JmUmFidTlFM3NZN0dZQXVYRGYxTWppR01JV2hyMFllNG9TWllwTzg4U3dfVkhRTTYzSE5sRTZuaVR1V0NpWnFWV1pGOUF1TXZReWE5cHpfMGdWRG41OEJWWHdFcDFILWxDY0hVeXlQSXR5TTNBcklCcnVwWmM4MTNZNl9Xd1h0dG95RmRaR3RUUHlCZEdBS3VrdEt6M2g2M3VlN25fSkRyVzIySndPQ289ODg0Q0I4ODQ=").
                body(payload).
                log().all().
        when().
                post("rest/api/3/issue").
        then().
                log().all().
                assertThat().
        statusCode(201).
                extract().response().asString();

        JsonPath jsonPath = new JsonPath(issueResponse);
        issueId = jsonPath.getString("id");
        System.out.println(issueId);
    }

    @Test
    public void addAttachmentToBug(){
        issueId = "10001";
        given().
                header("X-Atlassian-Token", "no-check").
                header("Authorization", "Basic ZGhpcmVudC4yMDIwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBzU2JmUmFidTlFM3NZN0dZQXVYRGYxTWppR01JV2hyMFllNG9TWllwTzg4U3dfVkhRTTYzSE5sRTZuaVR1V0NpWnFWV1pGOUF1TXZReWE5cHpfMGdWRG41OEJWWHdFcDFILWxDY0hVeXlQSXR5TTNBcklCcnVwWmM4MTNZNl9Xd1h0dG95RmRaR3RUUHlCZEdBS3VrdEt6M2g2M3VlN25fSkRyVzIySndPQ289ODg0Q0I4ODQ=").
                multiPart("file", new File("C:\\Users\\Dhire\\Downloads\\tshirt-pumas.jpg")).
                pathParams("key",issueId).
        when().
                post("rest/api/3/issue/{key}/attachments").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}
