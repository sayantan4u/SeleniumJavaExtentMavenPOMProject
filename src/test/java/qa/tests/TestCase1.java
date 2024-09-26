package qa.tests;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.LoginPageEvents;
import pageEvents.InventoryPageEvents;
import utils.ElementFetch;

//Run All
public class TestCase1 extends BaseTest {
    ElementFetch ele = new ElementFetch();
    LoginPageEvents loginPage = new LoginPageEvents();
    InventoryPageEvents inventoryPage = new InventoryPageEvents();

    @Test
    //Run | Debug
    public void loginCredentials() {
        logger.log(Status.INFO,"Login as Valid User");
        loginPage.login("standard_user", "secret_sauce");
        logger.log(Status.INFO,"Verifying Logging is successful!");
        Assert.assertTrue(inventoryPage.verifyInventoryPageIsLoaded(), "Inventory Page is not Loaded!");
    }

    @Test
    public void addItemsToCart(){
        loginCredentials();
        logger.log(Status.INFO,"Adding 'Backpack' into Cart");
        inventoryPage.addItemToCart("sauce-labs-backpack");
        logger.log(Status.INFO,"Adding 'Bolt T-shirt' into Cart");
        inventoryPage.addItemToCart("sauce-labs-bolt-t-shirt");
        logger.log(Status.INFO,"Adding 'Fleece Jacket' into Cart");
        inventoryPage.addItemToCart("sauce-labs-fleece-jacket");
    }
}
