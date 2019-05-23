package com.example.driveandlog;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "userID")
    private int mUserID;
    @NonNull
    @ColumnInfo(name = "username")
    private String mUsername;
    @NonNull
    @ColumnInfo(name = "email")
    private String mUserEmail;

    public User(@NonNull int userID, @NonNull String username, @NonNull String email) {
        this.mUserID = userID;
        this.mUsername = username;
        this.mUserEmail = email;
    }

    public int getUser(){
        return this.mUserID;
    }

}
