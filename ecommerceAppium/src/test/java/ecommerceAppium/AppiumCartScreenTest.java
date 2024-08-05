package ecommerceAppium;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import screenObjects.CartObjectsScreen;
import utils.AndroidActions;

import java.util.List;

public class AppiumCartScreenTest extends AppiumConectionConfig {

    @Parameters({"countryName", "clientName", "gender", "productNamePG3", "productNameJordanRings"})
    @Test()
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

    @Parameters({"countryName", "clientName", "gender", "productName"})
    @Test(enabled = false)
    public void openingTermsSuccessfullyTest(String countryName, String clientName, String gender, String productName) {
        loginObjectsScreen.setCountry(countryName);
        loginObjectsScreen.setClientNameField(clientName);
        loginObjectsScreen.setGender(gender);
        loginObjectsScreen.clickLetsShopButton();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"));"));
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();

        String numberOfShops = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();

        Assert.assertEquals(numberOfShops, "1");

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebElement element = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));

        actions.longPressAction(element);

        String termsTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();

        Assert.assertEquals(termsTitle, "Terms Of Conditions");

        driver.findElement(By.id("android:id/button1")).click();
    }
}
