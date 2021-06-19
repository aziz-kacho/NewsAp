package com.example.newsapp.data.Models;

public class UserJV {
    private String email;
    private String name;
    private String password;

    public UserJV(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public UserJV() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
