package screenObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testUtils.AndroidActions;

import java.time.Duration;
import java.util.List;

public class LoginObjectsScreen extends AndroidActions {

    public LoginObjectsScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countries;

    @AndroidFindBy(id="android:id/text1")
    private WebElement countryName;

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement clientNameField;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleRadioButton;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
    private WebElement maleRadioButton;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopButton;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Products']")
    private WebElement productPageName;

    @AndroidFindBy(xpath="(//android.widget.Toast)[1]")
    private WebElement toastMessage;

    public void setCountry(String countryName) {
        countries.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }

    public void setClientNameField(String clientName) {
        clientNameField.sendKeys(clientName);
        driver.hideKeyboard();
    }

    public void setInvalidShortClientNameField(String invalidShorClientName) {
        clientNameField.sendKeys(invalidShorClientName);
        driver.hideKeyboard();
    }

    public void setInvalidBigClientNameField(String invalidBigClientName) {
        clientNameField.sendKeys(invalidBigClientName);
        driver.hideKeyboard();
    }

    public void setGender(String gender) {
        if(gender.contains("female")) {
            femaleRadioButton.click();
        } else {
            maleRadioButton.click();
        }
    }

        public String getClientNameText() {
            return clientNameField.getText();
    }

    public String getCountryNameText() {
        return countryName.getText();
    }

    public void clickLetsShopButton() {
        letsShopButton.click();
    }

    public String getProductPageTitle() {
        return productPageName.getText();
    }

    public String getToastMessage() {
        return toastMessage.getAttribute("name");
    }

    public void verifyLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean variable = true;

        while(variable) {
            try {
                WebElement pageIdentifier = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/btnLetsShop")));
                if(pageIdentifier.isDisplayed()) {
                    variable = false;
                }
            } catch (TimeoutException e) {
                System.out.println("Elemento não encontrado, voltando uma página.");
                driver.navigate().back();
            }
        }
    }
}
