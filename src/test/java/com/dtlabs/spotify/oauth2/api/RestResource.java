package com.dtlabs.spotify.oauth2.api;

import com.dtlabs.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.dtlabs.spotify.oauth2.api.Routes.API;
import static com.dtlabs.spotify.oauth2.api.Routes.TOKEN;
import static com.dtlabs.spotify.oauth2.utils.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec())
                .formParams(formParams)
                .when().post(API+TOKEN)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String path, String token, Object requestPlaylist){

        return given(getRequestSpec())
                .body(requestPlaylist)
                .auth().oauth2(token)
                .when()
                .post(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, String token){

        return given(getRequestSpec())
                .auth().oauth2(token)
                .when()
                .get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response update(String path,String token, Object requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .auth().oauth2(token)
                .when()
                .put(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }
}
