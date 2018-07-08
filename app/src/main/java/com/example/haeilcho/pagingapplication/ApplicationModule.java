package com.example.haeilcho.pagingapplication;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    Application provideApplication(){
        return new Application();
    }
}
