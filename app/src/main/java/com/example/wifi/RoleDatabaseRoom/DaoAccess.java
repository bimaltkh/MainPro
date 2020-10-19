package com.example.wifi.RoleDatabaseRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    long insertTodo(Role todo);

    @Insert
    void insertTodoList(List<Role> todoList);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO)
    List<Role> fetchAllTodos();

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE category = :category")
    List<Role> fetchTodoListByCategory(String category);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE todo_id = :todoId")
    Role fetchTodoListById(int todoId);

   /* @Query("SELECT viewsitelist  FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE todo_id = :")
    Todo fetchSite(int a);*/
@Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO + " WHERE name = :user2")
List<Role> fetchuser(String user2);
    @Update
    int updateTodo(Role todo);


    @Delete
    int deleteTodo(Role todo);


}