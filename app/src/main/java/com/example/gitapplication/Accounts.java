package com.example.gitapplication;

public class Accounts {
    String Accnumber;
    String Accountname;

    public Accounts(String Accnumber , String Accountname ) {
        this.Accnumber = Accnumber;
        this.Accountname = Accountname;
    }

    public String getAccnumber() {
        return Accnumber;
    }

    public String getAccountname() {
        return Accountname;
    }

    public void setAccnumber(String accnumber) {
        Accnumber = accnumber;
    }

    public void setAccountname(String accountname) {
        Accountname = accountname;
    }
}
