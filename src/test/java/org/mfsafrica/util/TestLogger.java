package org.mfsafrica.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides a method to retrieve the logger for the test framework
 */
public class TestLogger {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Returns the logger
     *
     * @return - Logger
     */
    public static Logger getLogger() {
        return logger;
    }
}
