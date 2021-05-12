package com.example.gitapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class RecordActivity extends AppCompatActivity {

    Button Add;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.list);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        Load(); //method containing  FirebaseRecyclerAdapter
    }

    private void Load() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Records");
        FirebaseRecyclerOptions<Records> options = new FirebaseRecyclerOptions.Builder<Records>()
                .setQuery(query, new SnapshotParser<Records>() {
                    @NonNull
                    @Override
                    public Records parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new Records(snapshot.child("id").getValue().toString(),
                                snapshot.child("name").getValue().toString(),
                                snapshot.child("type").getValue().toString(),
                                snapshot.child("amount").getValue().toString());
                    }
                })
                .build();

        adapter = new FirebaseRecyclerAdapter<Records, RecordViewHolder>(options) {
            @Override
            public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);

                return new RecordViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(RecordViewHolder holder, int position, Records model) {
                holder.setTexName(model.getName());
                holder.setTexType(model.getType());
                holder.setTexAmount(model.getAmount());


                holder.editbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final DialogPlus dailogPlus = DialogPlus.newDialog(holder.texName.getContext())
                                .setContentHolder(new ViewHolder(R.layout.update_dailog))
                                .setExpanded(true,900).setMargin(20, 20, 20, 20)

                                .create();

                        View view = dailogPlus.getHolderView();

                        EditText name = view.findViewById(R.id.tetName);
                        EditText type = view.findViewById(R.id.txType);
                        EditText amount = view.findViewById(R.id.txtAmount);

                        Button upbtn = view.findViewById(R.id.upbtninsert);

                        name.setText(model.getName());
                        type.setText(model.getType());
                        amount.setText(model.getAmount());

                        dailogPlus.show();

                        upbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("name",name.getText().toString());
                                map.put("type",type.getText().toString());
                                map.put("amount",amount.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child("Records")
                                        .child(getRef(position).getKey()).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(holder.texName.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                                dailogPlus.dismiss();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(Exception e) {
                                                Toast.makeText(holder.texName.getContext(), "Error While Updating ", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                            }
                        });

                    }
                });

                holder.deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.texName.getContext());
                        builder.setTitle("Are you sure..?");
                        builder.setMessage("Deleted data can't be Undo");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Records")
                                        .child(getRef(position).getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.texName.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });

            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


}