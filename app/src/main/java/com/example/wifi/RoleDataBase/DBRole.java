package com.example.wifi.RoleDataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = MyDatabase.TABLE_NAME_TODO)
public class DBRole implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int role_id;
    public String RoleName;
    public String ExpiryDate;
    public String Status;

    public String ViewSiteList;
    public String AddSite;
    public String ViewSiteDetails;
    public String EditSite;
    public String DeleteSite;
    public String ViewSiteParams;
    public String ViewAlarm;
    public String ViewAlarmHistory;
    public String ViewSiteSettings;
    public String InitializeSettings;
    public String ViewSiteIDRequests;
    public String EditSiteSettings;
    public String ControlSection;
    public String SendOTP;
    public String EnergyLevels;
    public String ViewReportHomepage;
    public String ViewUserList;
    public String AddUser;
    public String EditUser;
    public String DeleteUser;
    public String ChangeSupervisor;
    public String ViewRolelist;
    public String AddRole;
    public String EditRole;
    public String DeleteRole;
    public String ViewSystemSettings;
    public String EditSystemSettings;
}
