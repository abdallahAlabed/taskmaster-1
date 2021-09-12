package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;

import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;
import com.example.taskmaster_1.database.AppDatabase;
import com.example.taskmaster_1.database.TaskDoa;
import com.example.taskmaster_1.database.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {
    //    AppDatabase db ;
//    TaskDoa taskDoa ;
    Team team;
    Todo todo;

    List<Team> teams = new ArrayList<Team>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().build();
//        taskDoa = (TaskDoa) db.taskDao();


        EditText title = findViewById(R.id.titletxt);
        EditText state = findViewById(R.id.stateTxt);
        EditText body = findViewById(R.id.bodyTxt);
        Button addBtn = findViewById(R.id.button1);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("taskmaster1", team.getId());
                        teams.add(team);
                    }
                },
                error -> Log.e("taskmaster1", "Query failure", error)

        );
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                taskDoa.insertUsers(new TaskModel(title.getText().toString(),state.getText().toString(),body.getText().toString()));
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioButton.getText().toString().equals("team")) {
                    team =teams.get(0);
                } else if (radioButton.getText().toString().equals("team1")) {
                    team =teams.get(2);

                } else {
                    team =teams.get(1);

                }

                todo = Todo.builder().title(title.getText().toString()).state(state.getText().toString()).body(body.getText().toString()).team(team).build();
                Amplify.API.mutate(
                        ModelMutation.create(todo),
                        response -> Log.i("taskmaster1", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("taskmaster1", "Create failed", error)
                );
//

//                Intent goToAllTask = new Intent(AddTask.this, TaskDetail.class);
//                startActivity(goToAllTask);
                Toast.makeText(getApplicationContext(), "hallelooya!", Toast.LENGTH_LONG).show();
            }
        });
    }
}