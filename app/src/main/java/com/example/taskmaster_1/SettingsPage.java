package com.example.taskmaster_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsPage extends AppCompatActivity {
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button saveUsername = findViewById(R.id.save);

        saveUsername.setOnClickListener((view) -> {

            RadioGroup radioGroup = findViewById(R.id.radioGroup);
            RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            if (radioButton.getText().toString().equals("team")) {
                str ="teamOne";
            } else if (radioButton.getText().toString().equals("team1")) {
                str ="teamTwo";
            } else {
                str ="teamThr";
            }
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsPage.this);

            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

            EditText userName1 = findViewById(R.id.userName1);

            String userName = userName1.getText().toString();

            sharedPreferencesEditor.putString("userName", userName);
            sharedPreferencesEditor.putString("teamName", str);

            sharedPreferencesEditor.apply();
            Intent backToHome = new Intent(SettingsPage.this, MainActivity.class);
            startActivity(backToHome);
        });
    }


}