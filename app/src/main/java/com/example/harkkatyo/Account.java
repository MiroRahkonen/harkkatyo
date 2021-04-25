package com.example.harkkatyo;

public class Account {
    private String email;
    private String password;
    private String name;

    public Account(String email1, String password1, String name1) {
        email = email1;
        password = password1;
        name = name1;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}