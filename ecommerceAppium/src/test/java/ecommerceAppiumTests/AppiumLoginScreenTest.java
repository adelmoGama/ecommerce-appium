package ecommerceAppiumTests;

import configs.AppiumConnectionConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;


public class AppiumLoginScreenTest extends AppiumConnectionConfig {
    @Test(dataProvider = "getData")
    public void LoginSuccessfullyTest(HashMap<String, String> data) {
        loginObjectsScreen.verifyLoginPage();

        loginObjectsScreen.setCountry(data.get("countryName"));

        loginObjectsScreen.setClientNameField(data.get("clientName"));

        loginObjectsScreen.setGender(data.get("femaleGender"));

        String getClientName = loginObjectsScreen.getClientNameText();
        String getCountyName = loginObjectsScreen.getCountryNameText();

        Assert.assertEquals(getClientName, data.get("clientName"));
        Assert.assertEquals(getCountyName, data.get("countryName"));

        loginObjectsScreen.clickLetsShopButton();

        String pageName = loginObjectsScreen.getProductPageTitle();

        Assert.assertEquals(pageName, "Products");
    }

    @Test(dataProvider = "getData")
    public void AttemptWithoutFillingNameFieldTest(HashMap<String, String> data) {
        loginObjectsScreen.verifyLoginPage();

        loginObjectsScreen.setCountry(data.get("countryName"));

        loginObjectsScreen.setGender(data.get("femaleGender"));

        String getCountyName = loginObjectsScreen.getCountryNameText();

        loginObjectsScreen.clickLetsShopButton();

        String toastMessage = loginObjectsScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test(dataProvider = "getData", enabled = false)
    public void LoginAttemptFillingNameFieldWithOneCharacterTest(HashMap<String, String> data) {
        loginObjectsScreen.verifyLoginPage();

        loginObjectsScreen.setCountry(data.get("countryName"));

        loginObjectsScreen.setInvalidShortClientNameField(data.get("invalidShorClientName"));

        loginObjectsScreen.setGender(data.get("femaleGender"));

        String getClientName = loginObjectsScreen.getClientNameText();
        String getCountyName = loginObjectsScreen.getCountryNameText();

        Assert.assertEquals(getClientName, data.get("invalidShorClientName"));
        Assert.assertEquals(getCountyName, data.get("countryName"));

        loginObjectsScreen.clickLetsShopButton();

        String toastMessage = loginObjectsScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test(dataProvider = "getData", enabled = false)
    public void LoginAttemptFillingNameFieldWith70CharactersTest(HashMap<String, String> data) {
        loginObjectsScreen.verifyLoginPage();

        loginObjectsScreen.setCountry(data.get("countryName"));

        loginObjectsScreen.setInvalidBigClientNameField(data.get("invalidBigClientName"));

        loginObjectsScreen.setGender(data.get("femaleGender"));

        String getClientName = loginObjectsScreen.getClientNameText();
        String getCountyName = loginObjectsScreen.getCountryNameText();

        Assert.assertEquals(getClientName, data.get("invalidBigClientName"));
        Assert.assertEquals(getCountyName, data.get("countryName"));

        loginObjectsScreen.clickLetsShopButton();

        String toastMessage = loginObjectsScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Number of characters bigger than the maximum value - 50");
    }
}
