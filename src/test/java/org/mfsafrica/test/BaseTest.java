package org.mfsafrica.test;

import org.mfsafrica.driver.Driver;
import org.mfsafrica.enums.ConfigProperties;
import org.mfsafrica.util.PropertyFileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

/**
 * Provides browser setup and teardown methods
 */
class BaseTest {

    protected BaseTest() {}

    /**
     * Initializes the browser
     *
     * @throws Exception throws Exception if config could not be retrieved
     */
    @BeforeMethod
    public void setUp() throws Exception {
        Driver.initialize(PropertyFileUtil.get(ConfigProperties.BROWSER));
    }

    /**
     * Quits the browser
     */
    @AfterMethod
    public void tearDown() {
        Driver.quit();
    }
}
