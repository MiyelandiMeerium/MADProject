package com.example.gitapplication;

public class Accounts {
    String id;
    String accnumber;
    String accountname;
    String accounttype;
    String bankname;
    String description;
    String openingBalance;

    public Accounts( String id, String accnumber, String accountname, String accounttype , String bankname , String description , String openingBalance) {
        this.id = id;
        this.accnumber = accnumber;
        this.accountname = accountname;
        this.accounttype = accounttype;
        this.bankname = bankname;
        this.description = description;
        this.openingBalance = openingBalance;
    }

    public String getId() {
        return id;
    }

    public String getAccnumber() {
        return accnumber;
    }

    public String getAccountname() {
        return accountname;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public String getBankname() {
        return bankname;
    }

    public String getDescription() {
        return description;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }
}
