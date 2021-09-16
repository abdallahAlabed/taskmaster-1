package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

public class Onedish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onedish);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=getIntent();

        String img =intent.getExtras().getString("img","img");

        Amplify.Storage.downloadFile(
                img,
                new File(getApplicationContext().getFilesDir() + img),
                result -> {
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    ImageView image = findViewById(R.id.imageView);
                    image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                },
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );
        String title=intent.getExtras().getString("title","title");
        String body=intent.getExtras().getString("body","body");
        String state=intent.getExtras().getString("state","state");
        TextView textViews1=findViewById(R.id.titleTextView);
        TextView textViews2=findViewById(R.id.stateTextView);
        TextView textViews3=findViewById(R.id.bodyTextView);
        textViews1.setText(title);
        textViews2.setText(state);
        textViews3.setText(body);

    }
}