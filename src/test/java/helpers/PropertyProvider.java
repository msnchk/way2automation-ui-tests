package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {
    private static final String PROPERTIES_FILE = "env_local.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream propertiesInputStream = PropertyProvider.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (propertiesInputStream == null) {
                throw new RuntimeException("Properties file not found: " + PROPERTIES_FILE);
            }
            properties.load(propertiesInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }

    private static String getProperty(String key) {
        if (!properties.containsKey(key)) {
            throw new IllegalArgumentException("Ключ '" + key + "' отсутствует в properties");
        }
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Значение для ключа '" + key + "' отсутствует или пусто");
        }
        return value;
    }

    public static String baseUrl() {
        return getProperty("way2automation.base.url");
    }

    public static String loginFormUrl() {
        return getProperty("angularjs-protractor.registration.url");
    }
}



