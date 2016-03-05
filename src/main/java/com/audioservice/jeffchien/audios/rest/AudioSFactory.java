package com.audioservice.jeffchien.audios.rest;

import android.content.Context;

import com.audioservice.jeffchien.audios.rest.net.PersistentCookieStore;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class AudioSFactory {

    private Context mContext;

    private Gson mGson;
    private OkHttpClient mHttpClient;

    private final List<Converter.Factory> mConverters = new ArrayList<>();

    public AudioSFactory(){
        //Default configurations
        mGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        mHttpClient = new OkHttpClient();

        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(mContext),
                                        CookiePolicy.ACCEPT_ALL);
        mHttpClient.setCookieHandler(cookieManager);
    }

    public AudioSFactory setContext(Context ctx){
        mContext = ctx;
        return this;
    }

    public AudioSFactory setGson(Gson gson){
        if(gson != null) mGson = gson;
        return this;
    }

    public AudioSFactory setHttpClient(OkHttpClient client){
        if(client != null) mHttpClient = client;
        return this;
    }
    public AudioSFactory setCookieManager(CookieHandler manager){
        if(manager != null) mHttpClient.setCookieHandler(manager);
        return this;
    }

    public AudioSFactory addConvertFactory(Converter.Factory converter){
        if(converter != null) mConverters.add(converter);
        return this;
    }

    public IAudioS create(){

        Retrofit.Builder builder = new Retrofit.Builder()
                        .client(mHttpClient)
                        .baseUrl(Constants.getBaseUriBuilder(false).toString())
                        .addConverterFactory(GsonConverterFactory.create(mGson));

        if(mConverters.size() > 0){
            for(Converter.Factory converter : mConverters){
                if(converter != null) builder.addConverterFactory(converter);
            }
        }

        return builder.build().create(IAudioS.class);
    }
}
