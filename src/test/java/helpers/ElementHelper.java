package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ElementHelper {
    private final WebDriver driver;
    private final Actions actions;

    public ElementHelper(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
    }

    public void clickWhenClickable(WebElement element) {
        WaitHelper.waitForElementToBeVisibleAndClickable(driver, element);
        actions.scrollToElement(element);
        element.click();
    }

    public void clickAfterPause(WebElement element) {
        WaitHelper.waitForElementToBeVisibleAndClickable(driver, element);
        actions.moveToElement(element).pause(300).click().perform();
    }

    public void switchToIframe(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public void selectCheckboxAnswersByValue(List<WebElement> checkboxes, List<String> desiredValues) {
        for(WebElement checkbox: checkboxes) {
            String value = checkbox.getAttribute("value");
            if (desiredValues.contains(value)) {
                checkbox.click();
            }
        }
    }

    public void selectDropdownByValue(WebElement selectorElement, String value) {
        Select select = new Select(selectorElement);
        select.selectByValue(value);
    }

    public void selectDropdownByText(WebElement selectorElement, String text) {
        Select select = new Select(selectorElement);
        select.selectByVisibleText(text);
    }

    @Step("Get count of opened windows/tabs")
    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }

    @Step("Switch to last browser tab")
    public void switchToLastTab() {
        List<String> handles = new ArrayList<>(driver.getWindowHandles());
        String lastHandle = handles.get(handles.size() - 1);
        driver.switchTo().window(lastHandle);
    }
}
