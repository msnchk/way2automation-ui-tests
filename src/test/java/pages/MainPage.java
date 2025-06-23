package pages;

import helpers.PropertyProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public static final String MAIN_PAGE_URL = PropertyProvider.baseUrl();

    @FindBy(xpath = "//h3[normalize-space(text())='API Testing with Java']")
    private WebElement ApiTestingWithJavaFlipBox;

    @FindBy(xpath = "//a[contains(@href, 'rest-api-webservices-testing-training') and normalize-space(text())='Read More']")
    private WebElement readMoreApiTestingWithJavaButton;

    @FindBy(xpath = "//div[normalize-space(text())='Testing RestFul webservices using XML and Json request.']")
    private WebElement blockingReadMoreButtonOverlay;

    @FindBy(xpath = "//h2[@class='elementor-image-box-title' and normalize-space(text())='Most Popular Software Testing Courses']")
    private WebElement mostPopularCoursesBlock;

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
        actions.scrollToElement(mostPopularCoursesBlock);
        clickWhenClickable(robotFrameworkForAutomationButton);
    }
}
