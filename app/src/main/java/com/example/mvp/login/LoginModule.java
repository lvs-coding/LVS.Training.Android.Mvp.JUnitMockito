package com.example.mvp.login;

import dagger.Module;
import dagger.Provides;

/**
 * Provide the necessary methods for the MVP module to work
 */
@Module
public class LoginModule {

    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model) {
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository) {
        return new LoginModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository() {
        return new MemoryRepository();
    }
}
