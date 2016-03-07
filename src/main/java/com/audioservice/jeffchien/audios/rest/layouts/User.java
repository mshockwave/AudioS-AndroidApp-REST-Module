package com.audioservice.jeffchien.audios.rest.layouts;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Parcelable{
    protected User(Parcel in) {
        id = in.readString();
        email = in.readString();
        profile = (Profile)in.readSerializable();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeSerializable(profile);
    }

    public class Profile implements Serializable{
        public String username;
        public Profile(){}
    }

    public String id;
    public String email;
    public Profile profile = new Profile();

    public User(){}
}
