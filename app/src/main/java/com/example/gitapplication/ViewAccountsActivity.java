package com.example.gitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewAccountsActivity extends AppCompatActivity {
    ListView myListview;
    List<Accounts> accountsList;

    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_accounts);

        myListview = findViewById(R.id.listview);
        accountsList = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance().getReference("Accounts");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                accountsList.clear();

                for(DataSnapshot accountDatasnap : snapshot.getChildren()){

                    Accounts accounts = accountDatasnap.getValue(Accounts.class);
                    accountsList.add(accounts);
                }

                ListAdapter adapter = new ListAdapter(ViewAccountsActivity.this , accountsList);
                myListview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}