package ecommerceAppiumTests;

import configs.AppiumConnectionConfig;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AppiumProductScreenTest extends AppiumConnectionConfig {
    @Test(dataProvider = "getData")
    public void ProductSearchingSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.setCountry(data.get("countryName"));
        loginObjectsScreen.setClientNameField(data.get("clientName"));
        loginObjectsScreen.setGender(data.get("femaleGender"));
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(data.get("productNameSFBJungle"));

        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='Nike SFB Jungle']")).isDisplayed());
    }

    @Test(dataProvider = "getData")
    public void ProductAddingOneSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.setCountry(data.get("countryName"));
        loginObjectsScreen.setClientNameField(data.get("clientName"));
        loginObjectsScreen.setGender(data.get("femaleGender"));
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(data.get("productNameAllStar"));
        productObjectsScreen.addProductToShoppingCartByName(data.get("productNameAllStar"));

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();

        Assert.assertEquals(numberOfShops, "1");
    }

    @Test(dataProvider = "getData")
    public void ProductsAddingSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.setCountry(data.get("countryName"));
        loginObjectsScreen.setClientNameField(data.get("clientName"));
        loginObjectsScreen.setGender(data.get("femaleGender"));
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(data.get("productNamePG3"));
        productObjectsScreen.addProductToShoppingCartByName(data.get("productNamePG3"));

        productObjectsScreen.scrollToProduct(data.get("productNameJordanRings"));
        productObjectsScreen.addProductToShoppingCartByName(data.get("productNameJordanRings"));

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();
        Assert.assertEquals(numberOfShops, "2");
    }
}
