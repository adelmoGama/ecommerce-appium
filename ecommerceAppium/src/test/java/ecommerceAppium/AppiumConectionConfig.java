package ecommerceAppium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class AppiumConectionConfig {

    public AppiumDriverLocalService appiumService;
    public AndroidDriver driver;

    @BeforeClass
    public void appiumAutostartTheEmulator() {
        appiumService = new AppiumServiceBuilder()
                .withAppiumJS(new File("C://Users//USUARIO//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        appiumService.start();
    }
    @BeforeMethod
    public void appiumAutostartTheDriver() throws URISyntaxException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("emulatorTest2");
        options.setApp("C://Projetos//ws-intelliJ//ecommerce-appium//ecommerceAppium//src//test//java//resources//General-Store.apk");
        options.setAutomationName("UiAutomator2").setPlatformName("android");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDownAppium() {
        appiumService.stop();
    }

    @AfterMethod
    public void tearDownDriver() {
        driver.quit();
    }

    public void longPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",
                        ((RemoteWebElement) element).getId(),
                        "duration", 2000));
    }

    public void startActivity(String currentFocus) {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: startActivity", ImmutableMap.of(
                        "intent", currentFocus
                ));
    }
}
