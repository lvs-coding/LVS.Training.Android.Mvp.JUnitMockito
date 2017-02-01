package com.example.mvp.login;

/**
 * Created by laurent on 1/30/17.
 */

public interface LoginRepository {

    User getUser();

    void saveUser(User user);
}
