package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class PageHelper {
    private final WebDriver driver;
    private final Actions actions;

    public PageHelper(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
    }

    @Step("Get count of opened windows/tabs")
    public int getWindowCount() {
        return driver.getWindowHandles().size();
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

    @Step("Switch to last browser tab")
    public void switchToLastTab() {
        List<String> handles = new ArrayList<>(driver.getWindowHandles());
        String lastHandle = handles.get(handles.size() - 1);
        driver.switchTo().window(lastHandle);
    }
}
