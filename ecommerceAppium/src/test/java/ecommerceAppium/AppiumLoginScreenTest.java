package ecommerceAppium;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppiumLoginScreenTest extends AppiumConectionConfig {
    @Parameters({"clientName"})
    @Test
    public void LoginSuccessfullyTest(String clientName) {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Poland\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys(clientName);

        driver.hideKeyboard();

        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

        String getClientName = driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).getText();
        String getCountyName = driver.findElement(By.id("android:id/text1")).getText();

        Assert.assertEquals(getClientName, "Branca de Neve");
        Assert.assertEquals(getCountyName, "Poland");

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String pageName = driver.findElement(By.xpath("//android.widget.TextView[@text='Products']")).getText();

        Assert.assertEquals(pageName, "Products");
    }

    @Test
    public void AttemptWithoutFillingNameFieldTest() {
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

    // THE APPLICATION DIDN'T SET A MINIMUM VALUE TO THE NAME FIELD
    @Test(enabled = false)
    public void LoginAttemptFillingNameFieldWith1CharacterTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("v");

        driver.hideKeyboard();

        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

        String clientName = driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).getText();
        String countyName = driver.findElement(By.id("android:id/text1")).getText();

        Assert.assertEquals(clientName, "v");
        Assert.assertEquals(countyName, "Brazil");

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    // THE APPLICATION DIDN'T SET A MAXIMUM VALUE TO THE NAME FIELD
    @Test(enabled = false)
    public void LoginAttemptFillingNameFieldWith70CharactersTest() {
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");

        driver.hideKeyboard();

        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

        String clientName = driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).getText();
        String countyName = driver.findElement(By.id("android:id/text1")).getText();

        Assert.assertEquals(clientName, "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
        Assert.assertEquals(countyName, "Brazil");

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");

        Assert.assertEquals(toastMessage, "Number of characters bigger than the maximum value - 50");
    }
}
