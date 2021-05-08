package com.example.gitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    TextView forget_pass;
    TextView signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ini_var();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e,p;
                e=email.getText().toString();
                p=password.getText().toString();
                if (e.contains("@")&&e.contains(".")){
                    if (p.length()>=6) {
                        if (e.equals("exp@exp.com") && p.equals("expexp")) {
                            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ini_var() {
        email=findViewById(R.id.email_id);
        password=findViewById(R.id.pass_id);
        login=findViewById(R.id.login_btn);
        forget_pass=findViewById(R.id.forgat_id);
        signup=findViewById(R.id.signup_tx);
    }
}