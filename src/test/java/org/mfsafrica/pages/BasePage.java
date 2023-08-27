package org.mfsafrica.pages;

import org.mfsafrica.constants.FrameworkConstants;
import org.mfsafrica.driver.DriverManager;
import org.mfsafrica.util.TestLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * BasePage provides methods to wait for elements and properly interact with the elements
 */
public class BasePage {
    Alert alert;
    /**
     * Ensures element is clickable
     *
     * @param locator used to find the element
     * @return WebElement
     */
    private static WebElement waitForElementToBeClickable(By locator) {
        try {
            return new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitWait())
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Ensures element is located and visible
     *
     * @param locator used to find the element
     * @return WebElement
     */
    private static WebElement waitForElementToBeVisible(By locator) {
        try {
            return new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitWait())
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    protected static String getPageSource() {
        try {
            return DriverManager.getDriver().getPageSource();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Clicks on a WebElement
     *
     * @param locator used to find the element
     * @param label label to identify element for logging purpose
     */
    protected static void clickElement(By locator, String label) {
        try {
            scrollElementIntoView(locator, label);
            waitForElementToBeClickable(locator).click();
            TestLogger.getLogger().info(label + " clicked");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Clicks on a WebElement
     *
     * @param element web element
     * @param label label to identify element for logging purpose
     */
    protected static void clickElement(WebElement element, String label) {
        try {
            scrollElementIntoView(element, label);
            element.click();
            TestLogger.getLogger().info(label + " clicked");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Enters text into text field
     *
     * @param locator used to find the element
     * @param text text to enter into element
     * @param label label to identify element for logging purpose
     */
    protected static void enterText(By locator, String text, String label) {
        try {
            scrollElementIntoView(locator, label);
            waitForElementToBeVisible(locator).sendKeys(text);
            TestLogger.getLogger().info("Text entered into " + label);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Retrieve text from element
     *
     * @param locator used to find the element
     * @param label label to identify element for logging purpose
     * @return String
     */
    protected static String getText(By locator, String label) {
        try {
            String text = waitForElementToBeVisible(locator).getText();
            TestLogger.getLogger().info(text + " text retrieved from " + label);

            return text;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Retrieve all child elements from parent element
     *
     * @param parentElement used to find the parent element
     * @param childLocator used to find the child elements
     * @param label label to identify element for logging purpose
     * @return List<WebElement> list of web elements
     */
    protected static List<WebElement> getChildElements(WebElement parentElement, By childLocator, String label) {
        try {
            List<WebElement> childElements = parentElement.findElements(childLocator);
            TestLogger.getLogger().info("Child elements retrieved from " + label);

            return childElements;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Retrieve web element
     *
     * @param locator used to find the element
     * @param label label to identify element for logging purpose
     * @return WebElement web element
     */
    protected static WebElement getElement(By locator, String label) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            TestLogger.getLogger().info(label + " element retrieved");

            return element;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Retrieve all child elements from parent element
     *
     * @param locator used to find the elements
     * @param label label to identify element for logging purpose
     * @return List<WebElement> list of web elements
     */
    protected static List<WebElement> getElements(By locator, String label) {
        try {
            List<WebElement> elements = DriverManager.getDriver().findElements(locator);
            TestLogger.getLogger().info("Elements retrieved from " + label);

            return elements;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Returns alert
     *
     * @return String alert
     */
    private Alert getAlert() {
        new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitWait())
                .until(ExpectedConditions.alertIsPresent());

        return DriverManager.getDriver().switchTo().alert();
    }

    protected String getAlertText() {
        alert = getAlert();
        return alert.getText();
    }

    /**
     * Accept alert
     */
    protected void acceptAlert() {
        alert.accept();
    }

    /**
     * Ensures element is located and visible
     *
     * @param locator used to find the element
     */
    private static void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
        try {
            new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.getExplicitWait())
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Wait for frame element to be available and switch to it
     *
     * @param locator used to find the element
     * @param label label to identify element for logging purpose
     */
    protected static void switchToFrame(By locator, String label) {
        try {
            waitForFrameToBeAvailableAndSwitchToIt(locator);
            TestLogger.getLogger().info("Switched to " + label + " element");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Switches to parent frame of current frame
     */
    protected static void switchToParentFrame() {
        try {
            DriverManager.getDriver().switchTo().parentFrame();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Switches to frame to default content
     */
    protected static void switchFrameToDefaultContent() {
        try {
            DriverManager.getDriver().switchTo().defaultContent();
            TestLogger.getLogger().info("Switched back to default content");
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Scroll element into view
     *
     * @param locator web element locator
     * @param label label to identify element for logging purpose
     */
    protected static void scrollElementIntoView(By locator, String label) {
        WebElement element = getElement(locator, label);

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverManager.getDriver();

        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);;

        TestLogger.getLogger().info(label + " scrolled into view");
    }

    /**
     * Scroll element into view
     *
     * @param element web element locator
     * @param label label to identify element for logging purpose
     */
    protected static void scrollElementIntoView(WebElement element, String label) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverManager.getDriver();

        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);;

        TestLogger.getLogger().info(label + " scrolled into view");
    }

    /**
     * Drags slider element
     *
     * @param locator used to find the element
     * @param noOfDrags no of times to drag slider
     */
    protected static void dragSlider(By locator, int noOfDrags) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            TestLogger.getLogger().info("Slider element retrieved");
            int startPoint = Integer.parseInt(element.getAttribute("value"));

            for (int i = startPoint; i < noOfDrags ; i++) {
                element.sendKeys(Keys.ARROW_RIGHT);

            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }

        TestLogger.getLogger().info("Dragged slider to value " + noOfDrags);
    }

    /**
     * Gets an elements value attribute
     *
     * @param locator used to find the element
     * @param value value attribute
     * @return String returns the value of the attribute
     */
    protected String getElementAttribute(By locator, String value) {
        try {
            WebElement element = waitForElementToBeVisible(locator);
            String attributeValue = element.getAttribute(value);
            TestLogger.getLogger().info(value + " attribute value returned");

            return attributeValue;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Returns element that has the text provided from a list
     * @param webElements list of web elements
     * @param optionText element text we expect to match
     * @return WebElement
     */
    protected WebElement getElementWithTextFromList(List<WebElement> webElements, String optionText) {
        for (WebElement element : webElements) {
            if (element.getText().equalsIgnoreCase(optionText)) {
                return element;
            }
        }

        return null;
    }
}
