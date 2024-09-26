package pageEvents;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.InventoryPageElements;
import utils.ElementFetch;

public class InventoryPageEvents {
    ElementFetch ele = new ElementFetch();

    public boolean verifyInventoryPageIsLoaded(){
//        Assert.assertTrue(ele.getWebElement("XPATH", InventoryPageElements.inventoryPageTitleText).isDisplayed(), "Inventory Page is not Loaded!");
         return ele.getWebElement("XPATH", InventoryPageElements.inventoryPageTitleText).isDisplayed();
    }

    public void addItemToCart(String item) {
        ele.getWebElement("CSS", InventoryPageElements.addToCartButtonPrefix + item + InventoryPageElements.addToCartButtonSuffix).click();
    }

}
