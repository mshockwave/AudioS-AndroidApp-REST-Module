package com.audioservice.jeffchien.audios.rest;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.audioservice.jeffchien.audios.rest.layouts.Resource;
import com.audioservice.jeffchien.audios.rest.layouts.ResourceList;
import com.audioservice.jeffchien.audios.rest.layouts.SimpleResult;
import com.audioservice.jeffchien.audios.rest.layouts.User;
import com.audioservice.jeffchien.audios.rest.tests.RestBasicTestingActivity;

import java.io.IOException;

import retrofit.Response;

public class BasicRestTest extends ActivityInstrumentationTestCase2<RestBasicTestingActivity>{
    private static final String TAG = BasicRestTest.class.getName();

    public BasicRestTest(){
        super(RestBasicTestingActivity.class);
    }

    private RestBasicTestingActivity mActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        assertNotNull("Activity not null", mActivity);
    }

    public void testAll(){
        getAllResources();

        //User Part
        userLogin();
        userProfile();
        userLogout();
    }

    public void getAllResources(){
        try {
            Response<ResourceList> response = mActivity.getAllResources();
            Log.d(TAG, "Code: " + response.code());
            Log.d(TAG, "Message: " + response.message());
            assertNotNull(response.body());
            Log.d(TAG, "Resource size: " + response.body().size);
            assertNotNull(response.body().resources);

            for(Resource res : response.body().resources){
                Log.d(TAG, "resource id: " + res.id);
                Log.d(TAG, "resource title: " + res.title);
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Catch IOException while getting resources");
        } catch (NullPointerException e){
            e.printStackTrace();
            fail("Catch NullException while getting resources");
        }
    }

    public void userLogin(){
        try{
            Response<SimpleResult> response = mActivity.userLogin("yihshyng223@gmail.com", "abcde");
            Log.d(TAG, "Code: " + response.code());
            Log.d(TAG, "Message: " + response.message());
            assertNotNull(response.body());

            Log.d(TAG, "Body msg: " + response.body().msg);
            Log.d(TAG, "Body description: " + response.body().description);
        }catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
    }

    public void userProfile(){
        try{
            Response<User> response = mActivity.getUserProfile();
            Log.d(TAG, "Code: " + response.code());
            Log.d(TAG, "Message: " + response.message());
            assertNotNull(response.body());

            Log.d(TAG, "User Id: " + response.body().id);
            Log.d(TAG, "User Email: " + response.body().email);
            Log.d(TAG, "User Username: " + response.body().profile.username);
        }catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
    }

    public void userLogout(){
        try{
            Response<SimpleResult> response = mActivity.userLogout();
            Log.d(TAG, "Code: " + response.code());
            Log.d(TAG, "Message: " + response.message());
            assertNotNull(response.body());

            Log.d(TAG, "Body msg: " + response.body().msg);
            Log.d(TAG, "Body description: " + response.body().description);
        }catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
