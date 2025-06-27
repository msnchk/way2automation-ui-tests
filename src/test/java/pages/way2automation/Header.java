package pages.way2automation;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class Header extends BasePage {
    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='All Courses']")
    private WebElement allCoursesMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Selenium']")
    private WebElement seleniumSubMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Selenium with Java']")
    private WebElement seleniumWithJavaSubMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Video Tutorial']")
    private WebElement videoTutorialMenu;

    @FindBy(xpath = "//*[@id='ast-desktop-header']//span[@class='menu-text' and normalize-space(text())='Cypress']")
    private WebElement cypressSubMenu;

    @FindBy(css = "a[aria-label='YouTube']")
    private WebElement youTubeIcon;

    public Header(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Navigate to 'Selenium with Java' from the top menu")
    public void navigateToSeleniumWithJavaFromMenu() {
       actions.moveToElement(allCoursesMenu).perform();
       clickWhenClickable(seleniumSubMenu);
       clickWhenClickable(seleniumWithJavaSubMenu);
    }

    @Step("Navigate to 'Cypress' from the top menu")
    public void navigateToCypressTutorialFromMenu() {
        actions.moveToElement(videoTutorialMenu).perform();
        clickWhenClickable(cypressSubMenu);
    }

    @Step("Click the YouTube icon in the header")
    public void navigateToYouTubeFromHeaderIcon() {
        clickWhenClickable(youTubeIcon);
    }
}
