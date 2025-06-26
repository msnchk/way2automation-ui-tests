package pages.way2automation;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public abstract class Header extends BasePage {
    @FindBy(css = ".dialog-widget-content.dialog-lightbox-widget-content")
    private WebElement popup;

    @FindBy(css = ".dialog-close-button.dialog-lightbox-close-button")
    private WebElement closePopupButton;

    @FindBy(xpath = "//a[.//span[normalize-space(text())='Register Now!']]")
    private WebElement registerNowPopupButton;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='All Courses']")
    private WebElement allCoursesMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Selenium']")
    private WebElement seleniumSubMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Selenium with Java']")
    private WebElement seleniumWithJavaSubMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Video Tutorial']")
    private WebElement videoTutorialMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Cypress']")
    private WebElement cypressSubMenu;

    @FindBy(css = "a[aria-label='YouTube']")
    private WebElement youTubeIcon;

    public Header(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Wait for the popup to appear")
    public void waitForPopup() {
        actions.moveByOffset(10, 10).click().perform();
        actions.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_UP).perform();
        WaitHelper.waitForElementToBeVisible(driver, popup);
    }

    @Step("Click 'Register Now!' button on the popup")
    public void clickButtonRegisterNowOnPopup() {
        clickWhenClickable(registerNowPopupButton);
    }

    @Step("Navigate to 'Selenium with Java' from the top menu")
    public void navigateToSeleniumWithJavaFromMenu() {
       actions.moveToElement(allCoursesMenu).perform();
       clickWhenClickable(seleniumSubMenu);
       clickWhenClickable(seleniumWithJavaSubMenu);
    }

    @Step("Navigate to 'Cypress' from the top menu")
    public void navigateToCypressTutorialFromMenu() {
        actions.moveToElement(videoTutorialMenu).perform();
        clickWhenClickable(cypressSubMenu);
    }

    @Step("Click the YouTube icon in the header")
    public void navigateToYouTubeFromHeaderIcon() {
        clickWhenClickable(youTubeIcon);
    }

    @Step("Click the 'Close' button on the popup")
    public void clickButtonClosePopup() {
        clickWhenClickable(closePopupButton);
    }

    protected void clickWhenClickable(WebElement element) {
        WaitHelper.waitForElementToBeVisible(driver, element);
        WaitHelper.waitForElementToBeClickable(driver, element);
        actions.scrollToElement(element);
        element.click();
    }
}
