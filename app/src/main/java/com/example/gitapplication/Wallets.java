package com.example.gitapplication;

public class Wallets {

    String name;
    String description;
    String category;
    String tasktype;
    String selecttask;
    String account;
    String amount;


    public Wallets(String name, String description, String category, String tasktype, String selecttask, String account, String amount) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.tasktype = tasktype;
        this.selecttask = selecttask;
        this.account = account;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getTasktype() {
        return tasktype;
    }

    public String getSelecttask() {
        return selecttask;
    }

    public String getAccount() {
        return account;
    }

    public String getAmount() {
        return amount;
    }
}
