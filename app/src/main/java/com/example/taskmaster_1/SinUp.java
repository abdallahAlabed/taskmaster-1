package com.example.taskmaster_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SinUp extends AppCompatActivity {
//    public static final String TAG = SinUp.class.getSimpleName();
//
//    private static PinpointManager pinpointManager;
//
//    public static PinpointManager getPinpointManager(final Context applicationContext) {
//        if (pinpointManager == null) {
//            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
//            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
//                @Override
//                public void onResult(UserStateDetails userStateDetails) {
//                    Log.i("INIT", userStateDetails.getUserState().toString());
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    Log.e("INIT", "Initialization error.", e);
//                }
//            });
//
//            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
//                    applicationContext,
//                    AWSMobileClient.getInstance(),
//                    awsConfig);
//
//            pinpointManager = new PinpointManager(pinpointConfig);
//
//            FirebaseMessaging.getInstance().getToken()
//                    .addOnCompleteListener(new OnCompleteListener<String>() {
//                        @Override
//                        public void onComplete(@NonNull Task<String> task) {
//                            if (!task.isSuccessful()) {
//                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//                                return;
//                            }
//                            final String token = task.getResult();
//                            Log.d(TAG, "Registering push notifications token: " + token);
//                            pinpointManager.getNotificationClient().registerDeviceToken(token);
//                        }
//                    });
//        }
//        return pinpointManager;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_up);
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            // Add this line, to include the Auth plugin.

            Log.i("taskmaster1", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("taskmaster1", "Could not initialize Amplify", error);
        }
        Button logInBtn = findViewById(R.id.sinUp);

        EditText userName = findViewById(R.id.loginUserNameTxt);
        EditText password = findViewById(R.id.loginPasswordTxt);
        EditText email = findViewById(R.id.emailTxt);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email.toString())
                        .build();
                Amplify.Auth.signUp(userName.toString(), password.toString(), options,
                        result -> {
                            Log.i("AuthQuickStart", "Result: " + result.toString());
                            Intent verification = new Intent(SinUp.this, ConfirmSignUp.class);
                            startActivity(verification);
                        },
                        error -> Log.e("AuthQuickStart", "Sign up failed", error)
                );

                Toast.makeText(getApplicationContext(), "signUp Confirmed !", Toast.LENGTH_LONG).show();
            }
        });

        Button goToLogInBtn = findViewById(R.id.loginButt);
        goToLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIn = new Intent(SinUp.this, Login.class);
                startActivity(logIn);
            }
        });


    }
}