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
    public int mUserID;
    @NonNull
    @ColumnInfo(name = "username")
    public String mUsername;
    @NonNull
    @ColumnInfo(name = "mail")
    public String mMail;


    public User(@NonNull int userID, @NonNull String username, @NonNull String mail) {
        this.mUserID = userID;
        this.mUsername = username;
        this.mMail = mail;

    }

    public int getUser(){
        return this.mUserID;
    }

}
