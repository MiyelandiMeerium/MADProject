package com.example.gitapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;



public class ListAdapter  extends ArrayAdapter {
    private Activity mContext;
    List<Accounts>  accountsList;

    public ListAdapter(Activity mContext, List<Accounts> accountsList ) {
        super(mContext, R.layout.listaccounts , accountsList);
        this.mContext = mContext;
        this.accountsList = accountsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listAccountsView = inflater.inflate(R.layout.listaccounts, null, true);

        TextView Accnum = listAccountsView.findViewById(R.id.Accnum);
        TextView Accname = listAccountsView.findViewById(R.id.Accname);
        TextView Acctype = listAccountsView.findViewById(R.id.Acctype);
        TextView bname = listAccountsView.findViewById(R.id.bname);
        TextView des = listAccountsView.findViewById(R.id.des);
        TextView balance = listAccountsView.findViewById(R.id.balance);

        Accounts accounts = accountsList.get(position);

        Accnum.setText(accounts.getAccnumber());
        Accname.setText(accounts.getAccnumber());
        Acctype.setText(accounts.getAccounttype());
        bname.setText(accounts.getBankname());
        des.setText(accounts.getDescription());
        balance.setText(accounts.getOpeningBalance());

        return listAccountsView;
    }
}
