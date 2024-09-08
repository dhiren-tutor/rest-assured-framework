package com.dtlabs.spotify.oauth2.pojo;

public class Error {

    public InnerError getError() {
        return error;
    }

    public void setError(InnerError error) {
        this.error = error;
    }

    public InnerError error;
}
