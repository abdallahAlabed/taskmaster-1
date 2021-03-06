package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

public class ConfirmSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sign_up);

        Button logInBtn = findViewById(R.id.submitVerification);

        EditText userName = findViewById(R.id.userNameVerification);
        EditText confirmSignUpCode = findViewById(R.id.Verification);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.confirmSignUp(
                        userName.toString(),
                        confirmSignUpCode.toString(),
                        result -> {
                            Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"); Intent verification = new Intent(ConfirmSignUp.this, Login.class);
                            startActivity(verification);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );


                Toast.makeText(getApplicationContext(), "signUp Confirmed !", Toast.LENGTH_LONG).show();
            }
        });




//        182970
    }

    }
