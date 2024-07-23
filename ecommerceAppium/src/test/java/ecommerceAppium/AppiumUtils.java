package ecommerceAppium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;

public class AppiumUtils {
    public AndroidDriver driver;

    public void startActivity() {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: startActivity", ImmutableMap.of(
                        "intent", "95fcc3a u0 com.androidsample.generalstore/com.androidsample.generalstore.AllProductsActivity"
                ));
    }

    public static Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }
}
