package pageObjects;

public interface LoginPageElements {
    String usernameField = "*[data-test=\"username\"]";
    String passwordField = "*[data-test=\"password\"]";
    String loginButton = "*[data-test=\"login-button\"]";
    String errorMessageField = "*[data-test=\"error\"]";
}
