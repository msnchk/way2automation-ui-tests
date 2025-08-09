package data.way2banking;

import org.testng.annotations.DataProvider;

import java.util.List;

public class SampleFormDataProvider {

    @DataProvider(name = "sampleData")
    public Object[][] sampleFormData() {
        return new Object[][] {
                {
                    new SampleFormData(
                            "Correct Name",
                            "Correct LastName",
                            "email@email",
                            "789password",
                            List.of("Sports"),
                            "female")
                }
        };
    }
}
