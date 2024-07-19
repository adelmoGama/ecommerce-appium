package ecommerceAppium;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumTest extends AppiumConectionConfig {
    @Test
    public void AppiumSelectCountrySuccessfullyTest() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Brazil']")).click();

        String countyName = driver.findElement(By.id("android:id/text1")).getText();

        Assert.assertEquals(countyName, "Brazil");
    }

    @Test
    public void AppiumFillFormSuccessfullyTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Poland\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Branca de Neve");

        driver.hideKeyboard();

        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

        String clientName = driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).getText();
        String countyName = driver.findElement(By.id("android:id/text1")).getText();

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Assert.assertEquals(clientName, "Branca de Neve");
        Assert.assertEquals(countyName, "Poland");
    }
}
