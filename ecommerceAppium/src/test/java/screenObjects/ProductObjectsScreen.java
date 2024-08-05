package screenObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class ProductObjectsScreen extends AndroidActions {
    public CartObjectsScreen cartObjectsScreen;

        public ProductObjectsScreen(AndroidDriver driver) {
            super(driver);
            this.driver = driver;
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

    @AndroidFindBy(xpath="(//android.widget.TextView[@text='ADD TO CART'])")
    private List<WebElement> addToCartButton;

    @AndroidFindBy(id="com.androidsample.generalstore:id/counterText")
    private WebElement numberOfProductsOnShoppingCartIcon;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    private List<WebElement> productsByName;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    public void scrollToProduct(String productName) {
        scrollToText(productName);
    }

    public void addProductToShoppingCartByIndex(int index) {
        List<WebElement> elements = addToCartButton;

        if(!elements.isEmpty()) {
            elements.get(index).click();
        } else {
            throw new RuntimeException("The list of products is empty.");
        }
    }

    public void addProductToShoppingCartByName(String name) {
        List<WebElement> elements = productsByName;

        if(!elements.isEmpty()) {
            for(int i = 0; i < elements.size(); i++) {
                String productName = elements.get(i).getText();
                if(productName.equalsIgnoreCase(name)) {
                    driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                }
            }
        } else {
            throw new RuntimeException("The list of products is empty.");
        }
    }

    public String getNumberOfProductsOnShoppingCartIcon() {
        return numberOfProductsOnShoppingCartIcon.getText();
    }

    public CartObjectsScreen clickOnShoppingCartIcon() {
        cartButton.click();
        return cartObjectsScreen = new CartObjectsScreen(driver);
    }
}
