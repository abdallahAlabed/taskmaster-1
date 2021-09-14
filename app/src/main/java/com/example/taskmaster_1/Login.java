package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;

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
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logInBtn = findViewById(R.id.loginButt);

        EditText userName = findViewById(R.id.loginUserNameTxt);
        EditText password = findViewById(R.id.loginPasswordTxt);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Amplify.Auth.signIn(
                userName.getText().toString(),
                password.getText().toString(),
                result -> {
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"); Intent getStarted = new Intent(Login.this, MainActivity.class);
                    startActivity(getStarted);
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );

                Toast.makeText(getApplicationContext(), password.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        Button startBtn = findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getStarted = new Intent(Login.this, MainActivity.class);
                startActivity(getStarted);
            }
        });
        Button goToSinUpBtn = findViewById(R.id.goToSinUp);
        goToSinUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sinUp = new Intent(Login.this, SinUp.class);
                startActivity(sinUp);
            }
        });

    }
}