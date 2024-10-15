package com.dtlabs.jira.apis;

import com.dtlabs.spotify.oauth2.api.RestResource;
import com.dtlabs.spotify.oauth2.pojo.Playlist;
import com.dtlabs.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.dtlabs.spotify.oauth2.api.Routes.PLAYLISTS;
import static com.dtlabs.spotify.oauth2.api.Routes.USERS;
import static com.dtlabs.spotify.oauth2.api.TokenManager.getToken;

public class JiraAPIs {

    public static Response post(Playlist requestPlaylist){

        return RestResource.post(USERS + "/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS, getToken(), requestPlaylist);
    }
}
