package factory;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    public static final String GRID_URL_STRING = PropertyProvider.gridUrl();

    public static WebDriver create(String browser) {
        try {
            URL gridUrl = new URL(GRID_URL_STRING);

            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments(
                            "--window-size=1920,1080",
                            "--disable-blink-features=AutomationControlled"
                    );
                    return new RemoteWebDriver(gridUrl, chromeOptions);
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--width=1920", "--height=1080");
                    return new RemoteWebDriver(gridUrl, firefoxOptions);
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }
}
