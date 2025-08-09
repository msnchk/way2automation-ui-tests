package tests.way2banking;

import data.way2banking.SampleFormData;
import data.way2banking.SampleFormDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.way2banking.BankingMainPage;
import pages.way2banking.SampleFormPage;
import tests.BaseTest;

public class BankingUITest extends BaseTest {
    private BankingMainPage bankingMainPage;

    @BeforeMethod
    public void openBankingMainPage() {
        getDriver().get(BankingMainPage.BANKING_MAIN_PAGE_URL);
        bankingMainPage = new BankingMainPage(getDriver(), getActions());
    }

    @Test(
            dataProvider = "sampleData",
            dataProviderClass = SampleFormDataProvider.class
    )
    public void shouldTestSampleFormWithValidData(SampleFormData sampleFormData) {
        SampleFormPage sampleFormPage = bankingMainPage.openSampleFormPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                getDriver().getCurrentUrl(),
                SampleFormPage.SAMPLE_FORM_URL,
                "URL doesn't match expected"
        );
        sampleFormPage.fillFormWithUserData(sampleFormData);
        softAssert.assertTrue(
                sampleFormPage.isSuccessMessageDisplayed(),
                "Success message isn't displayed");
        softAssert.assertEquals(
                sampleFormPage.getSuccessMessageText(),
                "User registered successfully!",
                "Success message doesn't match expected"
        );
        softAssert.assertAll();
    }
}
