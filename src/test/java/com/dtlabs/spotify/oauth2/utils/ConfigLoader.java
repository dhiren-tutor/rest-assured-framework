package com.dtlabs.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("client_id not specified in the config.properties file");
    }
    public String getClientSecret(){
        String prop = properties.getProperty("client_secret");
        if(prop != null) return prop;
        else throw new RuntimeException("client_secret not specified in the config.properties file");
    }
    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if(prop != null) return prop;
        else throw new RuntimeException("grant_type not specified in the config.properties file");
    }
    public String getRefreshToken(){
        String prop = properties.getProperty("refresh_token");
        if(prop != null) return prop;
        else throw new RuntimeException("refresh_token not specified in the config.properties file");
    }
    public String getUserId(){
        String prop = properties.getProperty("user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("user_id not specified in the config.properties file");
    }

    public String getXAPIKey(){
        String prop = properties.getProperty("x_api_key");
        if(prop != null) return prop;
        else throw new RuntimeException("x_api_key not specified in the config.properties file");
    }
}
