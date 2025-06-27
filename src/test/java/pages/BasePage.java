package pages;

import helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected Actions actions;

    public BasePage(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
        PageFactory.initElements(driver, this);
    }

    protected void clickWhenClickable(WebElement element) {
        WaitHelper.waitForElementToBeVisible(driver, element);
        WaitHelper.waitForElementToBeClickable(driver, element);
        actions.scrollToElement(element);
        element.click();
    }
}
