package com.example.driveandlog;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;





@Database(entities = {User.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {


    public abstract UserDao userDao();


    private static volatile UserRoomDatabase instance;

    static UserRoomDatabase getDatabase(final Context context){
        if (instance == null){
            synchronized (UserRoomDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){

            super.onOpen(db);
            new PopulateDbAsync(instance).execute();
        }
    };

    //AsyncTask - Kører proces asynkront fra UI
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final UserDao mDao;

        PopulateDbAsync(UserRoomDatabase db){
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params){
            mDao.dropAll();
            User user = new User(1,"Dune","dune@dune.dune");
            mDao.createUser(user);
            user = new User(2, "Mathias", "Mathias@hvaså.jo");
            mDao.createUser(user);
            return null;
        }
    }


}
