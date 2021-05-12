package com.example.gitapplication;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecordViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout root;
    public TextView texName;
    public TextView texType;
    public TextView texAmount;
    public Button editbtn,deletebtn;

    public RecordViewHolder(View itemView ) {
        super(itemView);

        root = itemView.findViewById(R.id.list_root);
        texName = itemView.findViewById(R.id.list_name);
        texType = itemView.findViewById(R.id.list_type);
        texAmount = itemView.findViewById(R.id.list_amount);

        editbtn = itemView.findViewById(R.id.btnedit);
        deletebtn = itemView.findViewById(R.id.btndelete);

    }


    public void setTexName(String string) {
        texName.setText(string);
    }

    public void setTexType(String string) {
        texType.setText(string);
    }

    public void setTexAmount(String string) {
        texAmount.setText(string);
    }
}
