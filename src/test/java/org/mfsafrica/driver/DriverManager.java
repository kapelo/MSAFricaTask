package org.mfsafrica.driver;

import org.openqa.selenium.WebDriver;

/**
 * Provides a thread safe implementation for setting and retrieving the driver
 */
public final class DriverManager {
    private DriverManager() {}

    private static final ThreadLocal<WebDriver> WD = new ThreadLocal<>();

    /**
     * Returns the WebDriver
     *
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return WD.get();
    }

    /**
     * Sets the WebDriver
     *
     * @param driver WebDriver
     */
    public static void setDriver(WebDriver driver) {
        WD.set(driver);
    }

    /**
     * Removes the WebDriver instance from memory
     */
    public static void unload() {
        WD.remove();
    }
}
