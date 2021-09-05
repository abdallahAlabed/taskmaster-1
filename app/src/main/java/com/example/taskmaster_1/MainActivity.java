package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taskmaster_1.database.AppDatabase;
import com.example.taskmaster_1.database.TaskDoa;
import com.example.taskmaster_1.database.TaskModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AppDatabase db ;
    TaskDoa taskDoa;
    ArrayList<TaskModel>taskModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().build();
        taskDoa = (TaskDoa) db.taskDao();

        taskModels = (ArrayList<TaskModel>) taskDoa.getAll();
        RecyclerView taskModelRecuclerView = findViewById(R.id.taskRecylerView);
        taskModelRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        taskModelRecuclerView.setAdapter(new TaskAdaptaer(taskModels));
//        ArrayList<TaskModel> allTaskModel = new ArrayList<TaskModel>();
//        allTaskModel.add(new TaskModel("sleeping","new","بتحلم فيه بس ما بتشوفه "));
//        allTaskModel.add(new TaskModel("coding","assigned","يا ريت فالحين فيه "));
//        allTaskModel.add(new TaskModel("eating","progress","hallelooya"));
//
//        RecyclerView taskModelRecuclerView = findViewById(R.id.taskRecylerView);
//        taskModelRecuclerView.setLayoutManager(new LinearLayoutManager(this));
//        taskModelRecuclerView.setAdapter(new TaskAdaptaer(allTaskModel));


        Button addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask1 = new Intent(MainActivity.this, AddTask.class);
                startActivity(addTask1);
            }
        });

        Button allTask = findViewById(R.id.allTask);
        allTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allTask1 = new Intent(MainActivity.this, TaskDetail.class);
                allTask1.putExtra("addTask","all Tasks");
                startActivity(allTask1);
            }
        });


        Button eating = findViewById(R.id.eating);
        eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(MainActivity.this, TaskDetail.class);
                addTask.putExtra("addTask","eating");
                startActivity(addTask);

            }
        });

        Button coding = findViewById(R.id.coding);
        coding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(MainActivity.this, TaskDetail.class);
                addTask.putExtra("addTask","coding");
                startActivity(addTask);
            }
        });

        Button sleeping = findViewById(R.id.sleeping);
        sleeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(MainActivity.this, TaskDetail.class);
                addTask.putExtra("addTask","sleeping");
                startActivity(addTask);
            }
        });


        Button setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSetting = new Intent(MainActivity.this, SettingsPage.class);
                startActivity(goToSetting);
            }
        });


    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName", "abdallah");

        TextView instructorNameView = findViewById(R.id.userName);
        instructorNameView.setText(userName+ " tasks");


    }
}