package pages.httpwatch;

import helpers.EnvKeys;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class AuthPage extends BasePage {
    public static final String HTTPWATCH_AUTH_URL = EnvKeys.HTTPWATCH_AUTH_URL.get();

    @FindBy(id = "displayImage")
    private WebElement displayImageButton;

    @FindBy(id = "downloadImg")
    private WebElement authenticatedImage;

    public AuthPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Click 'Display Image' button to call browser basic auth popup")
    public void clickDisplayImageButton() {
        displayImageButton.click();
    }

    @Step("Build authenticated image URL using username and password")
    public String getImageBasicAuthURL() {
       String imageSrc = authenticatedImage.getAttribute("src");
       return insertBasicAuthIntoHttpsUrl(
               imageSrc,
               EnvKeys.HTTPWATCH_LOGIN.get(),
               EnvKeys.HTTPWATCH_PASSWORD.get());
    }

    private String insertBasicAuthIntoHttpsUrl(String url, String username, String password) {
        if (url == null || !url.startsWith("https://")) {
            throw new IllegalArgumentException("URL must be not null and start with https://");
        }
        String withoutProtocol = url.substring("https://".length());
        return "https://" + username + ":" + password + "@" + withoutProtocol;
    }
}
