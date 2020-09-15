package com.example.wifi.TabActivity;

public class UserManagementData {
    private String name,role;

    public UserManagementData( String name) {

        this.name = name;


    }

    public UserManagementData() {

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
