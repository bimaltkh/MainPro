package com.example.wifi.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Role.class}, version = 1, exportSchema = false)
public abstract class RoleDatabase extends RoomDatabase {
    public static final String DB_NAME = "app_db";
    public static final String TABLE_NAME_ROLE = "roledatabase";

    public abstract DaoAccess daoAccess();
}
