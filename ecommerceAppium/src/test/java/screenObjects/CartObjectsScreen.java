package screenObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

public class CartObjectsScreen extends AndroidActions {

        public CartObjectsScreen(AndroidDriver driver) {
            super(driver);
            this.driver = driver;
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }
}
