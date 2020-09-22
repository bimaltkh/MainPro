package com.example.wifi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DaoAccess {
    @Insert
    long insertRole(Role role);

    @Insert
    void insertRoleList(List<Role> roleList);

    @Query("SELECT * FROM " + RoleDatabase.TABLE_NAME_ROLE)
    List<Role> fetchAllTodos();

    @Query("SELECT * FROM " + RoleDatabase.TABLE_NAME_ROLE + " WHERE roletype = :roletype")
    List<Role> fetchRoleListByRoleType(String roletype);

    @Query("SELECT * FROM " + RoleDatabase.TABLE_NAME_ROLE + " WHERE role_id = :roleid")
    Role fetchTodoListById(int roleid);

    @Update
    int updateRole(Role role);

    @Delete
    int deleteRole(Role role);
}
