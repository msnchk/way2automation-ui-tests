package factory;

import helpers.EnvKeys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    public static final String GRID_URL_STRING = EnvKeys.GRID_URL.get();

    public static WebDriver createDriver(String executionType, String browser) {
        switch (executionType) {
            case "remote":
                return createRemoteWebdriver(browser);
            case "local":
                return createLocalWebdriver(browser);
            default:
                throw new IllegalArgumentException("Unsupported execution type: " + executionType);
        }
    }

    private static WebDriver createRemoteWebdriver(String browser) {
        try {
            URL gridUrl = new URL(GRID_URL_STRING);

            switch (browser) {
                case "chrome":
                    return new RemoteWebDriver(gridUrl, createChromeOptions());
                case "firefox":
                    return new RemoteWebDriver(gridUrl, createFirefoxOptions());
                case "edge":
                    return new RemoteWebDriver(gridUrl, createEdgeOptions());
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }

    private static WebDriver createLocalWebdriver(String browser) {
        switch (browser) {
            case "chrome":
                return new ChromeDriver(createChromeOptions());
            case "firefox":
                return new FirefoxDriver(createFirefoxOptions());
            case "edge":
                return new EdgeDriver(createEdgeOptions());
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static ChromeOptions createChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--window-size=1920,1080",
                "--disable-blink-features=AutomationControlled"
        );
        return chromeOptions;
    }

    private static FirefoxOptions createFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--width=1920", "--height=1080");
        return firefoxOptions;
    }

    private static EdgeOptions createEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("–-window-size=1920,1080");
        return edgeOptions;
    }
}
