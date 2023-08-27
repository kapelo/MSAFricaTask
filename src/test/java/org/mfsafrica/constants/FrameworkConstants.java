package org.mfsafrica.constants;

import java.time.Duration;

/**
 * FrameworkConstants class provides methods to get configuration information
 */
public final class FrameworkConstants {

    private FrameworkConstants() {}
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final String CONFIG_PATH = RESOURCES_PATH + "/config/config.properties";
    private static final String SIKULI_IMAGES_PATH = RESOURCES_PATH + "/sikuli-images";
    private static final int EXPLICIT_WAIT = 15;

    /**
     * Returns path to config.properties
     *
     * @return CONFIG_PATH
     */
    public static String getConfigPath() {
        return CONFIG_PATH;
    }

    /**
     * Returns path to sikuli images folder
     *
     * @return SIKULI_IMAGES_PATH
     */
    public static String getSikuliImagePath() {
        return SIKULI_IMAGES_PATH;
    }

    /**
     * Returns the explicit wait time
     *
     * @return EXPLICIT_WAIT
     */
    public static Duration getExplicitWait() {
        return Duration.ofSeconds(EXPLICIT_WAIT);
    }
}
