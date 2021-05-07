package com.example.gitapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AccountsActivity extends AppCompatActivity {

    EditText Accnum;
    EditText Accname;
    Button subbtn;
    DatabaseReference adddataref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accounts);


        Accname = findViewById(R.id.Accname);
        Accnum = findViewById(R.id.Accnum);
        subbtn = findViewById(R.id.subbtn);




    }

    public void buttononClicked(View view) {
    }
}
