package org.mfsafrica.pages;

import org.mfsafrica.util.GeneralHelper;
import org.openqa.selenium.By;

public class RegistrationPage extends BasePage {
    private final By userFormHeaderLabelLocator = By.cssSelector("#userForm > div > h4");
    private final By firstNameFieldLocator = By.id("firstname");
    private final By lastNameFieldLocator = By.id("lastname");
    private final By usernameFieldLocator = By.id("userName");
    private final By passwordFieldLocator = By.id("password");
    private final By captchaIFrameLocator = By.cssSelector("iframe[title=\"reCAPTCHA\"]");
    private final By captchaCheckboxLocator = By.cssSelector("div.recaptcha-checkbox-border");
    private final By registerButtonLocator = By.id("register");

    /**
     * Gets the user form header text from the page
     *
     * @return String
     */
    public String getUserFormHeaderLabelText() {
        return getText(userFormHeaderLabelLocator, "form header");
    }

    /**
     * Enter first name into field
     *
     * @param firstname user's first name
     */
    public void enterFirstName(String firstname) {
        enterText(firstNameFieldLocator, firstname, "first name field");
    }

    /**
     * Enter last name into field
     *
     * @param lastname user's last name
     */
    public void enterLastName(String lastname) {
        enterText(lastNameFieldLocator, lastname, "last name field");
    }

    /**
     * Enter username into field
     *
     * @param username user's username
     */
    public void enterUsername(String username) {
        enterText(usernameFieldLocator, username, "username field");
    }

    /**
     * Enter password into field
     *
     * @param password user's password
     */
    public void enterPassword(String password) {
        enterText(passwordFieldLocator, password, "password field");
    }

    /**
     * Click on register button
     */
    public void clickOnRegisterButton() {
        clickElement(registerButtonLocator, "register button");
    }

    /**
     * Click on captcha checkbox
     */
    public void clickOnCaptchaCheckbox() {
        clickElement(captchaCheckboxLocator, "Captcha checkbox");
    }

    /**
     * Register user
     *
     * @param firstName user's first name
     * @param lastName user's last name
     * @param username user's username
     * @param password user's password
     */
    public void fillAndSubmitUserForm(String firstName, String lastName, String username, String password) throws InterruptedException {
        enterFirstName(firstName);
        Thread.sleep(GeneralHelper.getRandomIntInRange(5000, 10000));
        enterLastName(lastName);
        Thread.sleep(GeneralHelper.getRandomIntInRange(4000, 10000));
        enterUsername(username);
        Thread.sleep(GeneralHelper.getRandomIntInRange(3000, 10000));
        enterPassword(password);
        switchToFrame(captchaIFrameLocator, "Captcha iframe");
        // Ideally captcha's can't be automated, so we will definitely experience flakiness in the test because of this
        // Google gives the option to disable captcha's on a test environment which will be the approach to be followed
        clickOnCaptchaCheckbox();
        switchFrameToDefaultContent();
        clickOnRegisterButton();
    }

    /**
     * Returns alert message
     *
     * @return String
     */
    public String getAlertMessage() {
        return getAlertText();
    }

    /**
     * Dismisses the alert
     */
    public void dismissAlert() {
        acceptAlert();
    }
}
