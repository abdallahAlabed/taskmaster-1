package com.example.taskmaster_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;
import com.example.taskmaster_1.database.AppDatabase;
import com.example.taskmaster_1.database.TaskDoa;
import com.example.taskmaster_1.database.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TaskDetail extends AppCompatActivity {
    //    AppDatabase db ;
//    TaskDoa taskDoa;
//    ArrayList<TaskModel>taskModels;
    List<Todo> todos = new ArrayList<Todo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(TaskDetail.this);
        String teamName = sharedPreferences.getString("teamName", "teamThr");

//        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().build();
//        taskDoa = (TaskDoa) db.taskDao();

//        taskModels = (ArrayList<TaskModel>) taskDoa.getAll();
        RecyclerView taskModelRecuclerView = findViewById(R.id.editTextTextMultiLine);
        taskModelRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        taskModelRecuclerView.setAdapter(new TaskAdaptaer(todos));

        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                taskModelRecuclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        Amplify.API.query(
                ModelQuery.list(Todo.class),
                response -> {
                    for (Todo todo : response.getData()) {
                        Log.i("taskmaster1", todo.getId());
                       if (teamName.equals(todo.getTeam().getName()) ){
                        todos.add(todo);
                       }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("taskmaster1", "Query failure", error)
        );

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
        String addTask = intent.getExtras().getString("addTask", "all tasks");
        TextView addTaskView = findViewById(R.id.tasksDetail);
        addTaskView.setText(addTask);
    }
}