package pages.httpwatch;

import helpers.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AuthPage extends BasePage {
    public static final String HTTPWATCH_AUTH_URL = PropertyProvider.httpWatchUrl();

    @FindBy(id = "displayImage")
    private WebElement displayImageButton;

    @FindBy(id = "downloadImg")
    private WebElement authenticatedImage;

    public AuthPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    public void clickDisplayImageButton() {
        displayImageButton.click();
    }

    public String getImageBasicAuthURL(String username, String password) {
       String imageSrc = authenticatedImage.getAttribute("src");
       return insertBasicAuthIntoHttpsUrl(imageSrc, username, password);
    }

    public String insertBasicAuthIntoHttpsUrl(String url, String username, String password) {
        if (url == null || !url.startsWith("https://")) {
            throw new IllegalArgumentException("URL must be not null and start with https://");
        }
        String withoutProtocol = url.substring("https://".length());
        return "https://" + username + ":" + password + "@" + withoutProtocol;
    }
}
