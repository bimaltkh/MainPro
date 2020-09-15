package com.example.wifi.TabActivity;

public class SingletonSession2 {private static SingletonSession2 instance;
    private String username;
    //no outer class can initialize this class's object
    private SingletonSession2() {}

    public static SingletonSession2 Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null)
        {
            instance = new SingletonSession2();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
