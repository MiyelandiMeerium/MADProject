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

public class BujectActivity extends AppCompatActivity {

    Button Addbu;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter badapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buject);
        Addbu = findViewById(R.id.addbujcet);
        recyclerView = findViewById(R.id.listbuject);

        Addbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BujectActivity.this, AddBujectActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        Load(); //method containing  FirebaseRecyclerAdapter
    }

    private void Load() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Buject");
        FirebaseRecyclerOptions<Bujects> options = new FirebaseRecyclerOptions.Builder<Bujects>()
                .setQuery(query, new SnapshotParser<Bujects>() {
                    @NonNull
                    @Override
                    public Bujects parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new Bujects(snapshot.child("id").getValue().toString(),
                                snapshot.child("name").getValue().toString(),
                                snapshot.child("type").getValue().toString(),
                                snapshot.child("amount").getValue().toString());
                    }
                })
                .build();

        badapter = new FirebaseRecyclerAdapter<Bujects, BujectViewHolder>(options) {
            @Override
            public BujectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.buject_item, parent, false);

                return new BujectViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(BujectViewHolder holder, int position, Bujects model) {
                holder.setwName(model.getName());
                holder.setwPeriod(model.getPeriod());
                holder.setwAmount(model.getAmount());


                holder.bedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final DialogPlus dailogPlus = DialogPlus.newDialog(holder.wName.getContext())
                                .setContentHolder(new ViewHolder(R.layout.update_buject))
                                .setExpanded(true,900).setMargin(20, 20, 20, 20)

                                .create();

                        View view = dailogPlus.getHolderView();

                        EditText name = view.findViewById(R.id.upname);
                        EditText period = view.findViewById(R.id.upperiod);
                        EditText amount = view.findViewById(R.id.upamount);

                        Button upbuject = view.findViewById(R.id.upbuject);

                        name.setText(model.getName());
                        period.setText(model.getPeriod());
                        amount.setText(model.getAmount());

                        dailogPlus.show();

                        upbuject.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("name",name.getText().toString());
                                map.put("period",period.getText().toString());
                                map.put("amount",amount.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child("Records")
                                        .child(getRef(position).getKey()).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(holder.wName.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                                dailogPlus.dismiss();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(Exception e) {
                                                Toast.makeText(holder.wName.getContext(), "Error While Updating ", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                            }
                        });

                    }
                });

                holder.bdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.wName.getContext());
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
                                Toast.makeText(holder.wName.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });

            }

        };
        recyclerView.setAdapter(badapter);
        badapter.startListening();

    }
}