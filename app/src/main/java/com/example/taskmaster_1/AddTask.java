package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmaster_1.database.AppDatabase;
import com.example.taskmaster_1.database.TaskDoa;
import com.example.taskmaster_1.database.TaskModel;

public class AddTask extends AppCompatActivity {
    AppDatabase db ;
    TaskDoa taskDoa ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task").allowMainThreadQueries().build();
        taskDoa = (TaskDoa) db.taskDao();

        EditText title = findViewById(R.id.titletxt);
        EditText state = findViewById(R.id.stateTxt);
        EditText body= findViewById(R.id.bodyTxt);
        Button addBtn = findViewById(R.id.button1);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDoa.insertUsers(new TaskModel(title.getText().toString(),state.getText().toString(),body.getText().toString()));
                Toast.makeText(getApplicationContext(), "hallelooya!", Toast.LENGTH_LONG).show();
                Intent goToAllTask = new Intent(AddTask.this, TaskDetail.class);
                startActivity(goToAllTask);

                Toast.makeText(getApplicationContext(), "hallelooya!", Toast.LENGTH_LONG).show();
            }
        });
    }
}