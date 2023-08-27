package org.mfsafrica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class LeftAccordionPage extends BasePage {
    private final By accordionTitleLocator = By.cssSelector("div.header-text");
    private final By accordionMenuListLocator = By.cssSelector("span.text");

    /**
     * Navigates to the page indicated by the arguments
     *
     * @param parentAccordion accordion header text
     * @param menuItem sub menu item text within the accordion
     */
    public void navigateToPage(String parentAccordion, String menuItem) {
        List<WebElement> accordions = getElements(accordionTitleLocator, "accordion header");
        WebElement accordionElement = getElementWithTextFromList(accordions, parentAccordion);

        scrollToAndClickElement(accordionElement, parentAccordion + " accordion");

        // Get the parent group element three levels up the DOM tree
        WebElement accordionGroupElement = accordionElement
                .findElement(By.xpath(".."))
                .findElement(By.xpath(".."))
                .findElement(By.xpath(".."));
        List<WebElement> accordionMenuItems = getChildElements(accordionGroupElement, accordionMenuListLocator, " accordion");
        WebElement menuItemElement = getElementWithTextFromList(accordionMenuItems, menuItem);

        scrollToAndClickElement(menuItemElement, menuItem + " option");
    }

    /**
     * Scrolls to and clicks on element
     *
     * @param element web element
     * @param label label for logging purposes
     */
    private void scrollToAndClickElement(WebElement element, String label) {
        if (Objects.nonNull(element)) {
            scrollElementIntoView(element, label);
            clickElement(element, label);
        } else {
            throw new Error(label + " element does not exist");
        }
    }
}
