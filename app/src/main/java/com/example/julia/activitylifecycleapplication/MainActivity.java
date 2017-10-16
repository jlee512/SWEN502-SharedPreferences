package com.example.julia.activitylifecycleapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("START", "Application started");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("STOPPED", "Application stopped");

//        Get the TextView text value
        TextView text = (TextView) findViewById(R.id.changeText);
        String text_to_save = text.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("My Preferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("persist", text_to_save);

//        Commit the changes to the sharedpreferences
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("DESTROYED", "Application destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("PAUSE", "Application paused");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("RESUME", "Application resumed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("CREATE", "Application created");

//        Add layout elements
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.changeText);
                EditText text_input_field = (EditText) findViewById(R.id.editText);
                String text_input = text_input_field.getText().toString();
                text.setText(text_input);
                v.setVisibility(View.GONE);
                text_input_field.setVisibility(View.GONE);

            }
        });

        Button open_app_button = (Button) findViewById(R.id.button2);
        open_app_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent launchHelloWorld = getPackageManager().getLaunchIntentForPackage("com.example.julia.myapplication");
                if (launchHelloWorld != null) {
                    startActivity(launchHelloWorld);
                }
            }
        });


//        Load Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences("My Preferences", 0);
        String text = sharedPreferences.getString("persist", null);

        if (text != null) {
            Log.d("SAVED_STATE", text);
            TextView text_view = (TextView) findViewById(R.id.changeText);
            text_view.setText(text);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("RESTART", "Application restarted");
    }
}
