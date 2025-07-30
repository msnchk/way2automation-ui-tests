package factory;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class WebDriverFactory {
    private static final String SELENOID_URL_STRING = PropertyProvider.selenoidURL();

    public static WebDriver createDriver(String executionType, String browser) {
        if (!"remote".equals(executionType)) {
            throw new IllegalArgumentException("Only remote");
        }
        return createRemoteWebDriver(browser);
    }

    private static WebDriver createRemoteWebDriver(String browser) {
        try {
            URL selenoidURL = new URL(SELENOID_URL_STRING);
            switch (browser.toLowerCase()) {
                case "chrome":
                    return new RemoteWebDriver(selenoidURL, createChromeOptions());
                case "firefox":
                    return new RemoteWebDriver(selenoidURL, createFirefoxOptions());
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }

    private static ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--window-size=1920,1080",
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );
        options.setCapability("browserVersion", "128.0");
        options.setCapability("selenoid:options", getSelenoidOptions("Chrome test"));
        return options;
    }

    private static FirefoxOptions createFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920", "--height=1080");
        options.setCapability("browserVersion", "125.0");
        options.setCapability("selenoid:options", getSelenoidOptions("Firefox test"));
        return options;
    }

    private static Map<String, Object> getSelenoidOptions(String testName) {
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("name", testName);
        selenoidOptions.put("sessionTimeout", "15m");
        selenoidOptions.put("env", Collections.singletonList("TZ=UTC"));
        Map<String, Object> labels = new HashMap<>();
        labels.put("manual", "true");
        selenoidOptions.put("labels", labels);
//        selenoidOptions.put("enableVideo", true);
        return selenoidOptions;
    }
}
