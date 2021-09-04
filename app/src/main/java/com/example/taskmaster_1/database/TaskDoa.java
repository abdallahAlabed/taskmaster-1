package com.example.taskmaster_1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDoa {
    @Insert
    void insertAll(TaskModel... taskModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(TaskModel... taskModels);

    @Delete
    void delete(TaskModel taskModel);

    @Query("SELECT * FROM taskModel")
    List<TaskModel> getAll();
}
