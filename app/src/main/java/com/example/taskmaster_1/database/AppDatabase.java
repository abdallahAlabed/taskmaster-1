package com.example.taskmaster_1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TaskModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskModel taskDao();
}