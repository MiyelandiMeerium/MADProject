package com.example.gitapplication;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class
AccountsViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout root;
    public TextView texnum;
    public TextView texname;
    public TextView textype;
    public TextView texbname;
    public TextView texdes;
    public TextView texbalance;
    public Button Editbtn,Deletebtn;

    public AccountsViewHolder( View itemView) {
        super(itemView);

        root = itemView.findViewById(R.id.list_root);
        texnum = itemView.findViewById(R.id.accnum4);
        texname = itemView.findViewById(R.id.accname3);
        textype = itemView.findViewById(R.id.acctype3);
        texbname = itemView.findViewById(R.id.bname1);
        texdes = itemView.findViewById(R.id.des1);
        texbalance = itemView.findViewById(R.id.openbalance1);
        Editbtn = itemView.findViewById(R.id.editbtn);
        Deletebtn = itemView.findViewById(R.id.deletebtn);



    }

    public void setTexnum(String string) { texnum.setText(string); }

    public void setTexname(String string) { texname.setText(string); }

    public void setTextype(String string) { textype.setText(string); }

    public void setTexbname(String string) { texbname.setText(string); }

    public void setTexdes(String string) { texdes.setText(string); }

    public void setTexbalance(String string) { texbalance.setText(string); }
}
