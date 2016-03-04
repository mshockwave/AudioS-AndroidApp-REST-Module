package com.audioservice.jeffchien.audios.rest.layouts;

public class User {
    public class Profile {
        public String username;
        public Profile(){}
    }

    public String id;
    public String email;
    public Profile profile = new Profile();

    public User(){}
}
