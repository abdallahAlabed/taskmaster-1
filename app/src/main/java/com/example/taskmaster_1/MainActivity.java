package com.example.taskmaster_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;
import com.example.taskmaster_1.database.AppDatabase;
import com.example.taskmaster_1.database.TaskDoa;
import com.example.taskmaster_1.database.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    AppDatabase db ;
//    TaskDoa taskDoa;
//    ArrayList<TaskModel>taskMotdels;

    List<Todo> todos =new ArrayList<Todo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//            // Add this line, to include the Auth plugin.
//
//            Log.i("taskmaster1", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("taskmaster1", "Could not initialize Amplify", error);
//        }
//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "a1650902031@gmail.com")
//                .build();
//        Amplify.Auth.signUp("abdallahAlabed1", "Abdllah321", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
//        Amplify.Auth.confirmSignUp(
//                "abdallahAlabed1",
//                "182970",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Amplify.Auth.signIn(
//                "abdallahAlabed1",
//                "Abdllah321",
//                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );

//        Amplify.Auth.signOut(
//                () -> Log.i("AuthQuickstart", "Signed out successfully"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );

//        Team team = Team.builder().name("teamOne").build();
//        Team team1 = Team.builder().name("teamTwo").build();
//        Team team2 = Team.builder().name("teamThr").build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(team),
//                response -> Log.i("taskmaster1", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("taskmaster1", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(team1),
//                response -> Log.i("taskmaster1", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("taskmaster1", "Create failed", error)
//        );
//        Amplify.API.mutate(
//                ModelMutation.create(team2),
//                response -> Log.i("taskmaster1", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("taskmaster1", "Create failed", error)
//        );
//        db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().build();
//        taskDoa = (TaskDoa) db.taskDao();
//        taskModels = (ArrayList<TaskModel>) taskDoa.getAll();
        Button signOutBtn = findViewById(R.id.signOut);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> {
                            Log.i("AuthQuickstart", "Signed out successfully");Intent sinOut = new Intent(MainActivity.this, Login.class);startActivity(sinOut);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
                Intent signOut = new Intent(MainActivity.this, Login.class);
                startActivity(signOut);
                Toast.makeText(getApplicationContext(), "Signed out successfully !", Toast.LENGTH_LONG).show();
            }
        });
        RecyclerView taskModelRecuclerView = findViewById(R.id.taskRecylerView);
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
                        todos.add(todo);
                        System.out.println(todo);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("taskmaster1", "Query failure", error)
        );


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
                allTask1.putExtra("addTask", "all Tasks");
                startActivity(allTask1);
            }
        });


        Button eating = findViewById(R.id.eating);
        eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(MainActivity.this, TaskDetail.class);
                addTask.putExtra("addTask", "eating");
                startActivity(addTask);

            }
        });

        Button coding = findViewById(R.id.coding);
        coding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(MainActivity.this, TaskDetail.class);
                addTask.putExtra("addTask", "coding");
                startActivity(addTask);
            }
        });

        Button sleeping = findViewById(R.id.sleeping);
        sleeping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTask = new Intent(MainActivity.this, TaskDetail.class);
                addTask.putExtra("addTask", "sleeping");
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
        instructorNameView.setText(userName + " tasks");


    }
}