package com.example.mvp.login;

import android.support.annotation.Nullable;

/**
 * Created by laurent on 1/30/17.
 */

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    /**
     * This @Nullable help check for null reference whenever we access to the view field
     * Due to the lifecycle events we may not be sure that the 'view' exists and the activity may
     * have been destroyed by the operating system
     */
    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(view.getFirstName(), view.getLastName());
                view.showUserSavedMessage();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = model.getuser();

        if(user != null) {
            /**
             * We check if the view is still active because Android operating system can kill an
             * activity at any time.
             */
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        } else {
            view.showUserNotAvailable();
        }
    }

    /**
     * This method is her for training on the tests part but it's not used in the code
     */
    @Override
    public void saveUser() {

        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {

                model.createUser(view.getFirstName(), view.getLastName());
                view.showUserSavedMessage();

            }
        }

    }
}
