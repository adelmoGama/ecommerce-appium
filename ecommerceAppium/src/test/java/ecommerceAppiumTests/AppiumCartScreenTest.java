package ecommerceAppiumTests;

import configs.AppiumConnectionConfig;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import screenObjects.CartObjectsScreen;
import testUtils.AndroidActions;

import java.util.HashMap;
import java.util.List;

public class AppiumCartScreenTest extends AppiumConnectionConfig {

    @Test(dataProvider = "getData")
    public void shoppingCartAmountSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.verifyLoginPage();

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

        CartObjectsScreen cartObjectsScreen = productObjectsScreen.clickOnShoppingCartIcon();

        String amount = cartObjectsScreen.getAmountOnShoppingCart();
        List<String> names = cartObjectsScreen.getCartProductsNameList();

        double productsAmount = cartObjectsScreen.getCartProductsAmount();

        Double totalAmount = AndroidActions.getFormattedAmount(amount);

        Assert.assertEquals(totalAmount, productsAmount);
        Assert.assertEquals(names.size(), 2);
        Assert.assertTrue(names.contains(data.get("productNamePG3")));
        Assert.assertTrue(names.contains(data.get("productNameJordanRings")));
    }

    @Test(dataProvider = "getData")
    public void openingTermsSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.verifyLoginPage();

        loginObjectsScreen.setCountry(data.get("countryName"));
        loginObjectsScreen.setClientNameField(data.get("clientName"));
        loginObjectsScreen.setGender(data.get("femaleGender"));
        loginObjectsScreen.clickLetsShopButton();

        productObjectsScreen.scrollToProduct(data.get("productNamePG3"));
        productObjectsScreen.addProductToShoppingCartByName(data.get("productNamePG3"));

        String numberOfShops = productObjectsScreen.getNumberOfProductsOnShoppingCartIcon();

        Assert.assertEquals(numberOfShops, "1");

        CartObjectsScreen cartObjectsScreen = productObjectsScreen.clickOnShoppingCartIcon();

        actions.longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));

        String termsTitle = cartObjectsScreen.getTermsTitle();

        Assert.assertEquals(termsTitle, "Terms Of Conditions");

        cartObjectsScreen.closeTermsTitle();
    }
}
