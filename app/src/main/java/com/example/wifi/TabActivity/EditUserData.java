package com.example.wifi.TabActivity;

public class EditUserData  {

    private String name,role;

    public EditUserData( String name) {

        this.name = name;


    }

    public EditUserData() {

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role=role;
    }


}
