package pages;

import helpers.PropertyProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public static final String MAIN_PAGE_URL = PropertyProvider.getInstance().getProperty("way2automation.base.url");
    public static final String JAVA_API_TESTING_URL = "https://www.way2automation.com/soapui/rest-api-webservices-testing-training";
    public static final String ROBOT_FRAMEWORK_SELENIUM_TUTORIAL_URL = "https://www.selenium-tutorial.com/p/robot-framework-python-ui-api-automation";

    @FindBy(xpath = "//h3[normalize-space(text())='API Testing with Java']")
    private WebElement ApiTestingWithJavaFlipBox;

    @FindBy(xpath = "//a[contains(@href, 'rest-api-webservices-testing-training') and normalize-space(text())='Read More']")
    private WebElement readMoreApiTestingWithJavaButton;

    @FindBy(xpath = "//div[normalize-space(text())='Testing RestFul webservices using XML and Json request.']")
    private WebElement blockingReadMoreButtonOverlay;

    @FindBy(xpath = "//a[contains(@class, 'pp-info-box-button') and contains(@href, 'robot-framework-python-ui-api-automation')]")
    private WebElement robotFrameworkForAutomationButton;

    public MainPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public void navigateToAPITestingWithJavaFromFlipBox() {
        clickWhenClickable(ApiTestingWithJavaFlipBox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", blockingReadMoreButtonOverlay);
        clickWhenClickable(readMoreApiTestingWithJavaButton);
    }

    public void navigateToRobotFrameworkForAutomationFromCarousel() {
        clickWhenClickable(robotFrameworkForAutomationButton);
    }
}
