package com.example.gitapplication;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class BujectViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout root;
    public TextView wName;
    public TextView wPeriod;
    public TextView wAmount;
    public Button bedit,bdelete;

    public BujectViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        root = itemView.findViewById(R.id.list_main);
        wName = itemView.findViewById(R.id.list_bname);
        wPeriod = itemView.findViewById(R.id.list_bperiod);
        wAmount = itemView.findViewById(R.id.list_bamount);

        bedit = itemView.findViewById(R.id.bedit);
        bdelete = itemView.findViewById(R.id.bdelete);
    }

    public void setRoot(LinearLayout root) {
        this.root = root;
    }


    public void setwName(String name) {
    }

    public void setwPeriod(String period) {
    }

    public void setwAmount(String amount) {
    }
}
