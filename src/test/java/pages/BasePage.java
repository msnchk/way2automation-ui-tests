package pages;

import helpers.AlertHelper;
import helpers.ElementHelper;
import helpers.JavaScriptHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final Actions actions;
    protected final AlertHelper alertHelper;
    protected final ElementHelper elementHelper;
    protected final JavaScriptHelper javaScriptHelper;

    public BasePage(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
        this.alertHelper = new AlertHelper(driver);
        this.elementHelper = new ElementHelper(driver, actions);
        this.javaScriptHelper = new JavaScriptHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public final AlertHelper getAlertHelper() {
        return alertHelper;
    }

    public final ElementHelper getElementHelper() {
        return elementHelper;
    }

    public final JavaScriptHelper getJavaScriptHelper() {
        return javaScriptHelper;
    }
}
