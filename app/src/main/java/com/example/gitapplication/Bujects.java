package com.example.gitapplication;

public class Bujects {

    String id;
    String name;
    String period;
    String amount;

    public Bujects(String id, String name, String period, String amount) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPeriod() {
        return period;
    }

    public String getAmount() {
        return amount;
    }
}
