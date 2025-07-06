package pages;

import helpers.WaitHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
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

    @Step("Check if the current page has vertical scroll")
    public boolean hasVerticalScroll() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Long scrollHeight = (Long) js.executeScript("return document.documentElement.scrollHeight;");
        Long clientHeight = (Long) js.executeScript("return document.documentElement.clientHeight");
        return scrollHeight > clientHeight;
    }

    @Step("Check if the current page has horizontal scroll")
    public boolean hasHorizontalScroll() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        Long scrollWidth = (Long) js.executeScript("return document.documentElement.scrollWidth;");
        Long clientWidth = (Long) js.executeScript("return document.documentElement.clientWidth");
        return scrollWidth > clientWidth;
    }

    protected void clickWhenClickable(WebElement element) {
        WaitHelper.waitForElementToBeVisible(driver, element);
        WaitHelper.waitForElementToBeClickable(driver, element);
        actions.scrollToElement(element);
        element.click();
    }

    protected void unfocusElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].blur();", element);
    }

    protected void switchToIframe(WebElement frame) {
        driver.switchTo().frame(frame);
    }
}
