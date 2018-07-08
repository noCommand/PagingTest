package com.example.haeilcho.pagingapplication;

import android.app.Application;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface MyViewModelComponent {
    Application setApplication();
}
