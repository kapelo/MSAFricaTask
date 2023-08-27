package org.mfsafrica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NestedFramesPage extends BasePage {
    private final By parentFrameLocator = By.id("frame1");
    private final By childFrameLocator = By.cssSelector("body > iframe");

    public void printParentAndChildFrame() {
        switchToFrame(parentFrameLocator, "parent frame");
        switchToFrame(childFrameLocator, "child frame");
        System.out.println("Child frame page source: " + getPageSource());
        switchToParentFrame();
        System.out.println("Parent frame page source: " + getPageSource());
    }
}
