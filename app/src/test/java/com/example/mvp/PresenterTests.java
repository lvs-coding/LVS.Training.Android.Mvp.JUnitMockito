package com.example.mvp;

import com.example.mvp.login.LoginActivity;
import com.example.mvp.login.LoginActivityMVP;
import com.example.mvp.login.LoginActivityPresenter;
import com.example.mvp.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


/**
 * In all tests we are trying to get as far as possible away from the Android framework to code
 * and run our tests.
 */
public class PresenterTests {

    //Will mock the Model interfacce
    LoginActivityMVP.Model mockLoginModel;
    //Will mock the View interface
    LoginActivityMVP.View mockView;
    LoginActivityMVP.Presenter presenter;
    User user;

    /**
     * The @Before annotation tells JUnit to start this method first every time it runs a test. This
     * method is know as a setup method. In this method we will reset the states of our object to be
     * sure they are not affected by a previous test. Think of it as a cleanup or initialization
     * process. This method have to be annotated with @Before but can have any name.
     *
     * This method will be executed for each test run.
     */
    @Before
    public void setup() {
        /**
         * Mock object for our model
         * We can do this because it's an interface
         */
        mockLoginModel = mock(LoginActivityMVP.Model.class);

        user = new User("Fox","Mulder");

        /**
         * Instruct our Mockito repository to return the user object which we just setup
         * This will ensure that we have a know user entity to test against
         * thenReturn(user) will return a user object.
         *
         * This line should read like this : When somebody call the repository with the getuser() method
         * return the user object we defined above.
         */
        when(mockLoginModel.getuser()).thenReturn(user);

        /**
         * Mock object for our view
         * We can do this because it's an interface
         */
        mockView = mock(LoginActivityMVP.View.class);

        /**
         * We use a concrete implementation of the presenter because that concrete implementation is
         * the core subject of this test. We want to test logic and code in this concrete
         * implementation while isolating all other dependencies via mock objects.
         */
        presenter = new LoginActivityPresenter(mockLoginModel);
        presenter.setView(mockView);
    }

    /**
     * If the users first name or last name is not present, then an error message should be shown
     * to the user.
     */
    @Test
    public void shouldCreateErrorMessageIfFieldsAreEmpty() {
        //==== 1 - We test when firstName is null or empty ============================
        /**
         * We instruct Mockito to return an empty value from our mock view when getFirstName method
         * is called.         *
         * This would bethe actual behavior of our view in a case when there is null or empty text
         * in the text field.
         */
        // Set up the view mock
        when(mockView.getFirstName()).thenReturn(""); // empty string

        /**
         * We are trying to save the user from the presenter
         **/
        presenter.saveUser();

        verify(mockView,times(1)).getFirstName();   // verify that getFirstName is called only once
        verify(mockView,never()).getLastName();     // verify that getLastName is never called
        // Verify that the presenter display a user input error one time
        verify(mockView,times(1)).showInputError();

        //==== 2 - We test when lastName is null or empty ============================
        // Note that in real app this test should be done in a separate method

        // Now tell mockView to return a value for first name and an empty last name
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("");

        presenter.saveUser();

        verify(mockView, times(2)).getFirstName();      // Called two times now, one before and one now
        verify(mockView, times(1)).getLastName();       // Only called once
        verify(mockView, times(2)).showInputError();    // Called two times now, one before and one now
    }

    /**
     * This test is describing that if a user is entering the firstname and lastName then it will
     * create a successful object and return a toast message to the user saying "everything is ok"
     */
    @Test
    public void shouldBeAbleToSaveAValiduser() {
        when(mockView.getFirstName()).thenReturn("Dana");
        when(mockView.getLastName()).thenReturn("Scully");

        presenter.saveUser();

        /**
         * Verify that getFirstName() and getLastName() is being called two times
         */
        // Called 2 more times in the saveUser call
        verify(mockView,times(2)).getFirstName();
        verify(mockView,times(2)).getLastName();

        /**
         * Verify that createUser has been called once
         */
        // Make sure the repository saved the user
        verify(mockLoginModel,times(1)).createUser("Dana","Scully");

        // Make sure that the view showed the user saved message
        verify(mockView,times(1)).showUserSavedMessage();

    }

    @Test
    public void shouldShowErrorMessageWhenUserIsNull() {
        /**
         * We are setting up our loginmodel mocked object to return a null value when getUser method
         * is invoked.
         */
        when(mockLoginModel.getuser()).thenReturn(null);

        presenter.getCurrentUser();

        /**
         * Verify that only one action is executed on the mockLoginModel
         */
        //verify model interactions
        verify(mockLoginModel,times(1)).getuser();

        /**
         * we make sure that setFirstName() and setLastName() are never called.
         * we verify that showUserNotAvailable is called only once
         */
        //verify view interactions
        verify(mockView,never()).setFirstName("Fox");
        verify(mockView,never()).setLastName("Mulder");
        verify(mockView,times(1)).showUserNotAvailable();
    }

    /**
     * This test could be said : When an actual user is found the user interface should be updated
     * with the users first and last name and no error should be shown to the user.
     * If any of these expectations are not met, the test will fail and we will know there is a
     * potential bug in the presenter.
     */
    @Test
    public void loadTheUserFromRepositoryWhenValiduserIsPresent() {
        when(mockLoginModel.getuser()).thenReturn(user);
        presenter.getCurrentUser();

        /**
         * Mockito can ensure wether a mock() method is being called with required arguments or not.
         * It is done using the verify() method.
         */

        /**
         * Verify that the getuser() method has been called only once
         */
        //verify model interactions
        verify(mockLoginModel, times(1)).getuser();

        /**
         * Verify that the user interface is updated correctly
         *
         * We check that setFirstName() and setLastName() are only called once.
         * We make sure that teh showUserNotAvailable() method is never invoked.
         *
         */
        //verify view interactions
        verify(mockView, times(1)).setFirstName("Fox");
        verify(mockView, times(1)).setLastName("Mulder");
        verify(mockView, never()).showUserNotAvailable();
    }

    /**
     * This type of lengthy and descriptive name convention for our test method is called
     * behavior driven naming. It makes it easier to recognize the tests and understand
     * immediately what is the focus of a particular test method by looking at its name.
     *
     * For this test is valid we have to comment when(mockLoginModel.getuser()).thenReturn(user) in
     * setup() method. And we have to comment the "else" in the LoginActivityPresenter
     */
    @Test
    public void noInteractionWithView() {
        presenter.getCurrentUser();

        /**
         * We are telling the presenter to save a null value and verify that nothing had changed
         * inside the view and no interactions took place inside the view.
         *
         * If we inspect the implementation of getCurrentUser() method we will see that we are
         * checking for null user value. If the value for the user is null then there is no action
         * taken by the presenter.
         */

        /**
         * This check that when the presenter.getCurrentUser() method is called while the value of
         * the user is null no methods of the view are called.
         */
        verifyZeroInteractions(mockView);

    }


}
