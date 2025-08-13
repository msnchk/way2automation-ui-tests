package pages.way2automation;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class Popup extends BasePage {
    @FindBy(css = ".dialog-widget-content.dialog-lightbox-widget-content")
    private WebElement popup;

    @FindBy(css = ".dialog-close-button.dialog-lightbox-close-button")
    private WebElement closePopupButton;

    @FindBy(xpath = "//a[.//span[normalize-space(text())='Register Now!']]")
    private WebElement registerNowPopupButton;

    public Popup(WebDriver driver, Actions actions) {
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
        elementHelper.clickWhenClickable(registerNowPopupButton);
    }

    @Step("Click the 'Close' button on the popup")
    public void clickButtonClosePopup() {
        elementHelper.clickWhenClickable(closePopupButton);
    }
}
