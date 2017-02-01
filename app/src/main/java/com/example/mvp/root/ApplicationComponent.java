package com.example.mvp.root;

import com.example.mvp.login.LoginActivity;
import com.example.mvp.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This application component tells Dagger where to inject the dependencies.
 * This component assigns references in our activities, services or fragments to have access to
 * the singleton.
 *
 * We need to annotate this class with the @Component declaration and set the modules to the
 * defined modules (ApplicationModule and LoginModuule in our app).
 *
 * Activities, services or fragments that can be added should be declared in this class with
 * individual inject() methods
 */
@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity target);
}
