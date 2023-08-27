package org.mfsafrica.util;

import org.mfsafrica.constants.FrameworkConstants;
import org.mfsafrica.enums.ConfigProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Provides a method to load and retrieve the framework properties
 */
public final class PropertyFileUtil {
    private PropertyFileUtil() {}

    private static final Properties PROPERTY = new Properties();
    private static final Map<String, String> CONFIG_MAP = new HashMap<>();

    static {
        try {
            FileInputStream file = new FileInputStream(FrameworkConstants.getConfigPath());

            PROPERTY.load(file);

            PROPERTY.forEach((key, value) -> CONFIG_MAP.put(String.valueOf(key), String.valueOf(value)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the value based on the configuration key
     *
     * @param key - used to retrieve the corresponding configuration value
     * @return - value corresponding to the key
     * @throws Exception - throws Exception if config could not be retrieved
     */
    public static String get(ConfigProperties key) throws Exception {
        if (Objects.isNull(key) || Objects.isNull(CONFIG_MAP.get(key.name().toLowerCase()))) {
            throw new Exception("Property name \"" + key + "\" is either null or not found. Please check config.properties");
        }

        return CONFIG_MAP.get(key.name().toLowerCase());
    }
}
