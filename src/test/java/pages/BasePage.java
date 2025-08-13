package pages;

import helpers.AlertHelper;
import helpers.ElementHelper;
import helpers.PageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final Actions actions;
    protected final AlertHelper alertHelper;
    protected final ElementHelper elementHelper;
    protected final PageHelper pageHelper;

    public BasePage(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
        this.alertHelper = new AlertHelper(driver, actions);
        this.elementHelper = new ElementHelper(driver, actions);
        this.pageHelper = new PageHelper(driver, actions);
        PageFactory.initElements(driver, this);
    }

    public AlertHelper getAlertHelper() {
        return alertHelper;
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }
}
