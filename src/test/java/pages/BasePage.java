package pages;

import org.openqa.selenium.WebDriver;
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
}
