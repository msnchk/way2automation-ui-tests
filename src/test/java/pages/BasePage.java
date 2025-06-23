package pages;

import helpers.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected Actions actions;

    @FindBy(css = ".dialog-widget-content.dialog-lightbox-widget-content")
    private WebElement popup;

    @FindBy(css = ".dialog-close-button.dialog-lightbox-close-button")
    private WebElement closePopupButton;

    @FindBy(xpath = "//a[.//span[normalize-space(text())='Register Now!']]")
    private WebElement registerNowButton;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='All Courses']")
    private WebElement allCoursesMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Selenium']")
    private WebElement seleniumSubMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Selenium with Java']")
    private WebElement seleniumWithJavaSubMenu;

    @FindBy(css = "a[aria-label='YouTube']")
    private WebElement youTubeIcon;

    public BasePage(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
        PageFactory.initElements(driver, this);
    }

    public void waitForPopup() {
        actions.moveByOffset(10, 10).click().perform();
        actions.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_UP).perform();
        WaitHelper.waitForElementToBeVisible(driver, popup);
    }

    public void clickButtonRegisterNowOnPopup() {
        clickWhenClickable(registerNowButton);
    }

    public void navigateToSeleniumWithJavaFromMenu() {
       clickWhenClickable(allCoursesMenu);
       clickWhenClickable(seleniumSubMenu);
       clickWhenClickable(seleniumWithJavaSubMenu);
    }

    public void navigateToYouTubeFromHeaderIcon() {
        clickWhenClickable(youTubeIcon);
    }

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
