package com.dtlabs.spotify.oauth2.applicationApi;

import com.dtlabs.spotify.oauth2.api.RestResource;
import com.dtlabs.spotify.oauth2.pojo.Playlist;
import com.dtlabs.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.dtlabs.spotify.oauth2.api.Routes.PLAYLISTS;
import static com.dtlabs.spotify.oauth2.api.Routes.USERS;
import static com.dtlabs.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistAPI {

    //static String access_token="BQDAo_XDkNg77JsfQaSK3Bk7wqbUs86eIHA2KVahhk40y2MLNPAo4mo-e4o0nvKoHa8dnPODcsiwIKJLHGN8v8dvRgwZ3orrTc8jwforN_VkFHuVXvR1tkmgj56rutjShojbpwC0_Gs5Kpu9q2XALObU2rTmqvAYYkRyU3ZIrnMC6et0O1p7HsG4umprQfj1lxPNFgXaQHNtq7i2OX1Qi6VCj4j49zp9Pxc0UkZ33MaDpezv5IAJTaKh2sbx7PtPuxT2N-QHo2NX";

    public static Response post(Playlist requestPlaylist){

        return RestResource.post(USERS + "/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS, getToken(), requestPlaylist);
    }

    public static Response post(Playlist requestPlaylist, String token){

        return RestResource.post(USERS +"/"+ ConfigLoader.getInstance().getUserId()+PLAYLISTS, token, requestPlaylist);
    }

    public static Response get(String playlistId){

        return RestResource.get(PLAYLISTS+"/"+playlistId, getToken());
    }

    public static Response update(String playlistId, Playlist requestPlaylist){

        return RestResource.update(PLAYLISTS+"/"+playlistId, getToken(), requestPlaylist);
    }
}
