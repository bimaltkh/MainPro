package com.example.wifi.TabActivity;
public class MyData {

    private String name,color,alarm1,alarm2,alarm3;

    public MyData( String name) {

        this.name = name;


    }

    public MyData() {

    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getAlarm1(){
        return alarm1;
    }
    public void setAlarm1(String alarm1){
        this.alarm1=alarm1;
    }
    public String getAlarm2(){
        return alarm2;
    }
    public void setAlarm2(String alarm2){
        this.alarm2=alarm2;
    }
    public String getAlarm3(){
        return alarm3;
    }
    public void setAlarm3(String alarm3){
        this.alarm3=alarm3;
    }


}
