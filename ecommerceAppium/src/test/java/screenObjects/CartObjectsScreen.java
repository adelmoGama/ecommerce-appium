package screenObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import testUtils.AndroidActions;

import java.util.ArrayList;
import java.util.List;

public class CartObjectsScreen extends AndroidActions {

    public CartObjectsScreen(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement amountPrice;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productsPrice;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private List<WebElement> productsName;

    @AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
    private WebElement termsTitle;

    @AndroidFindBy(id="android:id/button1")
    private WebElement closeTermsButton;

    public String getAmountOnShoppingCart() {
        return amountPrice.getText();
    }

    public double getCartProductsAmount() {
        List<WebElement> elements = productsPrice;
        double productsAmount = 0.0;

        if(!elements.isEmpty()) {
            for (WebElement productPrice : productsPrice) {
                String amountPrice = productPrice.getText();
                double price = AndroidActions.getFormattedAmount(amountPrice);
                productsAmount += price;
            }
            return productsAmount;
        } else {
            throw new RuntimeException("The list of products is empty.");
        }
    }

    public List<String> getCartProductsNameList() {
        List<WebElement> elements = productsName;
        List<String> names = new ArrayList<>();

        if(!elements.isEmpty()) {
            for (WebElement element : elements) {
                String productName = element.getText();
                names.add(productName);
            }
            return names;
        } else {
            throw new RuntimeException("The list of products is empty.");
        }
    }

    public String getTermsTitle() {
        return termsTitle.getText();
    }

    public void closeTermsTitle() {
        closeTermsButton.click();
    }
}
