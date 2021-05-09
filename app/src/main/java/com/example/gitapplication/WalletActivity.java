package com.example.gitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gitapplication.ui.wallet.WalletFragment;

public class WalletActivity extends AppCompatActivity {

    Button Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_activity);
        Btn =findViewById(R.id.button2);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(WalletActivity.this,AddRecoad.class);
                startActivity(intent);
            }
        });


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WalletFragment.newInstance())
                    .commitNow();
        }
    }
}