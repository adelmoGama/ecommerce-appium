package ecommerceAppiumTests;

import configs.AppiumConnectionConfig;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AppiumBrowserTest extends AppiumConnectionConfig {

    @Test(dataProvider = "getData")
    public void BrowserOpeningAppSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.setCountry(data.get("countryName"));
        loginObjectsScreen.setClientNameField(data.get("clientName"));
        loginObjectsScreen.setGender(data.get("femaleGender"));
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(data.get("productNamePG3"));
        productObjectsScreen.addProductToShoppingCartByName(data.get("productNamePG3"));

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();
        Assert.assertEquals(numberOfShops, "1");

        productObjectsScreen.clickOnShoppingCartIcon();

        browserObjectsScreen.clickToProceed();

        driver.context("WEBVIEW_com.androidsample.generalstore");
    }

    @Test(dataProvider = "getData")
    public void BrowserSearchingOnAppSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.setCountry(data.get("countryName"));
        loginObjectsScreen.setClientNameField(data.get("clientName"));
        loginObjectsScreen.setGender(data.get("femaleGender"));
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(data.get("productNamePG3"));
        productObjectsScreen.addProductToShoppingCartByName(data.get("productNamePG3"));

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();
        Assert.assertEquals(numberOfShops, "1");

        productObjectsScreen.clickOnShoppingCartIcon();

        browserObjectsScreen.clickToProceed();

        driver.findElement(By.name("q")).sendKeys("Lebron James");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Test(dataProvider = "getData")
    public void BrowserReturningToAppSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.setCountry(data.get("countryName"));
        loginObjectsScreen.setClientNameField(data.get("clientName"));
        loginObjectsScreen.setGender(data.get("femaleGender"));
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(data.get("productNamePG3"));
        productObjectsScreen.addProductToShoppingCartByName(data.get("productNamePG3"));

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
