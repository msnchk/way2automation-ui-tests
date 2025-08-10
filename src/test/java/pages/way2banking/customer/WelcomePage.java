package pages.way2banking.customer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.BasePage;
import pages.way2banking.Header;

public class WelcomePage extends BasePage {
    private final Header header;

    public WelcomePage(WebDriver driver, Actions actions) {
        super(driver, actions);
        this.header = new Header(driver, actions);
    }


}
