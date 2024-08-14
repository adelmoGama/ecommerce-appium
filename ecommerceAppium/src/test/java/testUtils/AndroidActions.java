package testUtils;

import com.google.common.collect.ImmutableMap;
import configs.AppiumConnectionConfig;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumConnectionConfig {

    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
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
}
