package com.example.harkkatyo;

public class Account {
    private String email;
    private String password;

    public Account(String email1, String password1) {
        email = email1;
        password = password1;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
