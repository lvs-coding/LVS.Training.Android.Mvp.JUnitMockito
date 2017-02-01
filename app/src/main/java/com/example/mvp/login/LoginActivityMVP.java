package com.example.mvp.login;

/**
 * This interface doesn't have any implementation but just acts as a container for other
 * important interfaces. Good convention to follow but not necessary.
 *
 * This way we can be assured these 3 components are tied together by a system in our case MVP
 * The suffix MVP in the name of the interface makes it very clear to the reviewer of the code that
 * we are using MVP pattern.
 *
 */
public interface LoginActivityMVP {

    interface View {
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSavedMessage();

        void setFirstName(String firstName);
        void setLastName(String lastName);

    }

    interface Presenter {

        void setView(LoginActivityMVP.View view);

        /**
         * Usually when we have buttons inside a view we instruct the presenter to handle click
         * events for our buttons
         */
        void loginButtonClicked();

        void getCurrentUser();

        void saveUser();

    }

    interface Model {

        void createUser(String firstName, String lastName);

        /**
         * This method is just forwarding the request to the repository because we don't have
         * any business logic to apply in our view model
         * @return
         */
        User getuser();

    }
}
