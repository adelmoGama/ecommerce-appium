package ecommerceAppium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppiumProductScreenTest extends AppiumConectionConfig {
    @Parameters({"countryName", "clientName", "gender", "productNameSFBJungle"})
    @Test()
    public void ProductSearchingSuccessfullyTest(String countryName, String clientName, String gender, String productNameSFBJungle) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(productNameSFBJungle);

        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='Nike SFB Jungle']")).isDisplayed());
    }

    @Parameters({"countryName", "clientName", "gender", "productNameAllStar", "productIndex"})
    @Test()
    public void ProductAddingOneSuccessfullyTest(String countryName, String clientName, String gender, String productNameAllStar, int productIndex) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(productNameAllStar);

        productObjectsScreen.addProductToShoppingCartByIndex(productIndex);

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();

        Assert.assertEquals(numberOfShops, "1");
    }

    @Parameters({"countryName", "clientName", "gender", "productNamePG3", "productNameJordanRings"})
    @Test()
    public void ProductsAddingSuccessfullyTest(String countryName, String clientName, String gender, String productNamePG3, String productNameJordanRings) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(productNamePG3);
        productObjectsScreen.addProductToShoppingCartByIndex(1);

        productObjectsScreen.scrollToProduct(productNameJordanRings);
        productObjectsScreen.addProductToShoppingCartByName(productNameJordanRings);

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();
        Assert.assertEquals(numberOfShops, "2");
    }
}
