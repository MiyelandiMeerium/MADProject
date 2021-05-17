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


public class AddBujectActivity extends AppCompatActivity {

    EditText buname, buamonunt;
    Spinner period;
    Button  buinsert;

    AwesomeValidation awesomeValidation;
    DatabaseReference BujectDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buject);

        buname=findViewById(R.id.buname);
        buamonunt=findViewById(R.id.buamount);
        period=findViewById(R.id.period);
        buinsert=findViewById(R.id.buinsert);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.buname,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.buamount,
                RegexTemplate.NOT_EMPTY,R.string.invalid_amount);


        BujectDbRef = FirebaseDatabase.getInstance().getReference().child("Buject");

        buinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(), "From Validate Successfully..", Toast.LENGTH_SHORT).show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Buject").push();
                    Map<String,Object> map = new HashMap<>();
                    map.put("id",databaseReference.getKey());
                    map.put("name",buname.getText().toString());
                    map.put("period",period.getSelectedItem().toString());
                    map.put("amount",buamonunt.getText().toString());

                    databaseReference.setValue(map);

                    Intent intent = new Intent(AddBujectActivity.this,BujectActivity.class);
                   startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Validation Faild", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}