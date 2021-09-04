package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.taskmaster_1.database.AppDatabase;
import com.example.taskmaster_1.database.TaskDoa;
import com.example.taskmaster_1.database.TaskModel;

import java.util.ArrayList;

public class TaskDetail extends AppCompatActivity {
    AppDatabase db ;
    TaskDoa taskDoa;
    ArrayList<TaskModel>taskModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task").allowMainThreadQueries().build();
        taskDoa = (TaskDoa) db.taskDao();

        taskModels = (ArrayList<TaskModel>) taskDoa.getAll();

        RecyclerView taskModelRecuclerView = findViewById(R.id.editTextTextMultiLine);
        taskModelRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        taskModelRecuclerView.setAdapter(new TaskAdaptaer(taskModels));
//
//        ArrayList<TaskModel> allTaskModel = new ArrayList<TaskModel>();
//        allTaskModel.add(new TaskModel("sleeping","new","بتحلم فيه بس ما بتشوفه "));
//        allTaskModel.add(new TaskModel("coding","assigned","يا ريت فالحين فيه "));
//        allTaskModel.add(new TaskModel("eating","progress","hallelooya"));
//
//        RecyclerView taskModelRecuclerView = findViewById(R.id.editTextTextMultiLine);
//        taskModelRecuclerView.setLayoutManager(new LinearLayoutManager(this));
//        taskModelRecuclerView.setAdapter(new TaskAdaptaer(allTaskModel));

        Intent intent = getIntent();
        String addTask = intent.getExtras().getString("addTask","all tasks");
        TextView addTaskView = findViewById(R.id.tasksDetail);
        addTaskView.setText(addTask);
    }
}