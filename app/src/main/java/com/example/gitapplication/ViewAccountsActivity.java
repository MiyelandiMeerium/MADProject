package com.example.gitapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

                for (DataSnapshot accountDatasnap : snapshot.getChildren()) {

                    Accounts accounts = accountDatasnap.getValue(Accounts.class);
                    accountsList.add(accounts);
                }

                ListAdapter adapter = new ListAdapter(ViewAccountsActivity.this, accountsList);
                myListview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {


            }

        });

        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Accounts accounts = accountsList.get(position);
                showUpdateDialog(accounts.getAccnumber(), accounts.getAccountname());
                return false;
            }

        });

    }

    private void showUpdateDialog(final String Accnum, String Accname) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dlgView = inflater.inflate(R.layout.updateaccounts, null);

        dlg.setView(dlgView);

        EditText updateAccnum = dlgView.findViewById(R.id.updateAccnum);
        EditText updateAccname = dlgView.findViewById(R.id.updateAccname);
        Spinner updateAcctype = dlgView.findViewById(R.id.updateAcctype);
        EditText updatebname = dlgView.findViewById(R.id.updatebname);
        EditText updatedes = dlgView.findViewById(R.id.updatedes);
        EditText updatebalance = dlgView.findViewById(R.id.updatebalance);
        EditText updatebtn = dlgView.findViewById(R.id.updatebtn);
        EditText deletebtn = dlgView.findViewById(R.id.deletebtn);

        dlg.setTitle("Updating" + Accnum + "record");
        final AlertDialog alertDialog = dlg.create();
        dlg.show();

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newaccnum = updateAccnum.getText().toString();
                String newaccname = updateAccname.getText().toString();
                String newacctype = updateAcctype.getSelectedItem().toString();
                String newbname = updatebname.getText().toString();
                String newdes = updatedes.getText().toString();
                String newbalace = updatebalance.getText().toString();
                updatedata(newaccnum, newaccname, newacctype, newbname, newdes, newbalace);
                Toast.makeText(ViewAccountsActivity.this, "Record updated", Toast.LENGTH_SHORT).show();
            }
        });

       deletebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                deleteRecord(Accnum);
           }
       });

       
    }
     private void showToast (String message) {
     Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
     }
     private void deleteRecord(String Accnum) {
         DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Accounts").child(Accnum);

         Task<Void> task = myRef.removeValue();
         task.addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
             public void onSuccess(Void unused) {

                  showToast("Deleted");

             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull @NotNull Exception e) {
                       showToast("error deleting record");
             }
         });
     }
   private void updatedata( String Accnum, String Accname , String Acctype , String bname , String des, String balance){

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Accounts").child(Accnum);
                Accounts accounts = new Accounts(Accnum , Accname , Acctype ,bname ,des ,balance);
                myRef.setValue(accounts);
            }
}