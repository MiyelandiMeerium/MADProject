package com.example.gitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignUpActivity extends AppCompatActivity {
    TextView Login;
    Button Signup;
    EditText Email,Pass,Copass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ini_var();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e,p,cp;
                e=Email.getText().toString();
                p=Pass.getText().toString();
                cp=Copass.getText().toString();

                if(e.contains("@")&&e.contains(".")){
                    if (p.length()>=6 && p.equals(cp)){

                    }
                }else {
                    Toast.makeText(SignUpActivity.this, "incorrect email or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ini_var() {
        Login=findViewById(R.id.login_tx);
        Signup=findViewById(R.id.signup_btn);
        Email=findViewById(R.id.getemail);
        Pass=findViewById(R.id.getpass);
        Copass=findViewById(R.id.getcopass);
    }
}