package com.example.taskmaster_1;

import androidx.annotation.Nullable;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity {
    //    AppDatabase db ;
//    TaskDoa taskDoa ;
    Team team;
    Todo todo;
    String fileName ="";
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
        Button logInBtn = findViewById(R.id.addPic);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFile();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        File file = new File(data.getData().getPath());
        fileName=file.getName();

        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
            Amplify.Storage.uploadInputStream(
                    fileName,
                    exampleInputStream,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private void pickFile () {
        Intent selctedFile = new Intent(Intent.ACTION_GET_CONTENT);
        selctedFile.setType(("*/*"));
        selctedFile = Intent.createChooser(selctedFile, "Select File");
        startActivityForResult(selctedFile, 1234);
        Toast.makeText(getApplicationContext(), "you added a new pic", Toast.LENGTH_LONG).show();

    }
}

//  someActivityResultLauncher = registerForActivityResult(
//          new ActivityResultContracts.StartActivityForResult(),
//          new ActivityResultCallback<ActivityResult>() {
//@Override
//public void onActivityResult(ActivityResult result) {
//        if (result.getResultCode() == Activity.RESULT_OK) {
//        // There are no request codes
//        Intent data = result.getData();
//        File file = new File(data.getData().getPath());
//        fileName=file.getName();
//        int index = fileName.lastIndexOf('.');
//        if(index > 0) {
//        String extension = fileName.substring(index + 1);
//        fileName = fileName + extension;
//        }
//        System.out.println(data.getData().getPath());
//        try {
//        InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
//        Amplify.Storage.uploadInputStream(
//        fileName,
//        exampleInputStream,
//        results -> Log.i("MyAmplifyApp", "Successfully uploaded: " + results.getKey()),
//        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//        } catch (FileNotFoundException e) {
//        e.printStackTrace();
//        }
//        }
//        }
//        });
