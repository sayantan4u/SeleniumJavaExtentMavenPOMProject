package pageEvents;

import pageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {
    ElementFetch ele = new ElementFetch();
    public void login(String uname, String pswd ) {
        ele.getWebElement("CSS", LoginPageElements.usernameField).sendKeys(uname);
        ele.getWebElement("CSS", LoginPageElements.passwordField).sendKeys(pswd);
        ele.getWebElement("CSS", LoginPageElements.loginButton).click();
    }

}
