package com.audioservice.jeffchien.audios.rest;

import com.audioservice.jeffchien.audios.rest.layouts.Resource;
import com.audioservice.jeffchien.audios.rest.layouts.ResourceList;
import com.audioservice.jeffchien.audios.rest.layouts.ResourceObject;
import com.audioservice.jeffchien.audios.rest.layouts.SimpleResult;
import com.audioservice.jeffchien.audios.rest.layouts.User;
import com.audioservice.jeffchien.audios.rest.layouts.UserResource;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

public interface IAudioS {

    //User
    @FormUrlEncoded
    @POST(Constants.BASE_API_PATH + "/user/login")
    Call<SimpleResult> userLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constants.BASE_API_PATH + "/user/register")
    Call<User> userRegister(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET(Constants.BASE_API_PATH + "/user/logout")
    Call<SimpleResult> userLogout();

    @GET(Constants.BASE_API_PATH + "/user/profile")
    Call<User> getUserProfile();

    @GET(Constants.BASE_API_PATH + "/user/resource")
    Call<UserResource> getUserResource();

    /*
    @POST(Constants.BASE_API_PATH + "/user/upload")
    Call<SimpleResult> uploadResource(
            @Part("title") String title,
            @Part("description") String description,
            @Part("latitude") Float latitude,
            @Part("longitude") Float longitude,
            @Part("attachments") RequestBody attachments
    );
    */

    //Resource
    @GET(Constants.BASE_API_PATH + "/resource")
    Call<ResourceList> getAllResources();

    @GET(Constants.BASE_API_PATH + "/resource/{resId}")
    Call<Resource> getResource(@Path("resId") String resId);

    //Object
    @GET(Constants.BASE_API_PATH + "/object/{objId}")
    Call<ResourceObject> getObjectMeta(@Path("objId") String objId);

    @GET(Constants.BASE_API_PATH + "/object")
    Call<List<ResourceObject>> getObjectMetaBatch(@Query("objId") List<String> objIds);
}
