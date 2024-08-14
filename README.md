# ECOMMERCE-APPIUM


# Automation with appium for a marketplace app:

This project is an automation for a marketplace app developed using Appium with Java. The automation is separate on pages and these pages are loginPage, productsPage, cartPage, and browserPage. The test scripts are simple but I tried to use my knowledge to practice and show my skills.


## Requirements:

1. **LoginPage**:
- The user just can log in if the name field is filled in.
- If the user didn't fill the name field and try to log in the user need recieve the message: "Please enter your name".
  
2. **ProductsPage**:
- The user can scroll up and down searching to a product.
- The user can add one product to the cart and this number needs to appear at cart icon.
- The user can add more than one product to the cart and the number of products need to appear at cart icon.

3. **Cartpage**: Candidatos e eleitores só podem ser excluídos se não tiverem votos computados.
- The user can view his shopping amount at this page.
- The user can view all the products added and it's informations.
- The user can open the Terms.
  
4. **BrowserPage**:
- The user can open the browser.
- The user can search in the browser.
- The user can return to the app.


## Main technologies Used:

- **Java**: 17.
- **Appium**: 9.2.1.
- **Selenium**: 4.23.0.
- **Allure**: 2.24.0.
- **TestNG**: 7.10.2.


## How to run:

1. **Cloning the repository**:
   git clone https://github.com/adelmoGama/ecommerce-appium.git
2. **Changing the app path**:
   You will need change the path of the app on AppiumConnectionConfig.
3. **Runing the tests**:
   You can run the tests just executing the testng.xml file where all classes are in.
4. **Generating the allure report**:
   After the tests finished you need to get in ´target package´ and execute the command to generate the report.
   - cd target
   - allure generate -c
5. **Some help**
   - You can have some problem when try to run your tests and when this error hapen "" you just need to execute this two commands on cmd where they will uninstall UiAutomator2 and then you can retry the execution
   - adb uninstall io.appium.uiautomator2.server
   - adb uninstall io.appium.uiautomator2.server.test

     
## Improvements:

...
