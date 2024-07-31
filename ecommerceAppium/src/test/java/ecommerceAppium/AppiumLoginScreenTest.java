package ecommerceAppium;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppiumLoginScreenTest extends AppiumConectionConfig {
    @Parameters({"countryName", "clientName", "gender"})
    @Test
    public void LoginSuccessfullyTest(String countryName, String clientName, String gender) throws InterruptedException {
        loginScreen.setCountry(countryName);

        loginScreen.setClientNameField(clientName);

        loginScreen.setGender(gender);

        String getClientName = loginScreen.getClientNameText();
        String getCountyName = loginScreen.getCountryNameText();

        Assert.assertEquals(getClientName, "Branca de Neve");
        Assert.assertEquals(getCountyName, "Argentina");

        loginScreen.clickLetsShopButton();

        String pageName = loginScreen.getProductPageTitle();

        Assert.assertEquals(pageName, "Products");
    }

    @Test
    public void AttemptWithoutFillingNameFieldTest(String countryName, String gender) {
        loginScreen.setCountry(countryName);

        loginScreen.setGender(gender);

        String getCountyName = loginScreen.getCountryNameText();

        Assert.assertEquals(getCountyName, "Argentina");

        loginScreen.clickLetsShopButton();

        String toastMessage = loginScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test(enabled = false)
    public void LoginAttemptFillingNameFieldWithOneCharacterTest(String countryName, String invalidShorClientName, String gender) {
        loginScreen.setCountry(countryName);

        loginScreen.setInvalidShortClientNameField(invalidShorClientName);

        loginScreen.setGender(gender);

        String getClientName = loginScreen.getClientNameText();
        String getCountyName = loginScreen.getCountryNameText();

        Assert.assertEquals(getClientName, "v");
        Assert.assertEquals(getCountyName, "Brazil");

        loginScreen.clickLetsShopButton();

        String toastMessage = loginScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test(enabled = false)
    public void LoginAttemptFillingNameFieldWith70CharactersTest(String countryName, String invalidBigClientName, String gender) {
        loginScreen.setCountry(countryName);

        loginScreen.setInvalidBigClientNameField(invalidBigClientName);

        loginScreen.setGender(gender);

        String getClientName = loginScreen.getClientNameText();
        String getCountyName = loginScreen.getCountryNameText();

        Assert.assertEquals(getClientName, "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
        Assert.assertEquals(getCountyName, "Brazil");

        loginScreen.clickLetsShopButton();

        String toastMessage = loginScreen.getToastMessage();

        Assert.assertEquals(toastMessage, "Number of characters bigger than the maximum value - 50");
    }
}
