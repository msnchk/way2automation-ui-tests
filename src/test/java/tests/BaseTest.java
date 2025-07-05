package tests;

import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private WebDriver driver;
    private Actions actions;

    @BeforeMethod
    public void setup(ITestContext context) {
        String executionType = context.getCurrentXmlTest().getParameter("executionType");
        String browser = context.getCurrentXmlTest().getParameter("browser");
        driver = WebDriverFactory.createDriver(executionType, browser);
        actions = new Actions(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Actions getActions() {
        return actions;
    }
}