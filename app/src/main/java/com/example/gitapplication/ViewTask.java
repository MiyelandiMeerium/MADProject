package com.example.gitapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class ViewTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtask);


        //textview id's implement
        TextView txtmsg1 = findViewById(R.id.m1);
        txtmsg1.setText(R.string.tasktxt1);


        TextView txtmsg2 = findViewById(R.id.m2);
        txtmsg2.setText(R.string.tasktxt3);

        TextView txtmsg3 = findViewById(R.id.m3);
        txtmsg1.setText(R.string.tasktxt9);

        TextView txtmsg4 = findViewById(R.id.m4);
        txtmsg1.setText(R.string.tasktxt11);

        //relativelayout id's implement
        RelativeLayout brl = (RelativeLayout) findViewById(R.id.r1);


        RelativeLayout brl1 = (RelativeLayout) findViewById(R.id.r2);




        //displaying system messages

        Log.i("Lifecycle" ,  "onCreate executed" );

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle" ,  "onStart executed" );

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle" ,  "onResume executed" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle" ,  "onPause executed" );

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle" ,  "onStop executed" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle" ,  "onDestroy executed" );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle" ,  "onRestart executed" );
    }
}
