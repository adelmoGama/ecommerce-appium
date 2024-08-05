package screenObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

public class BrowserObjectsScreen extends AndroidActions {

    public BrowserObjectsScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedButton;


    //TODO
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement searchField;

    public void clickToProceed() {
        proceedButton.click();
    }

    public void setSearchField(String text) {
        searchField.sendKeys(text);
    }

    public void sendSearchField() {
        searchField.sendKeys(Keys.ENTER);
    }

    public void returnToApp() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void getContext(String context) {
        driver.context(context);
    }
}
