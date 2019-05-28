package com.example.driveandlog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;
    private LiveData<List<User>> mAllUsers;


    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    LiveData<List<User>> getAllUsers(){
        return getAllUsers();
    }

    public void insert(User user) {
        mRepository.insert(user);
    }
}
