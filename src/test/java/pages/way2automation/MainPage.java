package pages.way2automation;

import helpers.PropertyProvider;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.time.Duration;

public class MainPage extends BasePage {
    public static final String MAIN_PAGE_URL = PropertyProvider.baseUrl();

    private final Header header;
    private final Popup popup;

    @FindBy(xpath = "//h3[normalize-space(text())='API Testing with Java']")
    private WebElement ApiTestingWithJavaFlipBox;

    @FindBy(xpath = "//a[contains(@href, 'rest-api-webservices-testing-training') and normalize-space(text())='Read More']")
    private WebElement readMoreApiTestingWithJavaButton;

    @FindBy(xpath = "//h2[@class='elementor-image-box-title' and normalize-space(text())='Most Popular Software Testing Courses']")
    private WebElement mostPopularCoursesBlock;

    @FindBy(xpath = "//a[contains(@class, 'pp-info-box-button') and contains(@href, 'robot-framework-python-ui-api-automation')]")
    private WebElement robotFrameworkForAutomationButton;

    @FindBy(xpath = "//a[contains(@class, 'elementor-button') and normalize-space(text())='Register Now']")
    private WebElement registerNowMainPageButton;

    public MainPage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
        this.popup = new Popup(driver, actions);
    }

    public Header getHeader() {
        return header;
    }

    public Popup getPopup() {
        return popup;
    }

    @Step("Navigate to 'API Testing with Java' course from the flip box")
    public void navigateToAPITestingWithJavaFromFlipBox() {
        clickWhenClickable(ApiTestingWithJavaFlipBox);
        actions.pause(Duration.ofMillis(300)).perform();
        clickWhenClickable(readMoreApiTestingWithJavaButton);
    }

    @Step("Navigate to 'API Testing with Java' course from the flip box")
    public void failNavigateToAPITestingWithJavaFromFlipBox() {
        clickWhenClickable(ApiTestingWithJavaFlipBox);
        readMoreApiTestingWithJavaButton.click();
    }

    @Step("Navigate to 'Robot Framework for Automation' course from the carousel")
    public void navigateToRobotFrameworkForAutomationFromCarousel() {
        actions.scrollToElement(mostPopularCoursesBlock);
        clickWhenClickable(robotFrameworkForAutomationButton);
    }

    @Step("Click 'Register Now' button on the main page")
    public void clickButtonRegisterNowOnMainPage() {
        clickWhenClickable(registerNowMainPageButton);
    }
}
