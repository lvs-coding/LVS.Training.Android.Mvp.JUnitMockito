package com.example.mvp.login;

/**
 * Used to forward requests to the repository
 */
public class LoginModel implements LoginActivityMVP.Model {
    private LoginRepository repository;

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        repository.saveUser(new User(firstName,lastName));
    }

    @Override
    public User getuser() {
        /**
         * Here we just simply return the getUser from the repository but if you want to make any
         * transformations in the model objects this is where it will happen
         */
        return repository.getUser();
    }
}
