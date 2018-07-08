package com.example.haeilcho.pagingapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;

public class DatabaseController{

    private static AppDatabase db;

    public DatabaseController(Context context) {
        db = AppDatabase.getAppDatabase(context);

    }

    public void insertUser(String firstname, String lastname) {
        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        db.userDao().insert(user);
    }

    public static List<User> selectUser(){
        List<User> userList = db.userDao().getAll();
        return userList;
    }

//    private static void AddTestData(AppDatabase db) {
//        deleteUser(db);
//
//        User user = new User();
//        user.setFirstName("Ajay");
//        user.setLastName("Saini");
//        addUser(db, user);
//
//        List<User> userList = db.userDao().getAll();
//        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size());
//    }

}
