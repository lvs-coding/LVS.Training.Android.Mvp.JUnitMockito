package com.example.mvp.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is where Dagger will keep tracks of the dependencies.
 *
 * It must be annotated with @Module so Dagger knows this is a Module.
 * Later on we will create modules for every feature we will build in our app (but not in the root
 * package, we will create a package for every feature and put the new module in it, "login" package
 * in our app)
 *
 * In such modules Dagger will look for variable methods and possible instance providers.
 */
@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    /**
     * The methods that will expose available return type should be annotated with @Provides decorator.
     * The @Singleton annotation also signals to Dagger that the instance should be created only once
     * in the application.
     *
     * We are specifying a context that use singleton annotation that can be used as a part of the
     * dependency list.
     * @return
     */
    @Provides
    @Singleton
    public Context  provideContext() {
        return application;
    }
}
