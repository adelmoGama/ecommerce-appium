package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import configs.AppiumConectionConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class AndroidActions extends AppiumConectionConfig {

    public AndroidActions(AndroidDriver androidDriver) {
        this.driver = androidDriver;
    }

    public static Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }

    public void longPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",
                        ((RemoteWebElement) element).getId(),
                        "duration", 2000));
    }

    public void startActivity(String currentFocus) {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: startActivity",
                ImmutableMap.of("action", "",
                        "intent", currentFocus));
    }
    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

}
