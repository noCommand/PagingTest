package com.example.haeilcho.pagingapplication;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MyViewModel extends ViewModel {

    @Inject Application application;

    private UserDao userDao;
    private LiveData<PagedList<User>> usersList;

    public LiveData<PagedList<User>> getUsersList() {
        return usersList;
    }

//    public void addUser(User newUser) {
//        userDao.insert(newUser);
//
//    }

    public MyViewModel(){
//        User user = new User();
//        user.setLastName("df");
//        PagedList<User> pagedList = new Page;
//        pagedList.add(user);
//        pagedList.add(user);
//        pagedList.add(user);
//        pagedList.add(user);
//        usersList.postValue(pagedList);

        MyViewModelComponent viewModelComponent = DaggerMyViewModelComponent.create();
        this.application = viewModelComponent.setApplication();

        this.userDao = AppDatabase.getAppDatabase(this.application).userDao();

//        PagedList.Config config = new PagedList.Config.Builder()
//                .setInitialLoadSizeHint(20)
//                .setPageSize(10)
//                .setPrefetchDistance(5)
//                .setEnablePlaceholders(true)
//                .build();
//        DataSource.Factory<Integer, User> dataSource = userDao.usersByLastName();
//
//        usersList = new LivePagedListBuilder(dataSource, config).setFetchExecutor(new Executor() {
//            @Override
//            public void execute(Runnable runnable) {
//                new Thread(runnable).start();
//            }
//        }).build();
//        usersList = new PagedList.Builder(userDao.usersByLastName(), config).build();
//
//
        usersList = new LivePagedListBuilder<>(
                userDao.usersByLastName(),20).build();
    }
    public LiveData<PagedList<User>> getUserList(int pageSize){
        return usersList;
    }


}
