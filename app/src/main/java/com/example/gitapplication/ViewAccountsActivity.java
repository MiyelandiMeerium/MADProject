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


public class ViewAccountsActivity extends AppCompatActivity {

    Button Add;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_accounts);
        Add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.list);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAccountsActivity.this, AddAccountsActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        Load(); //method containing  FirebaseRecyclerAdapter
    }

    private void Load() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Accounts");
        FirebaseRecyclerOptions<Accounts> options = new FirebaseRecyclerOptions.Builder<Accounts>()
                .setQuery(query, new SnapshotParser<Accounts>() {
                    @NonNull
                    @Override
                    public Accounts parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new Accounts(snapshot.child("id").getValue().toString(),
                                snapshot.child("num").getValue().toString(),
                                snapshot.child("name").getValue().toString(),
                                snapshot.child("type").getValue().toString(),
                                snapshot.child("bname").getValue().toString(),
                                snapshot.child("description").getValue().toString(),
                                snapshot.child("balance").getValue().toString());
                    }
                })
                .build();

        adapter = new FirebaseRecyclerAdapter<Accounts, AccountsViewHolder>(options) {
            @Override
            public AccountsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listaccounts, parent, false);

                return new AccountsViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(AccountsViewHolder holder, int position, Accounts model) {
                holder.setTexnum(model.getAccnumber());
                holder.setTexname(model.getAccountname());
                holder.setTextype(model.getAccounttype());
                holder.setTexbname(model.getBankname());
                holder.setTexdes(model.getDescription());
                holder.setTexbalance(model.getOpeningBalance());





                holder.Editbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final DialogPlus dailogPlus = DialogPlus.newDialog(holder.texnum.getContext())
                                .setContentHolder(new ViewHolder(R.layout.updateaccounts))
                                .setExpanded(true,900).setMargin(20, 20, 20, 20)

                                .create();

                        View view = dailogPlus.getHolderView();

                        EditText upaccnum = view.findViewById(R.id.updateAccnum);
                        EditText upaccname = view.findViewById(R.id.updateAccname);
                        EditText uptype = view.findViewById(R.id.updateAcctype);
                        EditText upbname = view.findViewById(R.id.updatebname);
                        EditText updes = view.findViewById(R.id.updatedes);
                        EditText upbalance = view.findViewById(R.id.updatebalance);



                        Button upbtn = view.findViewById(R.id.updatebtn);

                        upaccnum.setText(model.getAccnumber());
                        upaccname.setText(model.getAccountname());
                        uptype.setText(model.getAccounttype());
                        upbname.setText(model.getBankname());
                        updes.setText(model.getDescription());
                        upbalance.setText(model.getOpeningBalance());


                        dailogPlus.show();

                        upbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("num",upaccnum.getText().toString());
                                map.put("name",upaccname.getText().toString());
                                map.put("type",uptype.getText().toString());
                                map.put("bname",upbname.getText().toString());
                                map.put("description",updes.getText().toString());
                                map.put("balance",upbalance.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child("Accounts")
                                        .child(getRef(position).getKey()).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(holder.texname.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                                dailogPlus.dismiss();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(Exception e) {
                                                Toast.makeText(holder.texname.getContext(), "Error While Updating ", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                            }
                        });

                    }
                });

                holder.Deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.texname.getContext());
                        builder.setTitle("Are you sure..?");
                        builder.setMessage("Deleted data can't be Undo");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference().child("Accounts")
                                        .child(getRef(position).getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.texname.getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
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