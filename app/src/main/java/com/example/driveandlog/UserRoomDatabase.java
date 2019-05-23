package com.example.driveandlog;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {User.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {


    public abstract UserDao userDao();


    private static volatile UserRoomDatabase instance;

    static UserRoomDatabase getDatabase(final Context context){
        if (instance == null){
            synchronized (UserRoomDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return instance;
    }


}
