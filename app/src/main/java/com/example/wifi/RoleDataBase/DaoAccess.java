package com.example.wifi.RoleDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wifi.TabActivity.MyAdapter;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    long insertDBRole(DBRole dbRole);

    @Insert
    void insertDBRoleList(List<DBRole>dbRoleList);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_DB_ROLE)
    List<DBRole> fetchAllDBRoles();

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_DB_ROLE + " WHERE role_id = :roleId")
    DBRole fetchDBRoleListById(int roleId);

    @Update
    int updateDBRole(DBRole dbRole);

    @Delete
    int deleteDBRole(DBRole dbRole);



}
