package com.example.gitapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountsHomeActivity extends AppCompatActivity {

    public Button Addbtn;
    public Button Viewbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_home);

        Addbtn =(Button) findViewById(R.id.addbtn);
        Viewbtn=(Button) findViewById(R.id.viewbtn);

        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountsHomeActivity.this , AddAccountsActivity.class);
                startActivity(intent);
            }
        });
        Viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountsHomeActivity.this , ViewAccountsActivity.class);
                startActivity(intent);
            }
        });

    }
}