package com.example.haeilcho.pagingapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MyViewModel extends ViewModel {

    public final LiveData<PagedList<User>> usersList;

    public LiveData<PagedList<User>> getUsersList() {
        return usersList;
    }

    public MyViewModel(){
        usersList = null;
    }

    public MyViewModel(UserDao userDao) {
        usersList = new LivePagedListBuilder<>(
                userDao.usersByLastName(), /* page size */ 20).build();
    }
}
