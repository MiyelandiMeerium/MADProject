package com.example.gitapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAccountsActivity extends AppCompatActivity {
    EditText Accnum;
    EditText Accname;
    Button subbtn;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accounts);

        Accnum = findViewById(R.id.Accnum);
        Accname = findViewById(R.id.Accname);
        subbtn = findViewById(R.id.subbtn);

        myRef = FirebaseDatabase.getInstance().getReference().child("Accounts");
        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAccountsData();
            }
        });
    }

    private void insertAccountsData() {
        String Accnumber = Accnum.getText().toString();
        String Accountname = Accname.getText().toString();

        Accounts accounts = new Accounts(Accnumber , Accountname);

        myRef.push().setValue(accounts);
        Toast.makeText(AddAccountsActivity.this , "data inserted" , Toast.LENGTH_SHORT).show();
    }
}