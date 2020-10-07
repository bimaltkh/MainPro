package com.example.wifi.RoleDataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {DBRole.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public static final String DB_NAME ="app_db";
    public static final String TABLE_NAME_DB_ROLE = "db_role" ;

    public abstract DaoAccess daoAccess();
}
