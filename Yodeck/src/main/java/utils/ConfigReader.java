package utils;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;
    public final WebDriver driver;
    public final WaitHelper waitHelper;

    public ConfigReader(WebDriver driver){
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver,5);
    }

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();

            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                if (value != null && value.contains("${")) {
                    for (String propKey : properties.stringPropertyNames()) {
                        String placeholder = "${" + propKey + "}";
                        if (value.contains(placeholder)) {
                            value = value.replace(placeholder, properties.getProperty(propKey));
                        }
                    }
                    properties.setProperty(key, value);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load configuration file.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}