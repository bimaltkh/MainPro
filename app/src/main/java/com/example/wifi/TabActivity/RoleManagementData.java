package com.example.wifi.TabActivity;

public class RoleManagementData {
    private String role,date,status;

    public RoleManagementData( String role) {

        this.role = role;


    }

    public RoleManagementData() {

    }


    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }



}
