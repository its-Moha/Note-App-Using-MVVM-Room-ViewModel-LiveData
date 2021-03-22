package com.example.mvvmarchitectureandroom;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity  (tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    //private int priority;

    // constructor method
    public Note(String title, String description/**int priority**/) {
        this.title = title;
        this.description = description;
        //this.priority = priority;
    }
    // setter method


    public void setId(int id) {
        this.id = id;
    }

    // getter method
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

   /** public int getPriority() {
        return priority;
    }**/
}
