package org.mfsafrica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextBoxPage extends BasePage {
    private final By fullNameFieldLocator = By.id("userName");
    private final By emailFieldLocator = By.id("userEmail");
    private final By currentAddressFieldLocator = By.id("currentAddress");
    private final By permanentAddressFieldLocator = By.id("permanentAddress");
    private final By submitButton = By.id("submit");
    private final By outputElement = By.cssSelector("#output > div");

    /**
     * Returns the output element on the Text Box page
     * @return By locator
     */
    public By getOutputElement() {
        return outputElement;
    }

    /**
     * Submits users address
     *
     * @param fullName users full name
     * @param email users email address
     * @param currentAddress users current address
     * @param permanentAddress users permanent address
     */
    public void submitAddress(String fullName, String email, String currentAddress, String permanentAddress) {
        enterText(fullNameFieldLocator, fullName, "full name field");
        enterText(emailFieldLocator, email, "email field");
        enterText(currentAddressFieldLocator, currentAddress, "current address field");
        enterText(permanentAddressFieldLocator, permanentAddress, "permanent address field");
        scrollElementIntoView(submitButton, "submit button");
        clickElement(submitButton, "submit button");
    }

    /**
     * Checks is element has a certain class attribute
     *
     * @param locator used to identify the element
     * @param attributeValue value of the attribute
     * @return true or false boolean value
     */
    public boolean classAttributeValueExists(By locator, String attributeValue) {
        WebElement element = getElement(locator, "output section");
        boolean attributeExists = false;

        String clsVal = element.getAttribute("class");

        for (String i: clsVal.split(" ")) {
            if (i.equals(attributeValue)) {
                attributeExists = true;
                break;
            }
        }
        return attributeExists;
    }
}
