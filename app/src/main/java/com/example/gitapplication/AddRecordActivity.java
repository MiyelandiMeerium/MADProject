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


public class AddRecordActivity extends AppCompatActivity {

    EditText recordname, amonunt;
    Spinner recordtype;
    Button btninsert;

    AwesomeValidation awesomeValidation;
    DatabaseReference recordDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recoad);

        recordname=findViewById(R.id.recordname);
        amonunt=findViewById(R.id.amount);
        recordtype=findViewById(R.id.recordtype);
        btninsert=findViewById(R.id.btninsert);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.recordname,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.amount,
                RegexTemplate.NOT_EMPTY,R.string.invalid_amount);


        recordDbRef = FirebaseDatabase.getInstance().getReference().child("Records");

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(), "From Validate Successfully..", Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Records").push();
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",databaseReference.getKey());
                    map.put("name",recordname.getText().toString());
                    map.put("type",recordtype.getSelectedItem().toString());
                    map.put("amount",amonunt.getText().toString());

                    databaseReference.setValue(map);


                    Intent intent = new Intent(AddRecordActivity.this,RecordActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Validation Faild", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}