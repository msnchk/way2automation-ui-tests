package pages.way2banking.sampleform;

import data.way2banking.sampleform.SampleFormData;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class SampleFormPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "gender")
    private WebElement genderSelect;

    @FindBy(id = "about")
    private WebElement aboutYourselfTextArea;

    @FindBy(css = "input[type='checkbox'][name='hobbies']")
    private List<WebElement> hobbiesCheckboxes;

    @FindBy(css = "button[type='submit']")
    private WebElement registerButton;

    @FindBy(css = "#successMessage")
    private List<WebElement> successMessage;

    public SampleFormPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @Step("Fill form with user data: {sampleFormData}")
    public void fillFormWithUserData(SampleFormData sampleFormData) {
        firstNameInput.sendKeys(sampleFormData.getFirstName());
        lastNameInput.sendKeys(sampleFormData.getLastName());
        email.sendKeys(sampleFormData.getEmail());
        password.sendKeys(sampleFormData.getPassword());
        chooseHobby(sampleFormData.getHobbies());
        chooseGender(sampleFormData.getGender());
        aboutYourselfTextArea.sendKeys(getLongestHobbyWord());
        registerButton.click();
    }


    private void chooseHobby(List<String> answers) {
        selectCheckboxAnswersByValue(hobbiesCheckboxes, answers);
    }

    private void chooseGender(String answer) {
        selectDropdownByValue(genderSelect, answer);
    }

    private String getLongestHobbyWord() {
        int maxLength = 0;
        String longestValue = "";
        for (WebElement hobby : hobbiesCheckboxes) {
            String value = hobby.getAttribute("value");
            if (value != null && value.length() > maxLength) {
                maxLength = value.length();
                longestValue = value;
            }
        }
        return "Самое длинное слово из предложенных хобби - " + longestValue;
    }

    @Step("Check if success message is displayed")
    public boolean isSuccessMessageDisplayed() {
        if (successMessage.isEmpty()) {
            return false;
        }
        return true;
    }

    @Step("Get success message text")
    public String getSuccessMessageText() {
        if (successMessage.isEmpty()) {
            return "";
        }
        return successMessage.get(0).getText().trim();
    }
}
