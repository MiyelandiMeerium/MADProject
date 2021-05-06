package com.example.gitapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AccountsActivity extends AppCompatActivity {

    EditText Accnum;
    EditText Accname;
    Button subbtn;
    DatabaseReference adddataref;
    ListView mylistview;
    ArrayList<String> myarraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accounts);
        ArrayAdapter<String> myarrayadapter = new ArrayAdapter<String>(AccountsActivity.this, android.R.layout.simple_list_item_1,myarraylist);

        Accname = findViewById(R.id.Accname);
        Accnum = findViewById(R.id.Accnum);
        subbtn = findViewById(R.id.subbtn);
        mylistview = (ListView) findViewById(R.id.listAcc);
        mylistview.setAdapter(myarrayadapter);

        adddataref = FirebaseDatabase.getInstance().getReference().child("Accounts");
        adddataref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                String value = snapshot.getValue(String.class);
                myarraylist.add(value);
                myarrayadapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable String s ) {
                myarrayadapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAccountsData();
            }
        });

    }
    private void insertAccountsData() {

        Integer accountNum = Integer.valueOf(Accnum.getText().toString());
        String accountName = Accname.getText().toString();

        Accounts accounts = new Accounts(accountNum ,accountName);
        adddataref.push().setValue(accounts);
        Toast.makeText(AccountsActivity.this, "data inserted" , Toast.LENGTH_SHORT).show();
    }

}
