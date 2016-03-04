package com.audioservice.jeffchien.audios.rest;

import android.content.Context;

import com.audioservice.jeffchien.audios.rest.net.PersistentCookieStore;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class AudioSFactory {

    private Context mContext;

    private Gson mGson;
    private OkHttpClient mHttpClient;

    public AudioSFactory(){
        mGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        mHttpClient = new OkHttpClient();
    }

    public AudioSFactory setContext(Context ctx){
        mContext = ctx;
        return this;
    }

    public IAudioS create(){
        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(mContext),
                                        CookiePolicy.ACCEPT_ALL);
        mHttpClient.setCookieHandler(cookieManager);

        Retrofit retrofit = new Retrofit.Builder()
                        .client(mHttpClient)
                        .baseUrl(Constants.getBaseUriBuilder(false).toString())
                        .addConverterFactory(GsonConverterFactory.create(mGson))
                        .build();
        return retrofit.create(IAudioS.class);
    }
}
