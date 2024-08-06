package configs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import screenObjects.BrowserObjectsScreen;
import screenObjects.LoginObjectsScreen;
import screenObjects.ProductObjectsScreen;
import utils.AndroidActions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumConnectionConfig {

    public AppiumDriverLocalService appiumService;
    public AndroidDriver driver;

    public LoginObjectsScreen loginObjectsScreen;
    public ProductObjectsScreen productObjectsScreen;
    public BrowserObjectsScreen browserObjectsScreen;
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
        browserObjectsScreen = new BrowserObjectsScreen(driver);
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

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data =  getJsonData(System.getProperty("user.dir") + "//src//test//java//testData//data.json");

        return new Object[][] {{data.get(0)}};
    }
}
