package ecommerceAppiumTests;

import configs.AppiumConectionConfig;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import screenObjects.CartObjectsScreen;
import utils.AndroidActions;

import java.util.List;

public class AppiumCartScreenTest extends AppiumConectionConfig {

    @Parameters({"countryName", "clientName", "gender", "productNamePG3", "productNameJordanRings"})
    @Test(enabled = false)
    public void shoppingCartAmountSuccessfullyTest(String countryName, String clientName, String gender, String productNamePG3, String productNameJordanRings) throws InterruptedException {
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

        CartObjectsScreen cartObjectsScreen = productObjectsScreen.clickOnShoppingCartIcon();

        String amount = cartObjectsScreen.getAmountOnShoppingCart();
        List<String> names = cartObjectsScreen.getCartProductsNameList();

        double productsAmount = cartObjectsScreen.getCartProductsAmount();

        Double totalAmount = AndroidActions.getFormattedAmount(amount);

        Assert.assertEquals(totalAmount, productsAmount);
        Assert.assertEquals(names.size(), 2);
        Assert.assertTrue(names.contains("PG 3"));
        Assert.assertTrue(names.contains("Jordan 6 Rings"));
    }

    @Parameters({"countryName", "clientName", "gender", "productNamePG3"})
    @Test()
    public void openingTermsSuccessfullyTest(String countryName, String clientName, String gender, String productNamePG3) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(productNamePG3);
        productObjectsScreen.addProductToShoppingCartByName(productNamePG3);

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();

        Assert.assertEquals(numberOfShops, "1");

        CartObjectsScreen cartObjectsScreen = productObjectsScreen.clickOnShoppingCartIcon();

        actions.longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));

        String termsTitle = cartObjectsScreen.getTermsTitle();

        Assert.assertEquals(termsTitle, "Terms Of Conditions");

        cartObjectsScreen.closeTermsTitle();
    }
}
