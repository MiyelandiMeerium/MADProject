package com.example.gitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class AddAccountsActivity extends AppCompatActivity {

    EditText Accnum, Accname,Bname,Des,Balance;
    Spinner Acctype;
    Button Subbtn;

    AwesomeValidation awesomeValidation;
    DatabaseReference accountsDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accounts);

        Accnum = findViewById(R.id.Accnum);
        Accname = findViewById(R.id.Accname);
        Acctype = findViewById(R.id.Acctype);
        Des = findViewById(R.id.des);
        Balance = findViewById(R.id.balance);
        Subbtn = findViewById(R.id.subbtn);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this , R.id.Accnum,RegexTemplate.NOT_EMPTY, R.string.invalid_Accnumber);
        awesomeValidation.addValidation(this,R.id.Accname, RegexTemplate.NOT_EMPTY,R.string.invalid_Accname);

        accountsDbRef = FirebaseDatabase.getInstance().getReference().child("Accounts");

        Subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(), "From Validate Successfully..", Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Accounts").push();
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",databaseReference.getKey());
                    map.put("num",Accnum.getText().toString());
                    map.put("name",Accname.getText().toString());
                    map.put("type",Acctype.getSelectedItem().toString());
                    map.put("bname",Bname.getText().toString());
                    map.put("description",Des.getText().toString());
                    map.put("balance",Balance.getText().toString());

                    databaseReference.setValue(map);


                    Intent intent = new Intent(AddAccountsActivity.this,ViewAccountsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Validation Faild", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}