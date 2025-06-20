package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private WebDriver driver;
    private Actions actions;

    @BeforeMethod
    public void setup() {
        driver = createWebDriver();
        actions = new Actions(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*",
                "--disable-gpu",
                "--no-sandbox",
                "--headless=new",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080",
                "--disable-blink-features=AutomationControlled"
        );
        return new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Actions getActions() {
        return actions;
    }
}