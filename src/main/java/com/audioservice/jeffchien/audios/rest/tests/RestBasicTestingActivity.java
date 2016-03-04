package com.audioservice.jeffchien.audios.rest.tests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.audioservice.jeffchien.audios.rest.Constants;
import com.audioservice.jeffchien.audios.rest.IAudioS;
import com.audioservice.jeffchien.audios.rest.R;
import com.audioservice.jeffchien.audios.rest.layouts.ResourceList;
import com.audioservice.jeffchien.audios.rest.layouts.SimpleResult;
import com.audioservice.jeffchien.audios.rest.layouts.User;
import com.audioservice.jeffchien.audios.rest.net.PersistentCookieStore;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RestBasicTestingActivity extends AppCompatActivity {

    private IAudioS mAudioS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_testing);

        Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(this),
                                        CookiePolicy.ACCEPT_ALL);
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setCookieHandler(cookieManager);

        Retrofit retrofit = new Retrofit.Builder()
                        .client(httpClient)
                        .baseUrl(Constants.getBaseUriBuilder(false).toString())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
        mAudioS = retrofit.create(IAudioS.class);
    }

    //Resources
    public Response<ResourceList> getAllResources() throws IOException, NullPointerException{
        return mAudioS.getAllResources().execute();
    }

    //User
    public Response<SimpleResult> userLogin(String email, String password) throws IOException, NullPointerException {
        return mAudioS.userLogin(email, password).execute();
    }
    public Response<User> getUserProfile() throws IOException {
        return mAudioS.getUserProfile().execute();
    }

    public Response<SimpleResult> userLogout() throws IOException {
        return mAudioS.userLogout().execute();
    }
}
