package org.mfsafrica.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

/**
 * Driver class is responsible for invoking and closing the browser.
 */

public final class Driver {
    private Driver() {}

    /**
     * Initializes and launches the browser
     *
     * @param browser chrome or firefox
     */
    public static void initialize(String browser) {
        if (Objects.isNull(DriverManager.getDriver() )) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/driver/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("user-agent=\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36 OPR/60.0.3255.170\"");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                DriverManager.setDriver(new ChromeDriver(chromeOptions));
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                DriverManager.setDriver(new FirefoxDriver());
            }
        }

        DriverManager.getDriver().manage().window().maximize();
    }

    /**
     * Quits the browser if it is not null
     */
    public static void quit() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
