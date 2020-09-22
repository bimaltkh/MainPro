package com.example.wifi.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = RoleDatabase.TABLE_NAME_ROLE)
public class Role implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int role_id;

    public String rolename;

    public String expirydate;

    public String roletype;

    @Ignore
    public String priority;

}
