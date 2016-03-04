package com.audioservice.jeffchien.audios.rest;

import android.net.Uri;

public class Constants {
    public static final String BASE_AUTHORITY = "130.211.246.16";
    public static final String BASE_API_PATH = "/api/v1";

    public static Uri.Builder getBaseUriBuilder(){
        return getBaseUriBuilder(true);
    }

    public static Uri.Builder getBaseUriBuilder(boolean appendPath){
        Uri.Builder builder = new Uri.Builder();

        builder.scheme("http")
                .authority(BASE_AUTHORITY);
        if(appendPath) builder.appendPath(BASE_API_PATH);

        return builder;
    }
}
