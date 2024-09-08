package com.dtlabs.httpmethods;

import com.dtlabs.spotify.oauth2.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class HttpMethods {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "https://api.postman.com";
    }

    @Ignore
    @Test
    public void validateGetRequest(){
        given().
                //baseUri("https://api.postman.com").
                header("X-Api-Key", ConfigLoader.getInstance().getXAPIKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200)
                .body("workspaces.name", hasItems("Public APIs", "Mukesh-Otwani", "Postman-Webniar"),
                        "workspaces.type", hasItems("team", "team", "team"),
                        "workspaces[0].name",equalTo("Public APIs"),
                        "workspaces.size()", equalTo(20));
    }
    @Ignore
    @Test
    public void extract_response(){

        Response response = given()
                .header("X-Api-Key", ConfigLoader.getInstance().getXAPIKey())
        .when()
                .get("/workspaces")
        .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        System.out.println("Response-"+ response.asString());
    }
    @Ignore
    @Test
    public void extract_single_value_from_response(){

        String name = given()
                .header("X-Api-Key", ConfigLoader.getInstance().getXAPIKey())
                .when()
                .get("/workspaces")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().path("workspaces[0].name");

        System.out.println("Workspace name-"+ name);

        //using hamcrest outside
        assertThat(name, equalTo("workspaces[0].name"));
        //System.out.println("Workspace name-"+ JsonPath.from(response).getString("workspaces[0].name"));

        //JsonPath jsonPath = new JsonPath(response.asString());
        //System.out.println("Workspace name-"+ jsonPath.getString("workspaces[0].name"));
        //System.out.println("Workspace name-"+ response.path("workspaces[0].name"));
    }

    @Test
    public void request_response_logging(){

        given().
                header("X-Api-Key", ConfigLoader.getInstance().getXAPIKey()).
        when().
                get("/workspaces").
        then().
                log().ifError().
                assertThat().
                statusCode(200);

    }
}
