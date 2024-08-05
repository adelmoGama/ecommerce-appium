package ecommerceAppium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import screenObjects.CartObjectsScreen;

public class AppiumBrowserTest extends AppiumConectionConfig {

    @Parameters({"countryName", "clientName", "gender", "productNamePG3"})
    @Test(enabled = false)
    public void BrowserOpeningAppSuccessfullyTest(String countryName, String clientName, String gender, String productNamePG3) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(productNamePG3);
        productObjectsScreen.addProductToShoppingCartByName(productNamePG3);

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();
        Assert.assertEquals(numberOfShops, "1");

        productObjectsScreen.clickOnShoppingCartIcon();

        browserObjectsScreen.clickToProceed();

        driver.context("WEBVIEW_com.androidsample.generalstore");
    }

    @Parameters({"countryName", "clientName", "gender", "productNamePG3"})
    @Test()
    public void BrowserSearchingOnAppSuccessfullyTest(String countryName, String clientName, String gender, String productNamePG3) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(productNamePG3);
        productObjectsScreen.addProductToShoppingCartByName(productNamePG3);

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();
        Assert.assertEquals(numberOfShops, "1");

        productObjectsScreen.clickOnShoppingCartIcon();

        browserObjectsScreen.clickToProceed();

        driver.findElement(By.name("q")).sendKeys("Lebron James");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Parameters({"countryName", "clientName", "gender", "productNamePG3"})
    @Test(enabled = false)
    public void BrowserReturningToAppSuccessfullyTest(String countryName, String clientName, String gender, String productNamePG3) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(productNamePG3);
        productObjectsScreen.addProductToShoppingCartByName(productNamePG3);

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();
        Assert.assertEquals(numberOfShops, "1");

        productObjectsScreen.clickOnShoppingCartIcon();

        browserObjectsScreen.clickToProceed();

        driver.findElement(By.name("q")).sendKeys("Lebron James");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }

}
