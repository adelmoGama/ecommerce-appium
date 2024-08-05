package ecommerceAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;
import screenObjects.LoginObjectsScreen;
import screenObjects.ProductObjectsScreen;
import utils.AndroidActions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class AppiumConectionConfig {

    public AppiumDriverLocalService appiumService;
    public AndroidDriver driver;

    public LoginObjectsScreen loginObjectsScreen;
    public ProductObjectsScreen productObjectsScreen;
    public AndroidActions actions;

    @BeforeClass
    public void appiumAutostartTheEmulator() throws URISyntaxException, MalformedURLException {
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

        options.setDeviceName("emulatorTest3");
        options.setApp("C://Projetos//ws-intelliJ//ecommerce-appium//ecommerceAppium//src//test//resources//General-Store.apk");
        options.setAutomationName("UiAutomator2").setPlatformName("android");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

        loginObjectsScreen = new LoginObjectsScreen(driver);
        productObjectsScreen = new ProductObjectsScreen(driver);
        actions = new AndroidActions(driver);

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
}
