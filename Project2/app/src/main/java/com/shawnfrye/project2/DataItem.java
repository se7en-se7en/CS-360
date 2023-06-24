package com.shawnfrye.project2;

public class DataItem {
    private int id; // New field to store the item ID
    private String text;

    public DataItem(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}

