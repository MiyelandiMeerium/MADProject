package com.example.gitapplication;

public class Accounts {
    String Accnumber;
    String Accountname;
    String Accounttype;
    String Bankname;
    String Description;
    String OpeningBalance;

    public Accounts(String Accnumber , String Accountname , String Accounttype  , String Bankname , String Description , String OpeningBalance) {
        this.Accnumber = Accnumber;
        this.Accountname = Accountname;
        this.Accounttype = Accounttype;
        this.Bankname = Bankname;
        this.Description = Description;
        this.OpeningBalance = OpeningBalance;
    }

    public String getAccnumber() {
        return Accnumber;
    }

    public String getAccountname() {
        return Accountname;
    }

    public String getAccounttype() { return Accounttype; }

    public String getBankname() { return Bankname; }

    public String getDescription() { return Description; }

    public String getOpeningBalance() { return OpeningBalance; }
}
