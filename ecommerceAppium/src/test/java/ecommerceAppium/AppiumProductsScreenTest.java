package ecommerceAppium;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AppiumProductsScreenTest extends AppiumConectionConfig {
    @Test
    public void ProductSearchingSuccessfullyTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Pele");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Nike SFB Jungle\"));"));

        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='Nike SFB Jungle']")).isDisplayed());
    }

    @Test
    public void ProductAddingOneSuccessfullyTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Pele");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Converse All Star\"));"));

        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();

        String numberOfShops = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();

        Assert.assertEquals(numberOfShops, "1");
    }

    @Test
    public void ProductsAddingSuccessfullyTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Pele");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"));"));
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();

        String numberOfShops = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();

        Assert.assertEquals(numberOfShops, "2");
    }

    @Test
    public void ProductsShoppingCartAmountSuccessfullyTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Pele");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"));"));
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));

        int numberOfProducts = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for(int i = 0; i < numberOfProducts; i++) {
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if(productName.equalsIgnoreCase("Jordan 6 Rings")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }

        String numberOfShops = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();

        Assert.assertEquals(numberOfShops, "2");

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        String amount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        String name1 = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='PG 3']")).getText();
        String name2 = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName' and @text='Jordan 6 Rings']")).getText();

        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));

        double productsAmount = 0.0;

        for (WebElement productPrice : productPrices) {
            String amountPrice = productPrice.getText();
            double price = AppiumUtils.getFormattedAmount(amountPrice);
            productsAmount += price;
        }

        Double totalAmount = AppiumUtils.getFormattedAmount(amount);

        Assert.assertEquals(name1, "PG 3");
        Assert.assertEquals(name2, "Jordan 6 Rings");
        Assert.assertEquals(totalAmount, productsAmount);
    }

    @Test
    public void ProductsOpeningTermsSuccessfullyTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Pele");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"));"));
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();

        String numberOfShops = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();

        Assert.assertEquals(numberOfShops, "1");

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebElement element = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));

        longPressAction(element);

        String termsTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();

        Assert.assertEquals(termsTitle, "Terms Of Conditions");

        driver.findElement(By.id("android:id/button1")).click();
    }
}
