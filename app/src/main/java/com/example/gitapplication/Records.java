package com.example.gitapplication;

public class Records {

    String id;
    String name;
    String type;
    String amount;

    public Records(String id, String name, String type, String amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;

    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

}
