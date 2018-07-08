package com.example.haeilcho.pagingapplication;

import android.app.Application;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MyViewModel extends ViewModel {

    @Inject Application application;

    private UserDao userDao;
    private LiveData<PagedList<User>> usersList;

    public LiveData<PagedList<User>> getUsersList() {
        return usersList;
    }

    public MyViewModel(){
        MyViewModelComponent viewModelComponent = DaggerMyViewModelComponent.create();
        this.application = viewModelComponent.setApplication();

        this.userDao = AppDatabase.getAppDatabase(this.application).userDao();
        usersList = new LivePagedListBuilder<>(
                userDao.usersByLastName(), /* page size */ 20).build();
    }


}
