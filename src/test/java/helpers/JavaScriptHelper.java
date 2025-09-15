package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {
    private final WebDriver driver;

    public JavaScriptHelper(WebDriver driver) {
        this.driver = driver;
    }

    private JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    public void unfocusElement(WebElement element) {
        js().executeScript("arguments[0].blur();", element);
    }

    @Step("Check if the current page has vertical scroll")
    public boolean hasVerticalScroll() {
        Long scrollHeight = (Long) js().executeScript("return document.documentElement.scrollHeight;");
        Long clientHeight = (Long) js().executeScript("return document.documentElement.clientHeight");
        return scrollHeight > clientHeight;
    }

    @Step("Check if the current page has horizontal scroll")
    public boolean hasHorizontalScroll() {
        Long scrollWidth = (Long) js().executeScript("return document.documentElement.scrollWidth;");
        Long clientWidth = (Long) js().executeScript("return document.documentElement.clientWidth");
        return scrollWidth > clientWidth;
    }
}
