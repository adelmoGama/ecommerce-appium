package ecommerceAppium;

import ecommerceAppium.AppiumConectionConfig;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumLoginTest extends AppiumConectionConfig {
    @Test
    public void AppiumLoginSuccessfullyTest() {
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

        String pageName = driver.findElement(By.xpath("//android.widget.TextView[@text='Products']")).getText();

        Assert.assertEquals(pageName, "Products");
    }

    @Test
    public void AppiumLoginAttemptWithoutFillingNameFieldTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();

        driver.hideKeyboard();

        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

        String countyName = driver.findElement(By.id("android:id/text1")).getText();

        Assert.assertEquals(countyName, "Brazil");

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    // THE APPLICATION DOESN'T SET MINIMUM AND MAXIMUM VALUES TO THE NAME FIELD
//    @Test
//    public void AppiumLoginAttemptFillingNameFieldWith1CaracterTest() {
//        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Poland\"));")).click();
//        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("v");
//
//        driver.hideKeyboard();
//
//        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
//
//        String clientName = driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).getText();
//        String countyName = driver.findElement(By.id("android:id/text1")).getText();
//
//        Assert.assertEquals(clientName, "v");
//        Assert.assertEquals(countyName, "Poland");
//
//        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
//
//        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
//
//        Assert.assertEquals(toastMessage, "Please enter your name");
//    }
}
