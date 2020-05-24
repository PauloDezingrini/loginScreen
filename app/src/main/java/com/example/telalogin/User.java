package com.example.telalogin;

public class User {

    String name ;
    String email ;
    String id;
    public User(){}



    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id+"={" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
