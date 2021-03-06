package com.example.mvp.root;

import android.app.Application;

import com.example.mvp.login.LoginModule;

import dagger.internal.DaggerCollections;

/**
 * This Application object is where Dagger will live throughout the entire life span of our
 * application.
 *
 * This is the default application class. The application name has to be modified in
 * AndroidManifest.xml according to this to launch our app (".root.App"). This way our application
 * will use this Application class to handle the initial initialization.
 */
public class App extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Android Studio show applicationModule as deprecated but it's not, check the doc for more
         * details.
         *
         * DaggerApplicationComponent is generated by Dagger on first build.
         *
         * Here we can define every module we have in our app (applicationModule + one module per
         * feature).
         */
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
