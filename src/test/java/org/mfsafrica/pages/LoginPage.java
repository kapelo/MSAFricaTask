package org.mfsafrica.pages;

import org.mfsafrica.driver.DriverManager;
import org.mfsafrica.enums.ConfigProperties;
import org.mfsafrica.util.PropertyFileUtil;
import org.mfsafrica.util.TestLogger;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    private final By newUserButtonLocator = By.id("newUser");

    /**
     * Navigates to the login page
     */
    public void visit() throws Exception {
        TestLogger.getLogger().info("Loading TOOLSQA DEMO page...");
        DriverManager.getDriver().get(PropertyFileUtil.get(ConfigProperties.DEMO_QA_URL));
        TestLogger.getLogger().info("Page loaded");
    }

    /**
     * Click on new user button
     *
     * @return RegistrationPage - an instance of the registration page
     */
    public RegistrationPage clickOnNewUserButton() {
        scrollElementIntoView(newUserButtonLocator, "New user button");
        clickElement(newUserButtonLocator, "New user button");

        return new RegistrationPage();
    }
}
