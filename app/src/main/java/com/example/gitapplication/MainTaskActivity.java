package com.example.gitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainTaskActivity extends AppCompatActivity implements NoteClickListener{

    List<NoteModel> noteModels=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintask);

        final RecyclerView notelist=findViewById(R.id.notelist);

        FloatingActionButton create=findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainTaskActivity.this,CreateNoteActivity.class));
            }
        });
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");

        databaseReference.child(StaticUtils.getUserEmail(MainTaskActivity.this)).child("noteModels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    String id=dataSnapshot1.child("id").getValue(String.class);
                    String note=dataSnapshot1.child("note_data").getValue(String.class);
                    String create=dataSnapshot1.child("created_at").getValue(String.class);
                    noteModels.add(new NoteModel(id,note,create));
                }

                NoteItemsRecyclerView noteItemsRecyclerView = new NoteItemsRecyclerView(noteModels, MainTaskActivity.this);
                notelist.setLayoutManager(new LinearLayoutManager(MainTaskActivity.this));
                notelist.setAdapter(noteItemsRecyclerView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClickItem(NoteModel noteModel) {
        Intent intent=new Intent(MainTaskActivity.this,ViewNoteDataActivity.class);
        intent.putExtra(Constants.id,noteModel.getId());
        intent.putExtra(Constants.note_text,noteModel.getNote_data());
        intent.putExtra(Constants.create_time,noteModel.getCreated_at());
        startActivity(intent);
    }
}


