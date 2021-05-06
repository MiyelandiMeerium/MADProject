package com.example.gitapplication;

public class Accounts {
    Integer  accountNum;
    String   accountName;

    public Accounts( Integer  accountNum , String   accountName){
        this.accountNum = accountNum;
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public Integer getAccountNum() {
        return accountNum;
    }
}
