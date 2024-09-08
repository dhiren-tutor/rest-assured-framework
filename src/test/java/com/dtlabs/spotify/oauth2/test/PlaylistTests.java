package com.dtlabs.spotify.oauth2.test;

import com.dtlabs.spotify.oauth2.applicationApi.PlaylistAPI;
import com.dtlabs.spotify.oauth2.applicationApi.StatusCode;
import com.dtlabs.spotify.oauth2.pojo.Error;
import com.dtlabs.spotify.oauth2.pojo.Playlist;
import com.dtlabs.spotify.oauth2.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests {

    @Test
    public void createPlaylist(){

        //builder pattern
        Playlist requestPlaylist = playlistbuilder("New Playlist", "New playlist description", false);

        Response response = PlaylistAPI.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());
        assertPlaylistEqual(requestPlaylist, response.as(Playlist.class));
    }

    @Test
    public void noPlaylistWithoutName(){
        Playlist requestPlaylist = playlistbuilder("", "New playlist description", false);
        Response response = PlaylistAPI.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400.getCode());

        assertError(response.as(Error.class), StatusCode.CODE_400.getCode(), "Missing required field: name");
    }

    @Test
    public void noPlaylistWithExpiredToken(){
        Playlist requestPlaylist = playlistbuilder("", "New playlist description", false);
        Response response = PlaylistAPI.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401.getCode());

        assertError(response.as(Error.class), StatusCode.CODE_401.getCode(), "Invalid access token");
    }

    @Test
    public void getPlaylist(){

        Playlist requestPlaylist = new Playlist();
        requestPlaylist.setName("New Playlist Name");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic(true);

        Response response = PlaylistAPI.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());

        Playlist responsePlaylist = response.as(Playlist.class);
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Test
    public void updatePlaylist(){

        Playlist requestPlaylist = new Playlist()
                .setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);

        Response response= PlaylistAPI.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    public Playlist playlistbuilder(String name, String description, boolean isPublic){
        return new Playlist()
                .setName(name)
                .setDescription(description)
                .setPublic(isPublic);
    }

    public void assertPlaylistEqual(Playlist requestPlaylist, Playlist responsePlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    public void assertStatusCode(int actualStatusCode, int expectedStatusCode){
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    public void assertError(Error responseErr, int expectedStatusCode, String expectedMessage){
        assertThat(responseErr.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseErr.getError().getMessage(), equalTo(expectedMessage));
    }
}
