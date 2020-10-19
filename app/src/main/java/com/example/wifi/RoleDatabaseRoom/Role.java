package com.example.wifi.RoleDatabaseRoom;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = MyDatabase.TABLE_NAME_TODO)
public class Role implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int todo_id;

  public String name;

    public String description;

    public String category;

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






    @Ignore
    public String priority;

}