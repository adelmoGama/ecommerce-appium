package ecommerceAppium;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppiumLoginScreenTest extends AppiumConectionConfig {
    @Parameters({"countryName", "clientName", "gender"})
    @Test
    public void LoginSuccessfullyTest(String countryName, String clientName, String gender) throws InterruptedException {
        loginObjectsScreen.setCountry(countryName);

        loginObjectsScreen.setClientNameField(clientName);

        loginObjectsScreen.setGender(gender);

        String getClientName = loginObjectsScreen.getClientNameText();
        String getCountyName = loginObjectsScreen.getCountryNameText();

        Assert.assertEquals(getClientName, "Branca de Neve");
        Assert.assertEquals(getCountyName, "Argentina");

        loginObjectsScreen.clickLetsShopButton();

        String pageName = loginObjectsScreen.getProductPageTitle();

        Assert.assertEquals(pageName, "Products");
    }

    @Parameters({"countryName", "gender"})
    @Test
    public void AttemptWithoutFillingNameFieldTest(String countryName, String gender) {
        loginObjectsScreen.setCountry(countryName);

        loginObjectsScreen.setGender(gender);

        String getCountyName = loginObjectsScreen.getCountryNameText();

        Assert.assertEquals(getCountyName, "Argentina");

        loginObjectsScreen.clickLetsShopButton();

        String toastMessage = loginObjectsScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Parameters({"countryName", "invalidShorClientName" ,"gender"})
    @Test(enabled = false)
    public void LoginAttemptFillingNameFieldWithOneCharacterTest(String countryName, String invalidShorClientName, String gender) {
        loginObjectsScreen.setCountry(countryName);

        loginObjectsScreen.setInvalidShortClientNameField(invalidShorClientName);

        loginObjectsScreen.setGender(gender);

        String getClientName = loginObjectsScreen.getClientNameText();
        String getCountyName = loginObjectsScreen.getCountryNameText();

        Assert.assertEquals(getClientName, "v");
        Assert.assertEquals(getCountyName, "Brazil");

        loginObjectsScreen.clickLetsShopButton();

        String toastMessage = loginObjectsScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Parameters({"countryName", "invalidBigClientName" ,"gender"})
    @Test(enabled = false)
    public void LoginAttemptFillingNameFieldWith70CharactersTest(String countryName, String invalidBigClientName, String gender) {
        loginObjectsScreen.setCountry(countryName);

        loginObjectsScreen.setInvalidBigClientNameField(invalidBigClientName);

        loginObjectsScreen.setGender(gender);

        String getClientName = loginObjectsScreen.getClientNameText();
        String getCountyName = loginObjectsScreen.getCountryNameText();

        Assert.assertEquals(getClientName, "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
        Assert.assertEquals(getCountyName, "Brazil");

        loginObjectsScreen.clickLetsShopButton();

        String toastMessage = loginObjectsScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Number of characters bigger than the maximum value - 50");
    }
}
