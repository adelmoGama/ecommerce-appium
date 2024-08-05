package screenObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

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

    @AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.androidsample.generalstore:id/rvCartProductList']")
    private List<WebElement> cartProductsAddedList;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrices;

    public String getAmountOnShoppingCart() {
        return amountPrice.getText();
    }

    public List<String> getCartProductsAddedList() {
        List<WebElement> elements = cartProductsAddedList;
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

    public double getCartProductsAmount() {
        List<WebElement> elements = productPrices;
        double productsAmount = 0.0;

        if(!elements.isEmpty()) {
            for (WebElement productPrice : productPrices) {
                String amountPrice = productPrice.getText();
                double price = AndroidActions.getFormattedAmount(amountPrice);
                productsAmount += price;
            }
            return productsAmount;
        } else {
            throw new RuntimeException("The list of products is empty.");
        }
    }
}
