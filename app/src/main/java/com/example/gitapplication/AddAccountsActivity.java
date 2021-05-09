package com.example.gitapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.internal.AccountType;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAccountsActivity extends AppCompatActivity {
    EditText Accnum;
    EditText Accname;
    EditText bname;
    EditText des;
    EditText balance;
    Spinner  Acctype;
    Button subbtn;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accounts);

        Accnum = findViewById(R.id.Accnum);
        Accname = findViewById(R.id.Accname);
        subbtn = findViewById(R.id.subbtn);
        Acctype = findViewById(R.id.Acctype);
        bname =findViewById(R.id.bname);
        des = findViewById(R.id.des);
        balance = findViewById(R.id.balance);

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
        String Accounttype = Acctype.getSelectedItem().toString();
        String Bankname = bname.getText().toString();
        String Description = des.getText().toString();
        String OpeningBalance = balance.getText().toString();

        Accounts accounts = new Accounts(Accnumber , Accountname , Accounttype ,Bankname,Description, OpeningBalance);

        myRef.push().setValue(accounts);
        Toast.makeText(AddAccountsActivity.this , "data inserted" , Toast.LENGTH_SHORT).show();
    }
}