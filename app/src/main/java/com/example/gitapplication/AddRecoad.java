package com.example.gitapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecoad extends AppCompatActivity {

    EditText RecoadName, Description, Category, TaskType, SelectTask, Account,Amount;
    Button AddRecoad;

    DatabaseReference WalletData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recoad);

        RecoadName = findViewById(R.id.recoadname);
        Description = findViewById(R.id.desc);
        Category = findViewById(R.id.category);
        TaskType = findViewById(R.id.tasktype);
        SelectTask = findViewById(R.id.selecttask);
        Account = findViewById(R.id.account);
        Amount = findViewById(R.id.amount);
        AddRecoad = findViewById(R.id.addbtn);

        WalletData = FirebaseDatabase.getInstance().getReference().child("Wallet");

        AddRecoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertwalletdata();
            }
        });
    }

    private void insertwalletdata() {

        String name = RecoadName.getText().toString();
        String description = Description.getText().toString();
        String category = Category.getText().toString();
        String tasktype = TaskType.getText().toString();
        String selecttask = SelectTask.getText().toString();
        String account = Account.getText().toString();
        String amount = Amount.getText().toString();

        Wallets wallets = new Wallets(name,description,category,tasktype,selecttask,account,amount);

        WalletData.push().setValue(wallets);
        Toast.makeText(AddRecoad.this, "Data inserted!", Toast.LENGTH_SHORT).show();

    }
}