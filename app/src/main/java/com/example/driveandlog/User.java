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
    private String mUsername;

    public  User(@NonNull int userID, @NonNull String mUsername){
        this.mUserID = userID;
        this.mUsername = mUsername;
    }

    public int getUserID(){
        return this.mUserID;
    }

    public String getUsername(){
        return this.mUsername;
    }
}
